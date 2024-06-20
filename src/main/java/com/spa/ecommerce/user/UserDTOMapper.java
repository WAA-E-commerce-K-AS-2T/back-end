package com.spa.ecommerce.user;

import com.spa.ecommerce.role.RoleDTO;
import com.spa.ecommerce.role.RoleDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    @Autowired
    private RoleDTOMapper roleDTOMapper;

    @Override
    public UserDTO apply(User user) {
        Collection<RoleDTO> roles = user.getRoles().stream().map(roleDTOMapper).toList();

        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getFullName(),
                user.getEnabled(),
                roles
        );
    }

    @Override
    public <V> Function<V, UserDTO> compose(Function<? super V, ? extends User> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<User, V> andThen(Function<? super UserDTO, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
