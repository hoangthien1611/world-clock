package worldclock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import worldclock.entity.User;
import worldclock.service.UserService;
import worldclock.utils.Message;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PutMapping("/changeHome")
	public Message changeHome(@RequestBody User user) {

		String content = userService.changeHome(user);

		int code = content.contains("Error") ? 400 : 200;

		return new Message(code, content);

	}

	@RequestMapping("/getAllUsers")
	public List<User> getAllUsers() {

		return userService.getAllUsers();

	}

}
