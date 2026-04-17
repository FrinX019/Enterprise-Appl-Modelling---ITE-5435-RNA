package com.hospital.permissionservice.dto;

public record PermissionRequest(Integer permissionRoleId, String permissionTitle, String permissionModule,
		String permissionDescription) {
}
