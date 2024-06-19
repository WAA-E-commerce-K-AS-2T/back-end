package com.spa.ecommerce.user;

import com.spa.ecommerce.common.GeneralService;
import com.spa.ecommerce.profile.ProfileDTO;

import java.security.Principal;
import java.util.Optional;

public interface UserService extends GeneralService<UserDTO, Long> {
    Optional<UserDTO> activate(Long id);

    Optional<UserDTO> deactivate(Long id);

    Optional<?> getCurrentUser(Principal principal);

    String updateUser(Principal principal, ProfileDTO profileDTO);

    //Optional<String> resetPassword(Long id, ResetPasswordDTO resetPassword);
}
