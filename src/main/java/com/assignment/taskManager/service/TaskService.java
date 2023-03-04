package com.assignment.taskManager.service;

import com.assignment.taskManager.modals.Task;
import com.assignment.taskManager.modals.TaskStatus;

import java.util.List;

public interface TaskService {

    // To add new task
    public Task addTask(Task task);

    // To get existing task by id
    public Task getTaskById (int id);

    // To get all available tasks
    public List<Task> getAllTasks ();

    // To update existing task by id
    public Task updateTaskById (int id, Task newTask);

    // To delete existing task by id
    public String deleteTaskById (int id);
}
