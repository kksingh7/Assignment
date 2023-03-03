package com.assignment.taskManager.service;

import com.assignment.taskManager.dao.TaskDao;
import com.assignment.taskManager.dao.TaskUpdateLogDao;
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

    private LinkedHashMap<String, String> populateField (String fieldName, String oldValue, String newValue) {
        LinkedHashMap<String, String> updatedField = new LinkedHashMap<>();
        updatedField.put(String.valueOf(TaskField.UPDATED_FIELD), fieldName);
        updatedField.put(String.valueOf(TaskField.OLD_VALUE), oldValue);
        updatedField.put(String.valueOf(TaskField.NEW_VALUE), newValue);

        return updatedField;
    }
    public Task updateTaskById (int id, Task newTask) {
        Task task = taskDao.findById(id).orElse(null);

        if (task==null) {
            throw new RuntimeException("Task not found");
        } else {
            TaskUpdateLog taskUpdateLog = new TaskUpdateLog();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            taskUpdateLog.setUpdated_timestamp(simpleDateFormat.format(new Date()));

            List<LinkedHashMap<String, String>> updatedFields = new ArrayList<>();

            if (!Objects.isNull(newTask.getTitle())) {
                updatedFields.add(populateField(String.valueOf(TaskField.TITLE), task.getTitle(), newTask.getTitle()));
                task.setTitle(newTask.getTitle());
            }

            if (!Objects.isNull(newTask.getEta())) {
                updatedFields.add(populateField(String.valueOf(TaskField.ETA), task.getEta(), newTask.getEta()));
                task.setEta(newTask.getEta());
            }

            if (!Objects.isNull(task.getStatus())) {
                updatedFields.add(populateField(String.valueOf(TaskField.STATUS), String.valueOf(task.getStatus()), String.valueOf(newTask.getStatus())));
                task.setStatus(newTask.getStatus());
            }

            taskUpdateLog.setUpdated_fields(String.valueOf(updatedFields));
            taskUpdateLog.setTask(task);
            taskUpdateLogDao.save(taskUpdateLog);

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
