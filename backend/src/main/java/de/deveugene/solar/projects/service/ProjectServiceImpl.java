package de.deveugene.solar.projects.service;

import de.deveugene.solar.projects.domain.Project;
import de.deveugene.solar.projects.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    
    private final ProjectRepository projectRepository;
    
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Project> getProjectById(UUID id) {
        return projectRepository.findById(id);
    }
    
    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
    
    @Override
    public Project updateProject(UUID id, Project project) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException("Project not found with id: " + id);
        }
        project.setId(id);
        return projectRepository.save(project);
    }
    
    @Override
    public void deleteProject(UUID id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException("Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
    }
}