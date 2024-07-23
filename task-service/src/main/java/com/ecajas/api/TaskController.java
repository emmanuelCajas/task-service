package com.ecajas.api;

import com.ecajas.dto.TaskRequest;
import com.ecajas.dto.TaskResponse;
import com.ecajas.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.createTask(taskRequest);
        return new ResponseEntity<>(taskResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.updateTask(id, taskRequest);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        TaskResponse taskResponse = taskService.getTaskById(id);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(@RequestParam Optional<Long> projectId,
                                                          @RequestParam Optional<String> projectName) {
        List<TaskResponse> tasks;
        if (projectId.isPresent()) {
            tasks = taskService.findTasksByProjectId(projectId.get());
        } else if (projectName.isPresent()) {
            tasks = taskService.findTasksByProjectName(projectName.get());
        } else {
            tasks = taskService.getAllTasks();
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
