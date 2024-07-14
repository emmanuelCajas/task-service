package com.ecajas.mapper;

import com.ecajas.dto.ProjectRequest;
import com.ecajas.dto.ProjectResponse;
import com.ecajas.model.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectMapper {

    private final ModelMapper modelMapper;

    public ProjectMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    public Project ProjectRequestToProject(ProjectRequest projectRequest){

        Project project = modelMapper.map(projectRequest,Project.class);
        return project;
    }

    public ProjectResponse ProjectToProjectResponse(Project project){
        ProjectResponse projectResponse = modelMapper.map(project,ProjectResponse.class);
        return projectResponse;
    }

    public List<ProjectResponse> projectListToprojectResponseList(List<Project> projectList){
        /*
        return projects.stream()
                .map(this::projectToProjectResponse)
                .toList();
        */

        List<ProjectResponse> projectResponses = projectList.stream()
                .map(e->this.ProjectToProjectResponse(e))
                .toList() ;
        return projectResponses;
    }



}
