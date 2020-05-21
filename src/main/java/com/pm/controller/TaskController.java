package com.pm.controller;

import com.pm.entity.ParentTask;
import com.pm.entity.Task;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskController {

    @PostMapping("/create")
    public Task createTask() {
        return new Task();
    }

    @PostMapping("/update")
    public Task updateTask() {
        return new Task();
    }

    @PostMapping("/updateTaskStatus")
    public Task updateTaskStatus(@RequestBody Task taskDto) {
        return new Task();
    }


    @PostMapping("/createParent")
    public ParentTask createParentTask(@RequestBody ParentTask parentTask) {
        return new ParentTask();
    }

    @GetMapping("/findAllParent")
    public List<ParentTask> findAllParentTasks() {
        return Arrays.asList(new ParentTask());
    }

    @GetMapping("/findAllParentTasksByInput/{input}")
    public List<ParentTask> findAllParentTasksByInput(@PathVariable("input") String input) {
        return Arrays.asList(new ParentTask());
    }
}
