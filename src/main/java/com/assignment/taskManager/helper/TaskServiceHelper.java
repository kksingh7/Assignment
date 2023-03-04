package com.assignment.taskManager.helper;

import com.assignment.taskManager.modals.Task;
import org.springframework.stereotype.Component;

@Component
public interface TaskServiceHelper {

    // Helper method to populate updated fields.
    public String getUpdatedFieldsForTask(Task oldTask, Task newTask);
}
