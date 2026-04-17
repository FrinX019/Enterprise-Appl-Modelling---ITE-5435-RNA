package com.hospital.permissionservice.dto;

public record PermissionResponse(Integer permissionId, Integer permissionRoleId, String permissionTitle,
		String permissionModule, String permissionDescription) {
}
