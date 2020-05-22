package com.pm.service;

import com.pm.entity.User;
import com.pm.exception.UserException;
import com.pm.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Test(expected = UserException.class)
    public void createUserEmployeeIdExist() {
        final User user = mockUser(0);
        when(userRepository.findByEmployeeId(any())).thenReturn(Optional.of(mockUser(0)));
        userServiceImpl.createUser(user);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void updateUser() {
        final User user = mockUser(1);
        when(userRepository.save(any())).thenReturn(mockUser(1));
        userServiceImpl.createUser(user);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void createNewUser() {
        final User user = mockUser(0);
        when(userRepository.findByEmployeeId(any())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(mockUser(0));
        userServiceImpl.createUser(user);
        verify(userRepository, times(1)).save(any());
    }


    @Test
    public void findAllUser() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(mockUser(0)));
        userServiceImpl.findAllUser();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void findAllUserByInputDefault() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(mockUser(0)));
        assertNotNull(userServiceImpl.findAllUserByInput("default"));
    }

    @Test
    public void findAllUserByInputFirstName() {
        when(userRepository.findByFirstNameContaining(anyString())).thenReturn(Arrays.asList(mockUser(0)));
        assertNotNull(userServiceImpl.findAllUserByInput("test"));
    }

    @Test
    public void findAllUserByInputLastName() {
        when(userRepository.findByFirstNameContaining(anyString())).thenReturn(Arrays.asList());
        when(userRepository.findByLastNameContaining(anyString())).thenReturn(Arrays.asList(mockUser(0)));
        assertNotNull(userServiceImpl.findAllUserByInput("test"));
    }

    @Test
    public void findAllUserByInputEmployeeId() {
        when(userRepository.findByFirstNameContaining(anyString())).thenReturn(Arrays.asList());
        when(userRepository.findByLastNameContaining(anyString())).thenReturn(Arrays.asList());
        when(userRepository.findByEmployeeIdContaining(anyString())).thenReturn(Arrays.asList(mockUser(0)));
        assertNotNull(userServiceImpl.findAllUserByInput("test"));
    }

    @Test
    public void findByIdReturnEmpty() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertNull(userServiceImpl.findById(10L));
    }

    @Test
    public void findByIdReturnValue() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser(1)));
        assertNotNull(userServiceImpl.findById(10L));
    }

    @Test
    public void deleteUserIdNotExist() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertNull(userServiceImpl.deleteUser(10L));
    }

    @Test
    public void deleteUserIdExist() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser(1)));
        doNothing().when(userRepository).delete(isA(User.class));
        userServiceImpl.deleteUser(1L);
    }

    private User mockUser(long id) {
        final User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmployeeId(123l);
        user.setUserId(id);
        return user;
    }
}
