package com.pm.service;

import com.pm.entity.User;
import com.pm.exception.UserException;
import com.pm.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;

    public User createUser(User user) {
        if (user != null) {
            if (user.getUserId() > 0) {
                userRepository.save(user);
            } else {
                Optional<User> vUser = userRepository.findByEmployeeId(user.getEmployeeId());
                if (vUser.isPresent()) {
                    throw new UserException("Employee Id already exists");
                }
                userRepository.save(user);
            }
        }
        return user;
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public List<User> findAllUserByInput(String input) {
        log.info("-findAllUserByInput-");
        List<User> users;
        if ("default".equals(input)) {
            return findAllUser();
        }
        users = userRepository.findByFirstNameContaining(input);
        if (users != null && users.isEmpty()) {
            users = userRepository.findByLastNameContaining(input);
        }
        if (users != null && users.isEmpty()) {
            users = userRepository.findByEmployeeIdContaining(input);
        }
        return users;
    }

    public User findById(Long id) {
        log.info("-User Service findById-");
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            return optUser.get();
        }
        return null;
    }

    public User deleteUser(Long id) {
        log.info("-deleteUser-");
        final Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            final User user = optUser.get();
            userRepository.delete(user);
        }
        return null;
    }
}
