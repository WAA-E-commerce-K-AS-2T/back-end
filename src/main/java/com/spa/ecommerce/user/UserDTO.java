package com.spa.ecommerce.user;

import com.spa.ecommerce.role.RoleDTO;

import java.util.Collection;

public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private Boolean isActive;
    private Collection<RoleDTO> roles;

    public UserDTO(Long id, String email, String username, String fullName, Boolean isActive, Collection<RoleDTO> roles) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleDTO> roles) {
        this.roles = roles;
    }
}
