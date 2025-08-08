package de.deveugene.solar.projects.service;

import de.deveugene.solar.projects.domain.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectService {
    
    List<Project> getAllProjects();
    
    Optional<Project> getProjectById(UUID id);
    
    Project createProject(Project project);
    
    Project updateProject(UUID id, Project project);
    
    void deleteProject(UUID id);
}