package com.johanna.productmanagementproject.data;

import com.johanna.productmanagementproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> deleteUserByEmail(String email);
}
