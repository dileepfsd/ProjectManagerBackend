package com.pm.service;

import com.pm.entity.Project;
import com.pm.entity.Task;
import com.pm.entity.User;
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
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTest {

    @InjectMocks
    private ProjectServiceImpl projectServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void createProject() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser(1)));
        when(projectRepository.save(any())).thenReturn(mockProject());
        when(userRepository.save(any())).thenReturn(mockUser(1));
        projectServiceImpl.createProject(mockProject());
        verify(projectRepository, times(1)).save(any());
    }

    @Test
    public void findAllProjects() {
        when(projectRepository.findAll()).thenReturn(Arrays.asList(mockProject()));
        assertNotNull(projectServiceImpl.findAllProjects());
    }

    @Test
    public void findAllProjectsWithTaskStatus() {
        when(projectRepository.findAll()).thenReturn(Arrays.asList(mockProject()));
        when(taskRepository.findTaskStatusByProjectId(anyLong())).thenReturn(null);
        assertNotNull(projectServiceImpl.findAllProjectsWithTaskStatus());
    }

    @Test
    public void findAllProjectsWithTaskStatusComplete() {
        when(projectRepository.findAll()).thenReturn(Arrays.asList(mockProject()));
        when(taskRepository.findTaskStatusByProjectId(anyLong())).thenReturn(Arrays.asList("start", "complete"));
        assertNotNull(projectServiceImpl.findAllProjectsWithTaskStatus());
    }

    @Test
    public void findAllProjectByInputDefault() {
        when(projectRepository.findAll()).thenReturn(Arrays.asList(mockProject()));
        assertNotNull(projectServiceImpl.findAllProjectByInput("default"));
    }

    @Test
    public void findAllProjectByInputValue() {
        when(projectRepository.findByProjectTitleContaining(any())).thenReturn(Arrays.asList(mockProject()));
        assertNotNull(projectServiceImpl.findAllProjectByInput("test"));
    }

    @Test
    public void findAllProjectByInputUndefined() {
        assertNotNull(projectServiceImpl.findAllProjectByInputWithTask("undefined"));
    }

    @Test
    public void findAllProjectByInputWithTaskValue() {
        when(projectRepository.findByProjectTitleContaining(any())).thenReturn(Arrays.asList(mockProject()));
        when(taskRepository.findTaskStatusByProjectId(anyLong())).thenReturn(Arrays.asList("start", "complete"));
        assertNotNull(projectServiceImpl.findAllProjectByInputWithTask("test"));
    }

    @Test
    public void findAllProjectByInputWithTaskDefaultValue() {
        when(projectRepository.findAll()).thenReturn(Arrays.asList(mockProject()));
        when(taskRepository.findTaskStatusByProjectId(anyLong())).thenReturn(Arrays.asList("start", "complete"));
        assertNotNull(projectServiceImpl.findAllProjectByInputWithTask("default"));
    }

    @Test
    public void findProjectById() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(mockProject()));
        when(userRepository.findUserByProjectId(anyLong())).thenReturn(Arrays.asList(mockUser(1)));
        assertNotNull(projectServiceImpl.findProjectById(anyLong()));
    }

    @Test
    public void findProjectByIdEmpty() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertNull(projectServiceImpl.findProjectById(anyLong()));
    }

    @Test
    public void deleteProject() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertNull(projectServiceImpl.deleteProject(10L));
    }

    @Test
    public void deleteProjectNotEmpty() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(mockProject()));
        User user = mockUser(1);
        Task task = new Task();
        Project project = new Project();
        user.setTask(task);
        user.setProject(project);
        when(userRepository.findUserByProjectId(anyLong())).thenReturn(Arrays.asList(user));
        task.setProject(project);
        when(taskRepository.findTasksByProjectId(anyLong())).thenReturn(Arrays.asList(task));
        assertNull(projectServiceImpl.deleteProject(10L));
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
}
