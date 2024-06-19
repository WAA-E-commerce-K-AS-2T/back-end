package com.spa.ecommerce.user;

import com.spa.ecommerce.common.GeneralService;
import com.spa.ecommerce.order.orderitem.dto.OrderItemDTO;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService extends GeneralService<UserDTO, Long> {
    Optional<UserDTO> activate(Long id);

    Optional<UserDTO> deactivate(Long id);

    Optional<List<OrderItemDTO>> getOrderItemsForSeller(Principal principal);

    //Optional<String> resetPassword(Long id, ResetPasswordDTO resetPassword);
}
