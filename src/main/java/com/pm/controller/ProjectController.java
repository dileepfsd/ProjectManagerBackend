package com.pm.controller;

import com.pm.entity.Project;
import com.pm.service.IProjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private IProjectService projectServiceImpl;

    @PostMapping("/create")
    public Project createProject(@RequestBody Project project) {
        return projectServiceImpl.createProject(project);
    }

    @GetMapping("/findAllProjects")
    public List<Project> findAllProjects() {
        return projectServiceImpl.findAllProjects();
    }

    @GetMapping("/findAllProjectByInput/{input}")
    public List<Project> findAllProjectByInput(@PathVariable("input") String input) {
        return projectServiceImpl.findAllProjectByInput(input);
    }

    @GetMapping("/findProjectById/{id}")
    public Project findProjectById(@PathVariable("id") Long id) {
        return projectServiceImpl.findProjectById(id);
    }

    @PostMapping("/delete")
    public Project deleteProject(@RequestBody Long id) {
        return projectServiceImpl.deleteProject(id);
    }
}
