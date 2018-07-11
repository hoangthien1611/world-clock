package worldclock.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import worldclock.entity.User;
import worldclock.service.UserService;
import worldclock.utils.Message;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@PutMapping("/signUp")
	public Message signUp(@RequestBody User user) {

		if ("".equals(user.getUsername())) {

			return new Message(400, "Error! Username can not be empty!");

		}

		String regex = "^[a-zA-Z0-9_.-@]*$";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(user.getUsername());

		if (!matcher.matches()) {

			return new Message(400, "Error! Username is not correct format!");

		}

		String content = userService.addUser(user);

		int code = content.contains("Error") ? 400 : 200;

		return new Message(code, content);

	}

	@PostMapping("/signIn")
	public String signIn(@RequestBody User user) {

		if (userService.checkUserExisted(user.getUsername(), user.getPassword())) {

			userService.deleteGuest(user.getSessionId());

			return userService.getUserByUsername(user.getUsername()).getSessionId();

		}

		return null;

	}

	@PostMapping("/signOut/{homeCityId}")
	public String signOut(@PathVariable("homeCityId") Integer homeCityId) {

		return userService.addGuest(homeCityId);

	}

	@PostMapping("/addGuest/{homeCityId}")
	public String addGuest(@PathVariable("homeCityId") Integer homeCityId) {

		return userService.addGuest(homeCityId);

	}

}
