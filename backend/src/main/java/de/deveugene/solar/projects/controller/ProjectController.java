package de.deveugene.solar.projects.controller;

import de.deveugene.solar.projects.api.ProjectMapper;
import de.deveugene.solar.projects.api.ProjectRequest;
import de.deveugene.solar.projects.api.ProjectResponse;
import de.deveugene.solar.projects.domain.Project;
import de.deveugene.solar.projects.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    
    public ProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }
    
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<ProjectResponse> projects = projectService.getAllProjects().stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable UUID id) {
        return projectService.getProjectById(id)
                .map(project -> ResponseEntity.ok(projectMapper.toResponse(project)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request) {
        Project project = projectMapper.toEntity(request);
        Project savedProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectMapper.toResponse(savedProject));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable UUID id,
            @Valid @RequestBody ProjectRequest request) {
        return projectService.getProjectById(id)
                .map(existingProject -> {
                    projectMapper.updateEntityFromRequest(existingProject, request);
                    Project updatedProject = projectService.updateProject(id, existingProject);
                    return ResponseEntity.ok(projectMapper.toResponse(updatedProject));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}