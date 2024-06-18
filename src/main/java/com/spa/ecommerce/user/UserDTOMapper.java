package com.spa.ecommerce.user;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {

        return new UserDTO(
                user.getId(),
                user.getEmail(),
                "",//user.getUsername(),
                user.getFullName(),
                user.getEnabled()
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
