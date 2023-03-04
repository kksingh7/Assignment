package com.assignment.taskManager.service;

import com.assignment.taskManager.modals.TaskUpdateLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskUpdateLogService {

    // To get all available update logs corresponding to a particular task
    public List<TaskUpdateLog> getTaskUpdateLogByTaskId(int taskId);
}
