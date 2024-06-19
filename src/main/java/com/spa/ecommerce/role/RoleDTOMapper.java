package com.spa.ecommerce.role;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RoleDTOMapper implements Function<Role, RoleDTO> {
    @Override
    public RoleDTO apply(Role role) {
        return new RoleDTO(role.getId(), role.getName(), role.getDescription());
    }
}
