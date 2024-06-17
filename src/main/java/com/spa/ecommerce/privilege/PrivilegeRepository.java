package com.spa.ecommerce.privilege;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);
}
