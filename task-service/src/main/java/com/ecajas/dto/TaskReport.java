package com.ecajas.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Este Dto sirve para mapear el resultado del Store Procedure creado en TaskRepository.
 * getTaskReport();
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskReport {

    private String projectName;
    private int completedTasks;
    private int incompleteTasks;
}
