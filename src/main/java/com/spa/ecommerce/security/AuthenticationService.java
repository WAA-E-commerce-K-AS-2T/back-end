package com.spa.ecommerce.security;

import com.spa.ecommerce.resetpassword.ResetPassword;
import com.spa.ecommerce.resetpassword.ResetPasswordRepository;
import com.spa.ecommerce.role.Role;
import com.spa.ecommerce.role.RoleRepository;
import com.spa.ecommerce.user.Buyer;
import com.spa.ecommerce.user.Seller;
import com.spa.ecommerce.user.User;
import com.spa.ecommerce.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Value("${security.resepassword.token.expiration-time}")
    private int resetpasswordExpiration;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ResetPasswordRepository resetPasswordRepository;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            ResetPasswordRepository resetPasswordRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.resetPasswordRepository = resetPasswordRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<User> signup(RegisterUserDto input) {
        User user;

        if (input.getAuthtype().equals(AuthType.SELLER)) {
            user = new Seller();
            Optional<Role> sellerRole = roleRepository.findByName("ROLE_SELLER");
            sellerRole.ifPresent(user::addRole);
        } else if (input.getAuthtype().equals(AuthType.BUYER)) {
            user = new Buyer();
            Optional<Role> sellerRole = roleRepository.findByName("ROLE_BUYER");
            sellerRole.ifPresent(user::addRole);
        } else {
            return Optional.empty();
        }
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setEnabled(true);

        return Optional.of(userRepository.save(user));
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

    public Optional<String> resetToken(LoginUserDto loginUserDto) {
        Optional<User> user = userRepository.findByEmail(loginUserDto.getEmail());
        if(user.isPresent()) {
            String token = java.util.UUID.randomUUID().toString();
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.setToken(token);
            resetPassword.setUser(user.get());

            resetPasswordRepository.save(resetPassword);

            return Optional.of("Token will sent to your email");
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<String> resetPassword(String token, ResetPasswordDTO resetPasswordDTO) {
        LocalDateTime expiryDate = LocalDateTime.now().minusHours(72);
        Optional<ResetPassword> resetPassword = resetPasswordRepository.findValidToken(token, expiryDate);
        if(resetPassword.isPresent()) {
            User user = resetPassword.get().getUser();
            user.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));

            resetPasswordRepository.deleteById(resetPassword.get().getId());
            userRepository.save(user);
        }
        return Optional.of("Your password has been changed.");
    }
}
