package com.hospital.permissionservice.ui;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.hospital.permissionservice.entity.Permission;
import com.hospital.permissionservice.repository.PermissionRepository;

@Controller
public class PermissionUiController {

	private final PermissionRepository permissionRepository;
	private final RestTemplate restTemplate;

	@Value("${services.role.url}")
	private String roleServiceUrl;

	public PermissionUiController(PermissionRepository permissionRepository, RestTemplate restTemplate) {
		this.permissionRepository = permissionRepository;
		this.restTemplate = restTemplate;
	}

	private static final ParameterizedTypeReference<List<Map<String, Object>>> LIST_MAP = new ParameterizedTypeReference<List<Map<String, Object>>>() {};

	private List<Map<String, Object>> fetchRoles() {
		try {
			ResponseEntity<List<Map<String, Object>>> r = restTemplate.exchange(roleServiceUrl + "/api/roles", HttpMethod.GET, null, LIST_MAP);
			return r.getBody() != null ? r.getBody() : List.of();
		} catch (RestClientException ex) {
			return List.of();
		}
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/permissions")
	public String permissions(Model model, @RequestParam(required = false) String keyword) {
		List<Permission> items;
		if (keyword == null || keyword.isBlank()) {
			items = permissionRepository.findAll();
		} else {
			items = permissionRepository.findByPermissionTitleContainingIgnoreCaseOrPermissionDescriptionContainingIgnoreCaseOrPermissionModuleContainingIgnoreCase(keyword, keyword, keyword);
		}
		List<Map<String, Object>> roles = fetchRoles();
		model.addAttribute("items", items);
		model.addAttribute("keyword", keyword != null ? keyword : "");
		model.addAttribute("roles", roles);
		model.addAttribute("rolesUnavailable", roles.isEmpty());
		return "permissions";
	}

	@PostMapping("/permissions")
	public String addPermission(@RequestParam Integer permissionRoleId, @RequestParam String permissionTitle,
			@RequestParam(required = false) String permissionModule,
			@RequestParam(required = false) String permissionDescription) {
		Permission p = new Permission();
		p.setPermissionRoleId(permissionRoleId);
		p.setPermissionTitle(permissionTitle);
		p.setPermissionModule(permissionModule);
		p.setPermissionDescription(permissionDescription);
		permissionRepository.save(p);
		return "redirect:/permissions";
	}

	@PostMapping("/permissions/delete")
	public String deletePermission(@RequestParam Integer id) {
		permissionRepository.deleteById(id);
		return "redirect:/permissions";
	}
}
