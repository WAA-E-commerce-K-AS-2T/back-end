package com.spa.ecommerce.common;

import com.spa.ecommerce.user.User;
import com.spa.ecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public AuditorAware<User> auditorProvider() {
        return new AuditorAwareImpl(userRepository);
    }

    public static class AuditorAwareImpl implements AuditorAware<User> {

        private final UserRepository userRepository;

        public AuditorAwareImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public Optional<User> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return Optional.empty();
            }
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return userRepository.findByEmail(((UserDetails) principal).getUsername());
            } else {
                return Optional.empty();
            }
        }
    }
}
