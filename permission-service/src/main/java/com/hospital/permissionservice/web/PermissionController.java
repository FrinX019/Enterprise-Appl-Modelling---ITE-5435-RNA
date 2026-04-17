package com.hospital.permissionservice.web;

import java.util.List;
import java.util.Optional;

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

import com.hospital.permissionservice.dto.PermissionRequest;
import com.hospital.permissionservice.dto.PermissionResponse;
import com.hospital.permissionservice.entity.Permission;
import com.hospital.permissionservice.repository.PermissionRepository;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

	private final PermissionRepository permissionRepository;

	public PermissionController(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}

	private static PermissionResponse toResponse(Permission p) {
		return new PermissionResponse(p.getPermissionId(), p.getPermissionRoleId(), p.getPermissionTitle(),
				p.getPermissionModule(), p.getPermissionDescription());
	}

	@GetMapping
	public List<PermissionResponse> list(@RequestParam(required = false) String keyword) {
		List<Permission> list;
		if (keyword == null || keyword.isBlank()) {
			list = permissionRepository.findAll();
		}
		else {
			list = permissionRepository
					.findByPermissionTitleContainingIgnoreCaseOrPermissionDescriptionContainingIgnoreCaseOrPermissionModuleContainingIgnoreCase(
							keyword, keyword, keyword);
		}
		return list.stream().map(PermissionController::toResponse).toList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PermissionResponse> get(@PathVariable Integer id) {
		return permissionRepository.findById(id).map(p -> ResponseEntity.ok(toResponse(p)))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<PermissionResponse> add(@RequestBody PermissionRequest request) {
		Permission p = new Permission();
		p.setPermissionRoleId(request.permissionRoleId());
		p.setPermissionTitle(request.permissionTitle());
		p.setPermissionModule(request.permissionModule());
		p.setPermissionDescription(request.permissionDescription());
		return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(permissionRepository.save(p)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PermissionResponse> edit(@PathVariable Integer id, @RequestBody PermissionRequest request) {
		Optional<Permission> opt = permissionRepository.findById(id);
		if (opt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Permission existing = opt.get();
		existing.setPermissionRoleId(request.permissionRoleId());
		existing.setPermissionTitle(request.permissionTitle());
		existing.setPermissionModule(request.permissionModule());
		existing.setPermissionDescription(request.permissionDescription());
		return ResponseEntity.ok(toResponse(permissionRepository.save(existing)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (!permissionRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		permissionRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
