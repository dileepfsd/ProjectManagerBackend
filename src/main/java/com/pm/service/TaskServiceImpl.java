package com.pm.service;

import com.pm.entity.*;
import com.pm.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@Transactional
public class TaskServiceImpl implements ITaskService {

    @Resource
    private TaskRepository taskRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ProjectRepository projectRepository;
    @Resource
    private ParentTaskRepository parentTaskRepository;

    public Task createTask(Task task) {
        return new Task();
    }

    public Task updateTask(Task task) {
        return new Task();
    }

    public Task updateTaskStatus(Task task) {
        return new Task();
    }

    public ParentTask createParentTask(ParentTask parentTask) {
        return new ParentTask();
    }

    public List<ParentTask> findAllParentTasks() {
        return Arrays.asList(new ParentTask());
    }

    public List<ParentTask> findAllParentTasksByInput(String input) {
        return Arrays.asList(new ParentTask());
    }
}
