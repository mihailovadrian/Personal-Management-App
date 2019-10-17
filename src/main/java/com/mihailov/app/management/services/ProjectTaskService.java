package com.mihailov.app.management.services;

import com.mihailov.app.management.domain.Backlog;
import com.mihailov.app.management.domain.ProjectEntity;
import com.mihailov.app.management.domain.ProjectTask;
import com.mihailov.app.management.exceptions.ProjectNotFoundException;
import com.mihailov.app.management.repositories.BacklogRepository;
import com.mihailov.app.management.repositories.ProjectRepository;
import com.mihailov.app.management.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            projectTask.setBacklog(backlog);
            Integer backlogSequence = backlog.getPTSequence();
            backlogSequence++;

            backlog.setPTSequence(backlogSequence);
            projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
            projectTask.setProjectIdentifer(projectIdentifier);

            if (projectTask.getPriority() == null)
                projectTask.setPriority(3);

            if (projectTask.getStatus() == "" || projectTask.getStatus() == null)
                projectTask.setStatus("TO_DO");

            return projectTaskRepository.save(projectTask);
        } catch (Exception e) {
            //Not the best way
            //Mysql server errors will be catch here too
            throw new ProjectNotFoundException("Project Not found");
        }
    }

    public Iterable<ProjectTask> findBacklogById(String id) {
        ProjectEntity projectEntity = projectRepository.findByProjectIdentifier(id);
        if (projectEntity == null) throw new ProjectNotFoundException("project with the ID:" + id + " not found");
        return projectTaskRepository.findByProjectIdentiferOrderByPriority(id);
    }

    //sequence
    public ProjectTask findPTByProjectSQ(String BacklogId,String SQ) {
        return projectTaskRepository.findByProjectSequence(SQ);
    }
}
