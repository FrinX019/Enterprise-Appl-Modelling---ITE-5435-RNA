package com.hospital.userservice.dto;

import java.time.LocalDate;

public record UserResponse(Integer userId, Integer userRoleId, String userName, String userEmail, LocalDate userDob,
		String userAddress) {
}
