package com.pm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.entity.User;
import com.pm.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userServiceImpl;

    @Test
    public void createUser() throws Exception {
        final User user = mockUser();
        ObjectMapper mapper = new ObjectMapper();
        when(userServiceImpl.createUser(any())).thenReturn(user);
        String requestJson = mapper.writeValueAsString(user);
        final MvcResult mvcResult = mockMvc.perform(
                post("/user/create")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        verify(userServiceImpl, times(1)).createUser(any());
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void findAllUser() throws Exception {
        final User user = mockUser();
        List<User> users = Arrays.asList(user, user);
        ObjectMapper mapper = new ObjectMapper();
        when(userServiceImpl.findAllUser()).thenReturn(users);
        String requestJson = mapper.writeValueAsString(users);
        final MvcResult mvcResult = mockMvc.perform(
                get("/user/findAllUser")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        verify(userServiceImpl, times(1)).findAllUser();
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void findAllUserByInput() throws Exception {
        final User user = mockUser();
        List<User> users = Arrays.asList(user, user);
        ObjectMapper mapper = new ObjectMapper();
        when(userServiceImpl.findAllUserByInput(anyString())).thenReturn(users);
        String requestJson = mapper.writeValueAsString(users);
        final MvcResult mvcResult = mockMvc.perform(
                get("/user/findAllUserByInput/test")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        verify(userServiceImpl, times(1)).findAllUserByInput(anyString());
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void findUser() throws Exception {
        final User user = mockUser();
        ObjectMapper mapper = new ObjectMapper();
        when(userServiceImpl.findById(anyLong())).thenReturn(user);
        String requestJson = mapper.writeValueAsString(user);
        final MvcResult mvcResult = mockMvc.perform(
                get("/user/findUserById/10")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        verify(userServiceImpl, times(1)).findById(anyLong());
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void deleteUser() throws Exception {
        final User user = mockUser();
        ObjectMapper mapper = new ObjectMapper();
        when(userServiceImpl.deleteUser(anyLong())).thenReturn(user);
        String requestJson = mapper.writeValueAsString(100);
        final MvcResult mvcResult = mockMvc.perform(
                post("/user/delete")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        verify(userServiceImpl, times(1)).deleteUser(anyLong());
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    private User mockUser() {
        final User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmployeeId(123l);
        return user;
    }
}
