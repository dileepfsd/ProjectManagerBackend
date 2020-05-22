package com.pm.service;

import com.pm.entity.Project;

import java.util.List;

public interface IProjectService {
    Project createProject(Project projectDto);

    List<Project> findAllProjects();

    List<Project> findAllProjectsWithTaskStatus();

    List<Project> findAllProjectByInput(String input);

    List<Project> findAllProjectByInputWithTask(String input);

    Project findProjectById(Long id);

    Project deleteProject(Long id);
}
