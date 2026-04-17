package com.hospital.userservice.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.hospital.userservice.entity.User;
import com.hospital.userservice.repository.UserRepository;

@Controller
public class UserUiController {

	private final UserRepository userRepository;
	private final RestTemplate restTemplate;

	@Value("${services.role.url}")
	private String roleServiceUrl;

	public UserUiController(UserRepository userRepository, RestTemplate restTemplate) {
		this.userRepository = userRepository;
		this.restTemplate = restTemplate;
	}

	private static final ParameterizedTypeReference<List<Map<String, Object>>> LIST_MAP = new ParameterizedTypeReference<List<Map<String, Object>>>() {};

	private List<Map<String, Object>> fetchRoles() {
		var r = restTemplate.exchange(roleServiceUrl + "/api/roles", HttpMethod.GET, null, LIST_MAP);
		return r.getBody() != null ? r.getBody() : List.of();
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/users")
	public String users(Model model, @RequestParam(required = false) String keyword) {
		List<User> items;
		if (keyword == null || keyword.isBlank()) {
			items = userRepository.findAll();
		} else {
			items = userRepository.findByUserNameContainingIgnoreCaseOrUserEmailContainingIgnoreCaseOrUserAddressContainingIgnoreCase(keyword, keyword, keyword);
		}
		model.addAttribute("items", items);
		model.addAttribute("keyword", keyword != null ? keyword : "");
		model.addAttribute("roles", fetchRoles());
		model.addAttribute("allUsers", userRepository.findAll());
		return "users";
	}

	@PostMapping("/users")
	public String addUser(@RequestParam(required = false) Integer userRoleId, @RequestParam String userName,
			@RequestParam String userEmail, @RequestParam(required = false) String userDob,
			@RequestParam(required = false) String userAddress) {
		User user = new User();
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		if (userDob != null && !userDob.isBlank()) {
			user.setUserDob(LocalDate.parse(userDob));
		}
		user.setUserAddress(userAddress);
		user.setUserRoleId(userRoleId);
		userRepository.save(user);
		return "redirect:/users";
	}

	@PostMapping("/users/delete")
	public String deleteUser(@RequestParam Integer id) {
		userRepository.deleteById(id);
		return "redirect:/users";
	}

	@PostMapping("/users/assign")
	@Transactional
	public String assignRole(@RequestParam Integer userId, @RequestParam Integer roleId) {
		User u = userRepository.findById(userId).orElseThrow();
		u.setUserRoleId(roleId);
		userRepository.save(u);
		return "redirect:/users";
	}
}
