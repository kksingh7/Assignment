package com.assignment.taskManager.service;

import com.assignment.taskManager.dao.TaskUpdateLogDao;
import com.assignment.taskManager.modals.TaskUpdateLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskUpdateLogServiceImpl implements TaskUpdateLogService {
    Logger logger = LoggerFactory.getLogger(TaskUpdateLogServiceImpl.class);
    @Autowired
    TaskUpdateLogDao taskUpdateLogDao;
    public List<TaskUpdateLog> getTaskUpdateLogByTaskId(int taskId) {
        logger.info("Inside method getTaskUpdateLogByTaskId, fetching all update logs for task id: " + taskId);

        return taskUpdateLogDao.findAllByTaskId(taskId);
    }
}
