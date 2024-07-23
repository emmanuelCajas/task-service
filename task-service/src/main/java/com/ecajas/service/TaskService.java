package com.ecajas.service;

import com.ecajas.dto.TaskRequest;
import com.ecajas.dto.TaskResponse;
import com.ecajas.dto.UserRequest;
import com.ecajas.dto.UserResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);
    TaskResponse updateTask(Long id, TaskRequest  taskRequest);
    List<TaskResponse> getAllTasks();
    TaskResponse getTaskById(Long id);
    void deleteTaskById(Long id);
    List<TaskResponse> findTasksByProjectId(Long projectId);
    List<TaskResponse> findTasksByProjectName(String projectName);
}
