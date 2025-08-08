package de.deveugene.solar.projects.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.deveugene.solar.projects.api.ProjectRequest;
import de.deveugene.solar.projects.domain.Project;
import de.deveugene.solar.projects.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ProjectControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @BeforeEach
    void setUp() {
        projectRepository.deleteAll();
    }
    
    @Test
    void createProject_ValidRequest_ReturnsCreatedProject() throws Exception {
        ProjectRequest request = createProjectRequest();
        
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()))
                .andExpect(jsonPath("$.customerName").value(request.getCustomerName()))
                .andExpect(jsonPath("$.status").value("PLANNING"));
    }
    
    @Test
    void getAllProjects_ReturnsListOfProjects() throws Exception {
        // Create test projects
        Project project1 = createTestProject("Project 1", "Customer 1");
        Project project2 = createTestProject("Project 2", "Customer 2");
        projectRepository.save(project1);
        projectRepository.save(project2);
        
        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Project 1"))
                .andExpect(jsonPath("$[1].name").value("Project 2"));
    }
    
    @Test
    void getProjectById_ExistingProject_ReturnsProject() throws Exception {
        Project project = createTestProject("Test Project", "Test Customer");
        Project savedProject = projectRepository.save(project);
        
        mockMvc.perform(get("/api/projects/{id}", savedProject.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedProject.getId().toString()))
                .andExpect(jsonPath("$.name").value("Test Project"));
    }
    
    @Test
    void getProjectById_NonExistingProject_ReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/projects/{id}", "550e8400-e29b-41d4-a716-446655440000"))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void createProject_InvalidRequest_ReturnsBadRequest() throws Exception {
        ProjectRequest request = new ProjectRequest();
        // Missing required fields
        
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").exists())
                .andExpect(jsonPath("$.errors.customerName").exists())
                .andExpect(jsonPath("$.errors.installationAddress").exists());
    }
    
    private ProjectRequest createProjectRequest() {
        ProjectRequest request = new ProjectRequest();
        request.setName("Solar Installation Project");
        request.setDescription("10kW residential solar system");
        request.setCustomerName("John Doe");
        request.setCustomerEmail("john.doe@example.com");
        request.setCustomerPhone("+1234567890");
        request.setInstallationAddress("123 Main St, City, State 12345");
        request.setSystemSizeKw(new BigDecimal("10.5"));
        request.setEstimatedCost(new BigDecimal("25000.00"));
        request.setScheduledDate(LocalDate.now().plusDays(30));
        return request;
    }
    
    private Project createTestProject(String name, String customerName) {
        Project project = new Project();
        project.setName(name);
        project.setCustomerName(customerName);
        project.setInstallationAddress("Test Address");
        return project;
    }
}