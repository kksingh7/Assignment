package com.assignment.taskManager.dao;

import com.assignment.taskManager.modals.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDao extends JpaRepository<Task, Integer> {
}
