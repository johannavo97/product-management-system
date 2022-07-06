package com.johanna.productmanagementproject.data;

import com.johanna.productmanagementproject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByaEmail(String email);
}
