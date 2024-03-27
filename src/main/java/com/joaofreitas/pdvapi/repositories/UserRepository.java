package com.joaofreitas.pdvapi.repositories;

import com.joaofreitas.pdvapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    @Query("select u.role from User u where u.username like :username")
    User.Role findRoleByUsername(String username);
}
