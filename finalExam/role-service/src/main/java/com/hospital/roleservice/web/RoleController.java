package com.hospital.roleservice.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.roleservice.dto.RoleRequest;
import com.hospital.roleservice.entity.Role;
import com.hospital.roleservice.repository.RoleRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	private final RoleRepository roleRepository;

	public RoleController(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@GetMapping
	public List<Role> list(@RequestParam(required = false) String keyword) {
		if (keyword == null || keyword.isBlank()) {
			return roleRepository.findAll();
		}
		return roleRepository.findByRoleTitleContainingIgnoreCaseOrRoleDescriptionContainingIgnoreCase(keyword, keyword);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Role> get(@PathVariable Integer id) {
		return roleRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Role> add(@RequestBody RoleRequest request) {
		Role role = new Role();
		role.setRoleTitle(request.roleTitle());
		role.setRoleDescription(request.roleDescription());
		return ResponseEntity.status(HttpStatus.CREATED).body(roleRepository.save(role));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Role> edit(@PathVariable Integer id, @RequestBody RoleRequest request) {
		return roleRepository.findById(id).map(existing -> {
			existing.setRoleTitle(request.roleTitle());
			existing.setRoleDescription(request.roleDescription());
			return ResponseEntity.ok(roleRepository.save(existing));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (!roleRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		roleRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
