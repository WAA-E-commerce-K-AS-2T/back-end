package com.spa.ecommerce.user;

import com.spa.ecommerce.common.GeneralService;

import java.util.Optional;

public interface UserService extends GeneralService<UserDTO, Long> {
    Optional<UserDTO> activate(Long id);

    Optional<UserDTO> deactivate(Long id);

    //Optional<String> resetPassword(Long id, ResetPasswordDTO resetPassword);
}
