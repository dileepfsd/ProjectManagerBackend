package com.pm.repository;

import com.pm.entity.Task;
import com.pm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.project.projectId=:id")
    List<Task> findTasksByProjectId(Long id);

    @Query("select u from User u where u.task.taskId=:id")
    Optional<User> findUserByTaskId(Long id);
}

