package worldclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PostMapping("/signUp")
	public Message signUp(@RequestBody User user) {

		if (userService.getUserByUsername(user.getUsername()) != null) {

			return new Message(400, "Username existed!");

		}

		userService.addUser(user);

		return new Message(200, "Sign up success!");
	}

	@PostMapping("/signIn")
	public String signIn(@RequestBody User user) {

		if (userService.checkUserExisted(user.getUsername(), user.getPassword())) {

			userService.deleteGuest(user.getSessionId());

			return userService.getUserByUsername(user.getUsername()).getSessionId();

		}

		return null;

	}

	@RequestMapping("/signOut/{cityName}")
	public String signOut(@PathVariable("cityName") String homeCity) {

		return userService.addGuest(homeCity);

	}

	@RequestMapping("/addGuest/{cityName}")
	public String addGuest(@PathVariable("cityName") String homeCity) {

		return userService.addGuest(homeCity);

	}

}
