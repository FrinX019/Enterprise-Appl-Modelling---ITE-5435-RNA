package com.hospital.userservice.dto;

import java.time.LocalDate;

public record UserRequest(Integer userRoleId, String userName, String userEmail, LocalDate userDob,
		String userAddress) {
}
