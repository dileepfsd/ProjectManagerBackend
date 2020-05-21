package com.pm.service;

import com.pm.entity.Project;
import com.pm.entity.User;
import com.pm.repository.ProjectRepository;
import com.pm.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        if (project != null) {
            projectRepository.save(project);
            Optional<User> optUser = userRepository.findById(project.getUserId());
            if (optUser.isPresent()) {
                User user = optUser.get();
                user.setProject(project);
                userRepository.save(user);
                project.setManagerName(user.getFirstName());
            }
        }
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
            final Project project = optProject.get();
            List<User> users = userRepository.findUserByProjectId(id);
            if (!users.isEmpty()) {
                project.setManagerName(users.get(0).getFirstName());
            }
            return project;
        }
        return null;
    }

    public Project deleteProject(Long id) {
        final Optional<Project> optProject = projectRepository.findById(id);
        optProject.ifPresent(project -> {
            List<User> users = userRepository.findUserByProjectId(id);
            for (User user : users) {
                user.setProject(null);
            }
            //    user.setTask(null);

   /*         for (Task task : project.getTasks()) {
                task.setProject(null);
            }*/
            projectRepository.delete(project);
        });
        return new Project();
    }

    private void setCountOfTask(List<Project> projects) {
        /*for (Project project : projects) {
            List<Task> taskDtos = project.getTaskDtos();
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

