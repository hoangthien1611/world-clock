package worldclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import worldclock.entity.User;
import worldclock.service.UserService;
import worldclock.utils.Message;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public Message signup(@RequestBody User user) {
		if (userService.getUserByUsername(user.getUsername()) != null) {
			return new Message(400, "Username existed!");
		}

		userService.addUser(user);
		return new Message(200, "Sign up success!");
	}

	@PostMapping("/signin")
	public String signin(@RequestBody User user) {
		if (userService.checkUserExisted(user.getUsername(), user.getPassword())) {
			userService.deleteGuest(user.getSessionId());
			return userService.getUserByUsername(user.getUsername()).getSessionId();
		}
		return null;
	}

	@RequestMapping("/logout")
	public String logout() {
		return userService.addGuest();
	}
	
	@RequestMapping("/createGuest")
	public String addGuest() {
		return userService.addGuest();
	}
}
