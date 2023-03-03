package com.assignment.taskManager.dao;

import com.assignment.taskManager.modals.TaskUpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskUpdateLogDao extends JpaRepository<TaskUpdateLog, Integer>  {
    List<TaskUpdateLog> findAllByTaskId(int taskId);
}
