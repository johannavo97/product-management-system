package com.student.productmanagementproject.data;

import com.student.productmanagementproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
