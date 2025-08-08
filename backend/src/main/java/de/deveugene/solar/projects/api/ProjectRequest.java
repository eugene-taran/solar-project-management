package de.deveugene.solar.projects.api;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProjectRequest {
    
    @NotBlank(message = "Project name is required")
    @Size(max = 255, message = "Project name must not exceed 255 characters")
    private String name;
    
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;
    
    @NotBlank(message = "Customer name is required")
    @Size(max = 255, message = "Customer name must not exceed 255 characters")
    private String customerName;
    
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String customerEmail;
    
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String customerPhone;
    
    @NotBlank(message = "Installation address is required")
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String installationAddress;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "System size must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Invalid system size format")
    private BigDecimal systemSizeKw;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Estimated cost must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Invalid cost format")
    private BigDecimal estimatedCost;
    
    @Future(message = "Scheduled date must be in the future")
    private LocalDate scheduledDate;
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerEmail() {
        return customerEmail;
    }
    
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    public String getCustomerPhone() {
        return customerPhone;
    }
    
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    public String getInstallationAddress() {
        return installationAddress;
    }
    
    public void setInstallationAddress(String installationAddress) {
        this.installationAddress = installationAddress;
    }
    
    public BigDecimal getSystemSizeKw() {
        return systemSizeKw;
    }
    
    public void setSystemSizeKw(BigDecimal systemSizeKw) {
        this.systemSizeKw = systemSizeKw;
    }
    
    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }
    
    public void setEstimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
    }
    
    public LocalDate getScheduledDate() {
        return scheduledDate;
    }
    
    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}