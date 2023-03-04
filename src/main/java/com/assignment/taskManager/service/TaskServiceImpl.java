package com.assignment.taskManager.service;

import com.assignment.taskManager.dao.TaskDao;
import com.assignment.taskManager.dao.TaskUpdateLogDao;
import com.assignment.taskManager.helper.TaskServiceHelper;
import com.assignment.taskManager.modals.Task;
import com.assignment.taskManager.modals.TaskField;
import com.assignment.taskManager.modals.TaskStatus;
import com.assignment.taskManager.modals.TaskUpdateLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    @Autowired
    TaskDao taskDao;
    @Autowired
    TaskUpdateLogDao taskUpdateLogDao;
    @Autowired
    TaskServiceHelper taskServiceHelper;
    public Task addTask (Task task) {
        logger.info("Inside method addTask, method starts...");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (task.getEta()==null || task.getEta().isEmpty()) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.WEEK_OF_YEAR, 1);
            task.setEta(dateFormat.format(cal.getTime()));
        }
        if (Objects.isNull(task.getStatus())) {
            task.setStatus(TaskStatus.PENDING);
        }

        logger.info("Inside method addTask, adding new task in database.");
        return taskDao.save(task);
    }

    public Task getTaskById (int id) {
        logger.info("Inside method getTaskById, fetching task for id: " + id);
        return taskDao.findById(id).orElseThrow();
    }

    public List<Task> getAllTasks () {
        logger.info("Inside method getAllTasks, fetching all tasks...");
        return taskDao.findAll();
    }

    public Task updateTaskById (int id, Task newTask) {
        logger.info("Inside method updateTaskById, method starts for task id: " + id);

        Task task = taskDao.findById(id).orElse(null);

        if (task==null) {
            logger.info("Inside method updateTaskById, task not found hence throwing exception for task id: " + id);
            throw new RuntimeException("Task not found");
        } else {
            TaskUpdateLog taskUpdateLog = new TaskUpdateLog();

            logger.info("Inside method updateTaskById, creating update log for task id: " + id);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            taskUpdateLog.setUpdated_timestamp(simpleDateFormat.format(new Date()));
            String updatedFields = taskServiceHelper.getUpdatedFieldsForTask(task, newTask);
            taskUpdateLog.setUpdated_fields(updatedFields);
            taskUpdateLog.setTask(task);
            taskUpdateLogDao.save(taskUpdateLog);

            logger.info("Inside method updateTaskById, update log created saving task for task id: " + id);
            return taskDao.save(task);
        }
    }

    public String deleteTaskById (int id) {
        logger.info("Inside method deleteTaskById, deleting task for id: " + id);

        if (taskDao.findById(id).isEmpty()) {
            return "Task does not Exist";
        } else {
            taskDao.deleteById(id);
        }

        return "Task deleted successfully";
    }

}
