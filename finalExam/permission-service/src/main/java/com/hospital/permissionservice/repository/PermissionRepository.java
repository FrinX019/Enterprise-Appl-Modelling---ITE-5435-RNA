package com.hospital.permissionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.permissionservice.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

	List<Permission> findByPermissionTitleContainingIgnoreCaseOrPermissionDescriptionContainingIgnoreCaseOrPermissionModuleContainingIgnoreCase(
			String t, String d, String m);
}
