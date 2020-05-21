package com.pm.controller;

import com.pm.entity.ParentTask;
import com.pm.entity.Task;
import com.pm.service.ITaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskController {

    @Resource
    private ITaskService taskServiceImpl;

    @PostMapping("/create")
    public Task createTask() {
        return taskServiceImpl.createTask(null);
    }

    @PostMapping("/update")
    public Task updateTask() {
        return taskServiceImpl.updateTask(null);
    }

    @PostMapping("/updateTaskStatus")
    public Task updateTaskStatus(@RequestBody Task taskDto) {
        return taskServiceImpl.updateTaskStatus(null);
    }


    @PostMapping("/createParent")
    public ParentTask createParentTask(@RequestBody ParentTask parentTask) {
        return taskServiceImpl.createParentTask(null);
    }

    @GetMapping("/findAllParent")
    public List<ParentTask> findAllParentTasks() {
        return taskServiceImpl.findAllParentTasks();
    }

    @GetMapping("/findAllParentTasksByInput/{input}")
    public List<ParentTask> findAllParentTasksByInput(@PathVariable("input") String input) {
        return taskServiceImpl.findAllParentTasksByInput(null);
    }
}
