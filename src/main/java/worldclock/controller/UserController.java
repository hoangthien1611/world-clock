package worldclock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import worldclock.entity.User;
import worldclock.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/changeHome")
	public void changeHome(@RequestBody User user) {

		userService.changeHome(user);

	}
	
	@RequestMapping("/getAllUsers")
	public List<User> getAllUsers() {

		return userService.getAllUsers();

	}

}
