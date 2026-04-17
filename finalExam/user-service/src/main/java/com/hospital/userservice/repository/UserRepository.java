package com.hospital.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByUserNameContainingIgnoreCaseOrUserEmailContainingIgnoreCaseOrUserAddressContainingIgnoreCase(
			String n, String e, String a);
}
