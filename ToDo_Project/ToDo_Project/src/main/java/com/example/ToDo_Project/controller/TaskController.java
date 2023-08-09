package com.example.ToDo_Project.controller;

import com.example.ToDo_Project.dto.TasksDTO;
import com.example.ToDo_Project.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TaskController {
    @Autowired
    private TasksService tasksService;
    @PostMapping("/task")
    public ResponseEntity<TasksDTO> createTasks(@Valid @RequestBody TasksDTO tasksDTO) {
        TasksDTO createdTask = tasksService.createTask(tasksDTO);
        return ResponseEntity.ok(createdTask);
    }
    @GetMapping("/allTasks")
    public ResponseEntity<List<TasksDTO>> getAllTasks() {
        List<TasksDTO> tasks = tasksService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    @PutMapping("/tasks/{id}")
    public ResponseEntity<TasksDTO> updateTasks(@PathVariable Long id, @Valid @RequestBody TasksDTO tasksDTO) {
        TasksDTO updatedTasks = tasksService.updateTask(id, tasksDTO);
        return ResponseEntity.ok(updatedTasks);
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTasks(@PathVariable Long id) {
        tasksService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
