package com.pyvovarnazar.mydrive.dao;

import com.pyvovarnazar.mydrive.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}