package com.hospital.userservice.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.userservice.dto.AssignRoleRequest;
import com.hospital.userservice.dto.UserRequest;
import com.hospital.userservice.dto.UserResponse;
import com.hospital.userservice.entity.User;
import com.hospital.userservice.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private static UserResponse toResponse(User u) {
		return new UserResponse(u.getUserId(), u.getUserRoleId(), u.getUserName(), u.getUserEmail(), u.getUserDob(),
				u.getUserAddress());
	}

	@GetMapping
	public List<UserResponse> list(@RequestParam(required = false) String keyword) {
		List<User> list;
		if (keyword == null || keyword.isBlank()) {
			list = userRepository.findAll();
		}
		else {
			list = userRepository
					.findByUserNameContainingIgnoreCaseOrUserEmailContainingIgnoreCaseOrUserAddressContainingIgnoreCase(
							keyword, keyword, keyword);
		}
		return list.stream().map(UserController::toResponse).toList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> get(@PathVariable Integer id) {
		return userRepository.findById(id).map(u -> ResponseEntity.ok(toResponse(u)))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<UserResponse> add(@RequestBody UserRequest request) {
		User user = new User();
		user.setUserName(request.userName());
		user.setUserEmail(request.userEmail());
		user.setUserDob(request.userDob());
		user.setUserAddress(request.userAddress());
		user.setUserRoleId(request.userRoleId());
		return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(userRepository.save(user)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> edit(@PathVariable Integer id, @RequestBody UserRequest request) {
		Optional<User> opt = userRepository.findById(id);
		if (opt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User existing = opt.get();
		existing.setUserName(request.userName());
		existing.setUserEmail(request.userEmail());
		existing.setUserDob(request.userDob());
		existing.setUserAddress(request.userAddress());
		existing.setUserRoleId(request.userRoleId());
		return ResponseEntity.ok(toResponse(userRepository.save(existing)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (!userRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/assign-role")
	@Transactional
	public ResponseEntity<UserResponse> assignRole(@RequestBody AssignRoleRequest request) {
		User user = userRepository.findById(request.userId()).orElse(null);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		user.setUserRoleId(request.roleId());
		User saved = userRepository.save(user);
		return ResponseEntity.ok(toResponse(saved));
	}
}
