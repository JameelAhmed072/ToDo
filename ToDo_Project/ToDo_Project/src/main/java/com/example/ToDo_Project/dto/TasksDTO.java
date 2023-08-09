package com.example.ToDo_Project.dto;


import lombok.*;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TasksDTO {
    private Long id;
    @NotNull
    private String myTasks;
}
