package com.pm.controller;

import com.pm.entity.Project;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectController {

    @PostMapping("/create")
    public Project createProject(@RequestBody Project project) {
        return new Project();
    }

    @GetMapping("/findAllProjects")
    public List<Project> findAllProjects() {

        return Arrays.asList(new Project());
    }

    @GetMapping("/findAllProjectByInput/{input}")
    public List<Project> findAllProjectByInput(@PathVariable("input") String input) {
        return Arrays.asList(new Project());
    }

    @GetMapping("/findProjectById/{id}")
    public Project findProjectById(@PathVariable("id") Long id) {

        return new Project();
    }

    @PostMapping("/delete")
    public Project deleteProject(@RequestBody Long id) {
        return new Project();
    }
}
