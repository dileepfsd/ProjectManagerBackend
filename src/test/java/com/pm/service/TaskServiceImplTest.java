package com.pm.service;

import com.pm.entity.ParentTask;
import com.pm.entity.Project;
import com.pm.entity.Task;
import com.pm.entity.User;
import com.pm.repository.ParentTaskRepository;
import com.pm.repository.ProjectRepository;
import com.pm.repository.TaskRepository;
import com.pm.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ParentTaskRepository parentTaskRepository;

    @Test
    public void createTask() {
        when(parentTaskRepository.findById(anyLong())).thenReturn(Optional.of(mockParentTask()));
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(mockProject()));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser(1)));
        when(taskRepository.save(any())).thenReturn(mockTask());
        assertNotNull(taskServiceImpl.createTask(mockTask()));
    }

    @Test
    public void updateTask() {
        when(taskRepository.save(any())).thenReturn(mockTask());
        assertNotNull(taskServiceImpl.updateTask(mockTask()));
    }

    @Test
    public void updateTaskStatus() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(mockTask()));
        when(taskRepository.save(any())).thenReturn(mockTask());
        taskServiceImpl.updateTaskStatus(mockTask());
        verify(taskRepository, times(1)).findById(any());
        verify(taskRepository, times(1)).save(any());
    }

    @Test
    public void createParentTask() {
        when(parentTaskRepository.save(any())).thenReturn(mockParentTask());
        assertNotNull(taskServiceImpl.createParentTask(mockParentTask()));
    }

    @Test
    public void findAllParentTasks() {
        when(parentTaskRepository.findAll()).thenReturn(Arrays.asList(mockParentTask()));
        assertNotNull(taskServiceImpl.findAllParentTasks());
    }
    @Test
    public void findAllParentTasksByInput() {
        when(parentTaskRepository.findByParentTaskNameContaining(anyString())).thenReturn(Arrays.asList(mockParentTask()));
        assertNotNull(taskServiceImpl.findAllParentTasksByInput("test"));
    }

    @Test
    public void findAllParentTasksByInputDefault() {
        when(parentTaskRepository.findAll()).thenReturn(Arrays.asList(mockParentTask()));
        assertNotNull(taskServiceImpl.findAllParentTasksByInput("default"));
    }

    @Test
    public void findTasksByProjectId() {
        when(taskRepository.findTasksByProjectId(anyLong())).thenReturn(Arrays.asList(mockTask()));
        when(taskRepository.findUserByTaskId(anyLong())).thenReturn(Optional.of(mockUser(1)));
        assertNotNull(taskServiceImpl.findTasksByProjectId(anyLong()));
    }

    private ParentTask mockParentTask() {
        final ParentTask parentTask = new ParentTask();
        parentTask.setId(1);
        parentTask.setParentTaskName("parent");
        return parentTask;
    }

    private Project mockProject() {
        final Project project = new Project();
        project.setProjectId(0);
        project.setProjectTitle("Test");
        project.setPriority(1);
        project.setUserId(1);
        project.setStartDate(LocalDate.of(2000, 10, 10));
        project.setEndDate(LocalDate.of(2000, 10, 20));
        return project;
    }

    private User mockUser(long id) {
        final User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmployeeId(123l);
        user.setUserId(id);
        return user;
    }

    private Task mockTask() {
        Task task = new Task();
        task.setParentTaskId(1);
        task.setProjectId(1);
        task.setUserId(1);
        return task;
    }
}
