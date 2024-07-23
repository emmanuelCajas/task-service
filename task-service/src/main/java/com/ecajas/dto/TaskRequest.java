package com.ecajas.dto;

import com.ecajas.model.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskPriority priority;
    private Long projectId;

}
