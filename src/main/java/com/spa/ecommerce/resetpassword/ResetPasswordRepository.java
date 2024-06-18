package com.spa.ecommerce.resetpassword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Long> {
    Optional<ResetPassword> findByToken(String token);

    @Query("SELECT r FROM ResetPassword r WHERE r.token = :token AND r.createdDate >= :expiryDate")
    Optional<ResetPassword> findValidToken(@Param("token") String token, @Param("expiryDate") LocalDateTime expiryDate);
}
