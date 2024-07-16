package com.ecajas.service;

import com.ecajas.dto.ProjectRequest;
import com.ecajas.dto.ProjectResponse;

import java.util.List;

public interface ProjectService {

    ProjectResponse createProject(ProjectRequest projectRequest);
    ProjectResponse updateProject(Long id, ProjectRequest projectRequest);
    List<ProjectResponse> getAllProjects();
    ProjectResponse getProjectById(Long id);
    void deleteProjectById(Long id);
    List<ProjectResponse> findProjectsByNameContaining(String name);
}
