package com.pm.service;

import com.pm.entity.*;
import com.pm.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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
        if (task.getParentTaskId() != 0) {
            Optional<ParentTask> optParentTask = parentTaskRepository.findById(task.getParentTaskId());
            optParentTask.ifPresent(task::setParentTask);
        }
        if (task.getProjectId() != 0) {
            Optional<Project> optProject = projectRepository.findById(task.getProjectId());
            optProject.ifPresent(task::setProject);
        }

        taskRepository.save(task);
        setUser(task);
        return task;
    }

    private void setUser(final Task task) {
        if (task.getUserId() != 0) {
            final Optional<User> optUser = userRepository.findByEmployeeId(task.getUserId());
            if (optUser.isPresent()) {
                final User user = optUser.get();
                user.setTask(task);
                user.setProject(task.getProject());
                userRepository.save(user);
            }
        }
    }

    public Task updateTask(Task task) {
        log.info("-updateTask-");
        if (task != null) {
            taskRepository.save(task);
        }
        return task;
    }

    public Task updateTaskStatus(Task task) {
        return new Task();
    }

    public ParentTask createParentTask(ParentTask parentTask) {
        parentTaskRepository.save(parentTask);
        return parentTask;
    }

    public List<ParentTask> findAllParentTasks() {
        return parentTaskRepository.findAll();
    }

    public List<ParentTask> findAllParentTasksByInput(String input) {
        if ("default".equals(input)) {
            return findAllParentTasks();
        }
        return parentTaskRepository.findByParentTaskNameContaining(input);
    }

    public List<Task> findTasksByProjectId(Long id) {
        List<Task> tasks = taskRepository.findTasksByProjectId(id);
        for (Task task : tasks) {
            Optional<User> optUser = taskRepository.findUserByTaskId(task.getTaskId());
            if (optUser.isPresent()) {
                task.setUserName(optUser.get().getFirstName());
            }
        }
        return tasks;
    }
}
