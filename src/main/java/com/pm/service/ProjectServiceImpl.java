package com.pm.service;

import com.pm.entity.Project;
import com.pm.entity.Task;
import com.pm.entity.User;
import com.pm.repository.ProjectRepository;
import com.pm.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectServiceImpl implements IProjectService {

    @Resource
    private ProjectRepository projectRepository;
    @Resource
    private UserRepository userRepository;

    public Project createProject(Project project) {
        Optional<User> userOpt = userRepository.findById(project.getUserId());
        User user = null;
        if (userOpt.isPresent()) {
            user = userOpt.get();
            //project.addUser(user);
        }
        projectRepository.save(project);
        return project;
    }

    public List<Project> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        setCountOfTask(projects);
        return projects;
    }

    public List<Project> findAllProjectByInput(String input) {
        if ("default".equals(input)) {
            return findAllProjects();
        } else if (!"undefined".equals(input)) {
            List<Project> projects = projectRepository.findByProjectTitleContaining(input);
            setCountOfTask(projects);
            return projects;
        }
        return new ArrayList<>();
    }

    public Project findProjectById(Long id) {
        Optional<Project> optProject = projectRepository.findById(id);
        if (optProject.isPresent()) {
            return optProject.get();
        }
        return new Project();
    }

    public Project deleteProject(Long id) {
        final Optional<Project> optProject = projectRepository.findById(id);
        optProject.ifPresent(project -> {
           /* for (User user : project.getUsers()) {
                user.setProject(null);
                user.setTask(null);
            }
            for (Task task : project.getTasks()) {
                task.setProject(null);
            }
            project.setUsers(null);
            project.setTasks(null);*/
            projectRepository.delete(project);
        });
        return new Project();
    }

    private void setCountOfTask(List<Project> projects) {
        for (Project project : projects) {
/*            List<Task> taskDtos = project.getTaskDtos();
            if (taskDtos != null && !taskDtos.isEmpty()) {
                projectDto.setTotalNoOfTasks(taskDtos.size());
                int noOfCompletedTasks = 0;
                for (TaskDto taskDto : taskDtos) {
                    if ("COMPLETE".equals(taskDto.getStatus())) {
                        noOfCompletedTasks = noOfCompletedTasks + 1;
                    }
                    projectDto.setTotalNoOfCompletedTasks(noOfCompletedTasks);
                }
            }*/
        }
    }
}
