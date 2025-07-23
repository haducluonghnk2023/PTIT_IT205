package com.data.session11.repository;

import com.data.session11.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findFirstByRoleName(String roleName);
}
