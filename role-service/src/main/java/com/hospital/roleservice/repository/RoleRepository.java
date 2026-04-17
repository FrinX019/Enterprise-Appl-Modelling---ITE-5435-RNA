package com.hospital.roleservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.roleservice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	List<Role> findByRoleTitleContainingIgnoreCaseOrRoleDescriptionContainingIgnoreCase(String title, String description);
}
