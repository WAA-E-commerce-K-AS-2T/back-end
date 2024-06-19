package com.spa.ecommerce.role;

import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private String name;
    private String description;

    public RoleDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
