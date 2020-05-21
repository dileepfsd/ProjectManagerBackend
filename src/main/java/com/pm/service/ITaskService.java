package com.pm.service;

import com.pm.entity.ParentTask;
import com.pm.entity.Task;

import java.util.List;

public interface ITaskService {
    Task createTask(Task task);

    Task updateTask(Task task);

    Task updateTaskStatus(Task task);

    ParentTask createParentTask(ParentTask parentTask);

    List<ParentTask> findAllParentTasks();

    List<ParentTask> findAllParentTasksByInput(String input);

}
