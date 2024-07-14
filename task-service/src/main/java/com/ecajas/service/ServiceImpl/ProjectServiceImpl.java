package com.ecajas.service.ServiceImpl;

import com.ecajas.dto.ProjectRequest;
import com.ecajas.dto.ProjectResponse;
import com.ecajas.mapper.ProjectMapper;
import com.ecajas.model.Project;
import com.ecajas.repository.ProjectRepository;
import com.ecajas.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest) {
        Project project = projectMapper.ProjectRequestToProject(projectRequest);
        projectRepository.save(project);
        return projectMapper.ProjectToProjectResponse(project);
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectMapper.projectListToprojectResponseList(projects);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest) {
        Project project = projectMapper.ProjectRequestToProject(projectRequest);
        project.setId(id);
        projectRepository.save(project);
        return projectMapper.ProjectToProjectResponse(project);
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        return projectMapper.ProjectToProjectResponse(project);
    }

    @Override
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }
}
