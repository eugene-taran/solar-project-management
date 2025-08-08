package de.deveugene.solar.projects.service;

public class ProjectNotFoundException extends RuntimeException {
    
    public ProjectNotFoundException(String message) {
        super(message);
    }
    
    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}