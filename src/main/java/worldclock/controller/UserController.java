package worldclock.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/checkUsername/{email:.+}")
	public boolean checkUsername(@PathVariable("email") String email) {

		return userService.isUsernameRegistered(email);

	}

	@PutMapping("/changePassword")
	public Message changePassword(@RequestBody User user) {

		if (userService.isUsernameRegistered(user.getUsername())) {

			userService.deleteGuest(user.getSessionId());

			String sessionId = userService.updatePassword(user);

			if (sessionId != null) {

				return new Message(200, sessionId);

			}

		}

		return new Message(400, "Can not update password!");

	}

	@GetMapping("/forgotPassword/{email:.+}")
	public Message sendMailWhenForgotPassword(@PathVariable("email") String mail) {

		if (userService.isUsernameRegistered(mail)) {

			try {

				userService.sendMail(mail);

				return new Message(200, "Check your mail to get the new password!");

			} catch (MessagingException | UnsupportedEncodingException e) {

				return new Message(400, "Can not send an email! Please try again!");

			}

		}

		return new Message(400, "Email is not registered!");

	}

}
