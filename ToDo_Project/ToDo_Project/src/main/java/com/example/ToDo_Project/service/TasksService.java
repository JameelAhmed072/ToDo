package com.example.ToDo_Project.service;

import com.example.ToDo_Project.dto.TasksDTO;

import java.util.List;

public interface TasksService {

    TasksDTO createTask(TasksDTO tasksDTO);

    List<TasksDTO> getAllTasks();

    TasksDTO updateTask(Long id,TasksDTO tasksDTO);

    void deleteTask(Long id);

}
