package com.mihailov.app.management.services;

import com.mihailov.app.management.domain.ProjectEntity;
import com.mihailov.app.management.exceptions.ProjectIdException;
import com.mihailov.app.management.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectEntity saveOrUpdate(ProjectEntity project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception ex) {
            throw new ProjectIdException("Project ID " + project.getProjectIdentifier() + "  not good.");
        }
    }

    public ProjectEntity findByProjectId(String projectId) {
        ProjectEntity project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null)
            throw new ProjectIdException("Project ID " + projectId + "not found.");
        return project;
    }

    public Iterable<ProjectEntity> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProject(String projectId) {
        ProjectEntity projectEntity = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (projectEntity == null) throw new ProjectIdException("Project doesnt exist :" + projectId);
        projectRepository.delete(projectEntity);
    }
}
