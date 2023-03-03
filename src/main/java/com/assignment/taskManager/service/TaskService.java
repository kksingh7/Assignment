package com.assignment.taskManager.service;

import com.assignment.taskManager.modals.Task;
import com.assignment.taskManager.modals.TaskStatus;

import java.util.List;

public interface TaskService {
    public Task addTask(Task task);

    public Task getTaskById (int id);

    public List<Task> getAllTasks ();

    public Task updateTaskById (int id, Task newTask);

    public String deleteTaskById (int id);
}
