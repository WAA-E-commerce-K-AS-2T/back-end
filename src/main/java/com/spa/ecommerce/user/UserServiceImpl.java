package com.spa.ecommerce.user;

import com.spa.ecommerce.address.Address;
import com.spa.ecommerce.address.AddressRepository;
import com.spa.ecommerce.buyer.BuyerDTO;
import com.spa.ecommerce.buyer.BuyerDTOMapper;
import com.spa.ecommerce.buyer.BuyerRepository;
import com.spa.ecommerce.order.orderitem.OrderItem;
import com.spa.ecommerce.order.orderitem.dto.OrderItemDTO;
import com.spa.ecommerce.order.orderitem.dto.OrderItemDTOMapper;
import com.spa.ecommerce.profile.ProfileDTO;
import com.spa.ecommerce.seller.Seller;
import com.spa.ecommerce.seller.SellerDTO;
import com.spa.ecommerce.seller.SellerDTOMapper;
import com.spa.ecommerce.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDTOMapper userDTOMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SellerDTOMapper sellerDTOMapper;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private BuyerDTOMapper buyerDTOMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderItemDTOMapper orderItemDTOMapper;

    @Override
    public Collection<UserDTO> getAll() {
        return userRepository.findAll().stream().map(userDTOMapper).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> get(Long id) {
        return userRepository.findById(id).map(userDTOMapper);
    }

    public Optional<UserDTO> save(UserDTO user) {
        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setEnabled(user.getIsActive());
        newUser = userRepository.save(newUser);

        return Optional.of(userDTOMapper.apply(newUser));
    }

    public Optional<UserDTO> update(Long id, UserDTO updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            existingUser.setEnabled(updatedUser.getIsActive());
            userRepository.save(existingUser);

            return Optional.of(userDTOMapper.apply(existingUser));
        } else {
            return Optional.empty();
        }
    }

    public Optional<UserDTO> delete(Long id, UserDTO updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            userRepository.deleteById(id);
            return Optional.of(userDTOMapper.apply(existingUserOpt.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDTO> activate(Long id) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setEnabled(true);
            userRepository.save(existingUser);

            return Optional.of(userDTOMapper.apply(existingUser));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDTO> deactivate(Long id) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setEnabled(false);
            userRepository.save(existingUser);

            return Optional.of(userDTOMapper.apply(existingUser));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<OrderItemDTO>> getOrderItemsForSeller(Principal principal) {
        String email = principal.getName();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            Long sellerId = user.get().getId();
            Optional<List<OrderItem>> oi = sellerRepository.findOrderItemsByUserId(sellerId);
            if (oi.isPresent()) {
                List<OrderItem> orderItems = oi.get();
                List<OrderItemDTO> dto = orderItems.stream().map(o -> orderItemDTOMapper.apply(o)).collect(Collectors.toList());
                return Optional.of(dto);
            }else{
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserWrapper> getCurrentUser(Principal principal) {
        String email = principal.getName();
        return sellerRepository.findByEmail(email)
                .map(seller -> new UserWrapper(sellerDTOMapper.apply(seller)))
                .or(() -> buyerRepository.findByEmail(email)
                        .map(buyer -> new UserWrapper(buyerDTOMapper.apply(buyer))));
    }

    @Override
    public String updateUser(Principal principal, ProfileDTO profileDTO) {
        //return Optional.of("test");
        Optional<Seller> seller = sellerRepository.findByEmail(principal.getName());
        if (seller.isPresent()) {
            return "Your Account has been changed";
        } else {
            Optional<Buyer> buyer = buyerRepository.findByEmail(principal.getName());
            if (buyer.isPresent()) {
                Buyer saveObj = buyer.get();
                saveObj.setFullName(profileDTO.getFullname());

                //if(saveObj.getAddress() == null) {
                    Address address = new Address();

                    //address.setId(profileDTO.getAddress().getId());
                    address.setAddress1(profileDTO.getAddress().getAddress1());
                    address.setAddress2(profileDTO.getAddress().getAddress2());
                    address.setAddress3(profileDTO.getAddress().getAddress3());
                    address.setAddress4(profileDTO.getAddress().getAddress4());
                    address.setCity(profileDTO.getAddress().getCity());
                    address.setState(profileDTO.getAddress().getState());
                    address.setZipcode(profileDTO.getAddress().getZipcode());
                    address.setPincode(profileDTO.getAddress().getPincode());

                    address = addressRepository.save(address);
                    saveObj.setAddress(address);
                //}
                buyerRepository.save(saveObj);
                System.out.println("asdfasdfas");
                return "Your Account has been changed";
            } else {
                return "";
            }
        }
    }

//    @Override
//    public Optional<String> resetPassword(Long id, ResetPasswordDTO resetPassword) {
//        Optional<User> existingUserOpt = userRepository.findById(id);
//        if (existingUserOpt.isPresent() && resetPassword.getPassword().equals(resetPassword.getRePassword())) {
//            User existingUser = existingUserOpt.get();
//            existingUser.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
//            userRepository.save(existingUser);
//
//            return Optional.of("Password Successfully Changed");
//        } else {
//            return Optional.empty();
//        }
//    }
}
