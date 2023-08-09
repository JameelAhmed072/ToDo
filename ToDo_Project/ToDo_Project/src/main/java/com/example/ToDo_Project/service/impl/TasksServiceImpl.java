package com.example.ToDo_Project.service.impl;

import com.example.ToDo_Project.domain.Tasks;
import com.example.ToDo_Project.dto.TasksDTO;
import com.example.ToDo_Project.exception.ResourceNotFoundException;
import com.example.ToDo_Project.repository.TaskRepository;
import com.example.ToDo_Project.service.TasksService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasksServiceImpl implements TasksService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public TasksDTO createTask(TasksDTO tasksDTO) {
        Tasks tasks = modelMapper.map(tasksDTO, Tasks.class);
        Tasks savedTasks =taskRepository.save(tasks);
        return modelMapper.map(savedTasks,TasksDTO.class);
    }
    @Override
    public List<TasksDTO> getAllTasks() {
        List<Tasks> tasks = taskRepository.findAll();
        return tasks.stream().map(task -> modelMapper.map(task, TasksDTO.class)).collect(Collectors.toList());
    }
    @Override
    public TasksDTO updateTask(Long id, TasksDTO tasksDTO) {
        Tasks tasks = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
        tasks.setMyTasks(tasksDTO.getMyTasks());
        Tasks updateTask =taskRepository.save(tasks);
        return modelMapper.map(updateTask,TasksDTO.class);
    }
    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with ID: " + id);
            }
        taskRepository.deleteById(id);
    }
}
