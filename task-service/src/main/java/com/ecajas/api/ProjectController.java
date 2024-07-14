package com.ecajas.api;


import com.ecajas.dto.ProjectRequest;
import com.ecajas.dto.ProjectResponse;
import com.ecajas.model.Project;
import com.ecajas.service.ServiceImpl.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectServiceImpl projectService;


    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest projectRequest) {
        return new ResponseEntity<>(projectService.createProject(projectRequest),HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> upDateProject(@PathVariable Long id, @RequestBody ProjectRequest projectRequest){
        ProjectResponse projectResponse = projectService.updateProject(id,projectRequest);
        return new ResponseEntity<>(projectResponse,HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id){
        projectService.deleteProjectById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        ProjectResponse project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
}
