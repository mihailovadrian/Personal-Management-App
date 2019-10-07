package com.mihailov.app.management.web;

import com.mihailov.app.management.domain.ProjectEntity;
import com.mihailov.app.management.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody ProjectEntity project, BindingResult result) {
        if (result.hasErrors())
            return new ResponseEntity<String>("Invalid Project obj", HttpStatus.BAD_REQUEST);
        project = projectService.saveOrUpdate(project);
        return new ResponseEntity<ProjectEntity>(project, HttpStatus.CREATED);
    }
}
