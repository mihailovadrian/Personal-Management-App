package com.mihailov.app.management.services;

import com.mihailov.app.management.domain.ProjectEntity;
import com.mihailov.app.management.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectEntity saveOrUpdate(ProjectEntity project) {
        return projectRepository.save(project);
    }
}
