package com.assignment.taskManager.controller;

import com.assignment.taskManager.modals.Task;
import com.assignment.taskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<Task>(task, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTask/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        try {
            Task task = taskService.getTaskById(id);
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            List<Task> tasks = taskService.getAllTasks();
            return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping ("/updateTask/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable int id, @RequestBody Task newTask) {
        try {
            Task task = taskService.updateTaskById(id, newTask);
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable int id) {
        try {
            String result = taskService.deleteTaskById(id);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
