package com.mihailov.app.management.web;

import com.mihailov.app.management.domain.ProjectTask;
import com.mihailov.app.management.services.ProjectTaskService;
import com.mihailov.app.management.services.ValidationErrorMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/backlog")
@CrossOrigin
public class ProjectTaskController {
    @Autowired
    private ProjectTaskService projectTaskService;
    @Autowired
    private ValidationErrorMapService errorMapService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String backlog_id) {
        ResponseEntity<?> errorMap = errorMapService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);
        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id) {
        return projectTaskService.findBacklogById(backlog_id);
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id) {
        ProjectTask projectTask = projectTaskService.findPTByProjectSQ(backlog_id, pt_id);
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @PatchMapping("/{backlogId}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String backlogId, @PathVariable String pt_id) {
        ResponseEntity<?> errorMap = errorMapService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        ProjectTask task = projectTaskService.updateByProjectSq(projectTask, backlogId, pt_id);
        return new ResponseEntity<ProjectTask>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{backlogId}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlogId, @PathVariable String pt_id) {
        projectTaskService.deletePTByProjectSq(backlogId, pt_id);
        return new ResponseEntity<String>("Project Task with id: " + pt_id + " was deleted.", HttpStatus.OK);
    }
}
