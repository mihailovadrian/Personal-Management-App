package com.mihailov.app.management.repositories;

import com.mihailov.app.management.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long> {

    Backlog findByProjectIdentifier(String projectIdentifier);
}
