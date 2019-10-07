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
}
