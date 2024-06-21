package com.spa.ecommerce.user;

import com.spa.ecommerce.common.GeneralService;
import com.spa.ecommerce.order.dto.OrderDTO;
import com.spa.ecommerce.profile.ProfileDTO;
import com.spa.ecommerce.order.orderitem.dto.OrderItemDTO;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService extends GeneralService<UserDTO, Long> {
    Optional<UserDTO> activate(Long id);

    Optional<UserDTO> deactivate(Long id);

    Optional<?> getCurrentUser(Principal principal);

    String updateUser(Principal principal, ProfileDTO profileDTO);

    Optional<List<OrderDTO>> getOrdersForSeller(Principal principal);

    //Optional<String> resetPassword(Long id, ResetPasswordDTO resetPassword);
}
