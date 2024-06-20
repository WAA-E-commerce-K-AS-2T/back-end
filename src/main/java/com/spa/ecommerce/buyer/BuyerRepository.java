package com.spa.ecommerce.buyer;

import com.spa.ecommerce.address.Address;
import com.spa.ecommerce.user.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    Optional<Buyer> findByEmail(String name);

    @Query("SELECT b.address FROM Buyer b WHERE b.email = :email")
    Optional<Address> findAddressByEmail(@Param("email") String email);
}
