package de.deveugene.solar.projects.api;

import de.deveugene.solar.projects.domain.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    
    public Project toEntity(ProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setCustomerName(request.getCustomerName());
        project.setCustomerEmail(request.getCustomerEmail());
        project.setCustomerPhone(request.getCustomerPhone());
        project.setInstallationAddress(request.getInstallationAddress());
        project.setSystemSizeKw(request.getSystemSizeKw());
        project.setEstimatedCost(request.getEstimatedCost());
        project.setScheduledDate(request.getScheduledDate());
        return project;
    }
    
    public ProjectResponse toResponse(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setCustomerName(project.getCustomerName());
        response.setCustomerEmail(project.getCustomerEmail());
        response.setCustomerPhone(project.getCustomerPhone());
        response.setInstallationAddress(project.getInstallationAddress());
        response.setStatus(project.getStatus());
        response.setSystemSizeKw(project.getSystemSizeKw());
        response.setEstimatedCost(project.getEstimatedCost());
        response.setScheduledDate(project.getScheduledDate());
        response.setCompletionDate(project.getCompletionDate());
        response.setCreatedAt(project.getCreatedAt());
        response.setUpdatedAt(project.getUpdatedAt());
        return response;
    }
    
    public void updateEntityFromRequest(Project project, ProjectRequest request) {
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setCustomerName(request.getCustomerName());
        project.setCustomerEmail(request.getCustomerEmail());
        project.setCustomerPhone(request.getCustomerPhone());
        project.setInstallationAddress(request.getInstallationAddress());
        project.setSystemSizeKw(request.getSystemSizeKw());
        project.setEstimatedCost(request.getEstimatedCost());
        project.setScheduledDate(request.getScheduledDate());
    }
}