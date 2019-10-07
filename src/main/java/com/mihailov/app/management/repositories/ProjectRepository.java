package com.mihailov.app.management.repositories;

import com.mihailov.app.management.domain.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity,Long> {
    }
