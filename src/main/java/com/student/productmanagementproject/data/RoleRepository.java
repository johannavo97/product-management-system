package com.student.productmanagementproject.data;

import com.student.productmanagementproject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByREmail(String email);
}
