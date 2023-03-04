package com.assignment.taskManager.helper;

import com.assignment.taskManager.modals.Task;
import com.assignment.taskManager.modals.TaskField;
import com.assignment.taskManager.service.TaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@Component
public class TaskServiceHelperImpl implements TaskServiceHelper {
    Logger logger = LoggerFactory.getLogger(TaskServiceHelperImpl.class);
    private LinkedHashMap<String, String> populateField (String fieldName, String oldValue, String newValue) {
        LinkedHashMap<String, String> updatedField = new LinkedHashMap<>();
        updatedField.put(String.valueOf(TaskField.UPDATED_FIELD), fieldName);
        updatedField.put(String.valueOf(TaskField.OLD_VALUE), oldValue);
        updatedField.put(String.valueOf(TaskField.NEW_VALUE), newValue);

        return updatedField;
    }
    public String getUpdatedFieldsForTask(Task oldTask, Task newTask) {
        logger.info("Inside method getUpdatedFieldsForTask, method starts");
        List<LinkedHashMap<String, String>> updatedFields = new ArrayList<>();

        if (!Objects.isNull(newTask.getTitle())) {
            updatedFields.add(populateField(String.valueOf(TaskField.TITLE), oldTask.getTitle(), newTask.getTitle()));
            oldTask.setTitle(newTask.getTitle());
        }

        if (!Objects.isNull(newTask.getEta())) {
            updatedFields.add(populateField(String.valueOf(TaskField.ETA), oldTask.getEta(), newTask.getEta()));
            oldTask.setEta(newTask.getEta());
        }

        if (!Objects.isNull(newTask.getStatus())) {
            updatedFields.add(populateField(String.valueOf(TaskField.STATUS), String.valueOf(oldTask.getStatus()), String.valueOf(newTask.getStatus())));
            oldTask.setStatus(newTask.getStatus());
        }

        logger.info("Inside method getUpdatedFieldsForTask, updated fields to be stored are: " + updatedFields);
        return String.valueOf(updatedFields);
    }
}
