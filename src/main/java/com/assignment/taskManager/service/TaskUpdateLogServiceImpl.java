package com.assignment.taskManager.service;

import com.assignment.taskManager.dao.TaskUpdateLogDao;
import com.assignment.taskManager.modals.TaskUpdateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskUpdateLogServiceImpl implements TaskUpdateLogService {
    @Autowired
    TaskUpdateLogDao taskUpdateLogDao;
    public List<TaskUpdateLog> getTaskUpdateLogByTaskId(int taskId) {
        return taskUpdateLogDao.findAllByTaskId(taskId);
    }
}
