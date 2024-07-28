package com.ecajas.service;
import com.ecajas.dto.*;
import com.ecajas.model.TaskPriority;
import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);
    TaskResponse updateTask(Long id, TaskRequest  taskRequest);
    List<TaskResponse> getAllTasks();
    TaskResponse getTaskById(Long id);
    void deleteTaskById(Long id);
    List<TaskResponse> findTasksByPriority(TaskPriority priority);
    List<TaskResponse> findTasksByProjectId(Long projectId);
    List<TaskResponse> findTasksByProjectName(String projectName);

    List<TaskResponse> findTasksByDateRange(LocalDate startDate, LocalDate endDate);
    void markTaskAsCompleted(Long id);
    List<TaskReport> getTaskReport();
}
