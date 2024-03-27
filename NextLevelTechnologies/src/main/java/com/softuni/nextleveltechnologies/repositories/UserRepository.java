package com.softuni.nextleveltechnologies.repositories;

import com.softuni.nextleveltechnologies.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsernameOrEmail(String username, String email);
}
