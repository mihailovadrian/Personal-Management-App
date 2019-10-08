package com.mihailov.app.management.web;

import com.mihailov.app.management.domain.ProjectEntity;
import com.mihailov.app.management.services.ProjectService;
import com.mihailov.app.management.services.ValidationErrorMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/project")
@CrossOrigin
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ValidationErrorMapService errorMapService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody ProjectEntity project, BindingResult result) {
        ResponseEntity<?> errorResponseEntity = errorMapService.MapValidationService(result);
        if (errorResponseEntity != null) return errorResponseEntity;

        ProjectEntity projectSaved = projectService.saveOrUpdate(project);
        return new ResponseEntity<ProjectEntity>(projectSaved, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        ProjectEntity project = projectService.findByProjectId(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<ProjectEntity> getAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProject(projectId);
        return  new ResponseEntity<String>("Project with id"+projectId+"was deleted",HttpStatus.OK);
    }
}
