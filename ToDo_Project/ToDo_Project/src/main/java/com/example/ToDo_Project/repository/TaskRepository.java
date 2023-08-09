package com.example.ToDo_Project.repository;

import com.example.ToDo_Project.domain.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks,Long> {
}
