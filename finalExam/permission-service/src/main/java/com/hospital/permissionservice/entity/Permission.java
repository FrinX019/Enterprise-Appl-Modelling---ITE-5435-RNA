package com.hospital.permissionservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "permissions")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permission_id")
	private Integer permissionId;

	@Column(name = "permission_role_id", nullable = false)
	private Integer permissionRoleId;

	@Column(name = "permission_title", nullable = false)
	private String permissionTitle;

	@Column(name = "permission_module")
	private String permissionModule;

	@Column(name = "permission_description")
	private String permissionDescription;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getPermissionRoleId() {
		return permissionRoleId;
	}

	public void setPermissionRoleId(Integer permissionRoleId) {
		this.permissionRoleId = permissionRoleId;
	}

	public String getPermissionTitle() {
		return permissionTitle;
	}

	public void setPermissionTitle(String permissionTitle) {
		this.permissionTitle = permissionTitle;
	}

	public String getPermissionModule() {
		return permissionModule;
	}

	public void setPermissionModule(String permissionModule) {
		this.permissionModule = permissionModule;
	}

	public String getPermissionDescription() {
		return permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}
}
