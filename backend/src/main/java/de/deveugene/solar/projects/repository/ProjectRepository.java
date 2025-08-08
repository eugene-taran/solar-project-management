package de.deveugene.solar.projects.repository;

import de.deveugene.solar.projects.domain.Project;
import de.deveugene.solar.projects.domain.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    
    List<Project> findByStatus(ProjectStatus status);
    
    List<Project> findByCustomerNameContainingIgnoreCase(String customerName);
    
    List<Project> findByStatusIn(List<ProjectStatus> statuses);
}