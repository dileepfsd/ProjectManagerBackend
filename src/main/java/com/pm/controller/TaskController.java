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
    public Task createTask(@RequestBody Task task) {
        return taskServiceImpl.createTask(task);
    }

    @PostMapping("/update")
    public Task updateTask(@RequestBody Task task) {
        return taskServiceImpl.updateTask(task);
    }

    @PostMapping("/updateTaskStatus")
    public Task updateTaskStatus(@RequestBody Task task) {
        return taskServiceImpl.updateTaskStatus(task);
    }

    @PostMapping("/createParentTask")
    public ParentTask createParentTask(@RequestBody ParentTask parentTask) {
        return taskServiceImpl.createParentTask(parentTask);
    }

    @GetMapping("/findAllParentTask")
    public List<ParentTask> findAllParentTask() {
        return taskServiceImpl.findAllParentTasks();
    }

    @GetMapping("/findAllParentTasksByInput/{input}")
    public List<ParentTask> findAllParentTasksByInput(@PathVariable("input") String input) {
        return taskServiceImpl.findAllParentTasksByInput(input);
    }

    @GetMapping("/findTasksByProjectId/{input}")
    public List<Task> findTasksByProjectId(@PathVariable("input") Long id) {
        return taskServiceImpl.findTasksByProjectId(id);
    }
}
