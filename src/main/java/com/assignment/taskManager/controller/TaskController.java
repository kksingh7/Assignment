package com.assignment.taskManager.controller;

import com.assignment.taskManager.controller.Serializer.TaskResponseEntity;
import com.assignment.taskManager.helper.TaskServiceHelperImpl;
import com.assignment.taskManager.modals.Task;
import com.assignment.taskManager.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        try {
            taskService.addTask(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(task, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTask/{id}")
    //@Cacheable(value = "task",key = "#id")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        try {
            Task task = taskService.getTaskById(id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTasks")
    //@Cacheable("task")
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            List<Task> tasks = taskService.getAllTasks();
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping ("/updateTask/{id}")
    //@CachePut(value = "task",key = "#id")
    public ResponseEntity<Task> updateTaskById(@PathVariable int id, @RequestBody Task newTask) {
        try {
            Task task = taskService.updateTaskById(id, newTask);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteTask/{id}")
    //@CacheEvict(value = "task",key = "#id",allEntries = false)
    public ResponseEntity<String> deleteTaskById(@PathVariable int id) {
        try {
            String result = taskService.deleteTaskById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
