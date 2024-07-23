package com.ecajas.service.ServiceImpl;

import com.ecajas.dto.TaskRequest;
import com.ecajas.dto.TaskResponse;
import com.ecajas.dto.UserResponse;
import com.ecajas.mapper.TaskMapper;
import com.ecajas.model.Project;
import com.ecajas.model.Task;
import com.ecajas.model.User;
import com.ecajas.repository.ProjectRepository;
import com.ecajas.repository.TaskRepository;
import com.ecajas.repository.UserRepository;
import com.ecajas.service.TaskService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskMapper.taskRequestToTask(taskRequest);

        Optional<Long> projectIdOptional = Optional.ofNullable(taskRequest.getProjectId());
        if (projectIdOptional.isPresent()) {
            Project project = projectRepository.findById(taskRequest.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            task.setProject(project);
        }

        task = taskRepository.save(task);
        return taskMapper.taskToTaskResponse(task);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Project project = projectRepository.findById(taskRequest.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Actualiza los campos de la tarea con los valores de request
        updateTaskFromRequest(task, taskRequest);
        task.setProject(project);
        task = taskRepository.save(task);
        return taskMapper.taskToTaskResponse(task);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(taskMapper::taskToTaskResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return taskMapper.taskToTaskResponse(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskResponse> findTasksByProjectId(Long projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        return tasks.stream()
                .map(taskMapper::taskToTaskResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> findTasksByProjectName(String projectName) {
        List<Task> tasks = taskRepository.findTasksByProjectName(projectName);
        return tasks.stream()
                .map(taskMapper::taskToTaskResponse)
                .collect(Collectors.toList());
    }

    public void updateTaskFromRequest(Task task, TaskRequest request) {
        // Aquí asignas los valores de request a los campos correspondientes de task
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setPriority(request.getPriority());
    }
}
