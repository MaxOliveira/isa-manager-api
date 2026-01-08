package com.isa.manager.isa_manager_api.repositories;

import com.isa.manager.isa_manager_api.domain.Product.Product;
import com.isa.manager.isa_manager_api.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
