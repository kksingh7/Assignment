package com.assignment.taskManager.controller;

import com.assignment.taskManager.modals.TaskUpdateLog;
import com.assignment.taskManager.service.TaskUpdateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class TaskUpdateLogController {
    @Autowired
    TaskUpdateLogService taskUpdateLogService;
    @GetMapping("/getTaskUpdateLog/{taskId}")
    //@Cacheable(value = "taskUpdateLog",key = "#id")
    public ResponseEntity<List<TaskUpdateLog>> getTaskUpdateLogByTaskId(@PathVariable int taskId) {
        try {
            List<TaskUpdateLog> taskUpdateLogList = taskUpdateLogService.getTaskUpdateLogByTaskId(taskId);
            return new ResponseEntity<>(taskUpdateLogList, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
