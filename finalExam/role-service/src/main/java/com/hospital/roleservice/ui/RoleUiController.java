package com.hospital.roleservice.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hospital.roleservice.entity.Role;
import com.hospital.roleservice.repository.RoleRepository;

@Controller
public class RoleUiController {

	private final RoleRepository roleRepository;

	public RoleUiController(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/roles")
	public String roles(Model model, @RequestParam(required = false) String keyword) {
		List<Role> items;
		if (keyword == null || keyword.isBlank()) {
			items = roleRepository.findAll();
		} else {
			items = roleRepository.findByRoleTitleContainingIgnoreCaseOrRoleDescriptionContainingIgnoreCase(keyword, keyword);
		}
		model.addAttribute("items", items);
		model.addAttribute("keyword", keyword != null ? keyword : "");
		return "roles";
	}

	@PostMapping("/roles")
	public String addRole(@RequestParam String roleTitle, @RequestParam(required = false) String roleDescription) {
		Role role = new Role();
		role.setRoleTitle(roleTitle);
		role.setRoleDescription(roleDescription);
		roleRepository.save(role);
		return "redirect:/roles";
	}

	@PostMapping("/roles/delete")
	public String deleteRole(@RequestParam Integer id) {
		roleRepository.deleteById(id);
		return "redirect:/roles";
	}
}
