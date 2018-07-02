package worldclock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import worldclock.entity.User;
import worldclock.repository.UserRepository;
import worldclock.utils.MD5Library;

@Service
public class UserService {

	@Autowired
	private UserRepository  userRepository;

	@Autowired
	private BoardService boardService;

	public void changeHome(User user) {

		userRepository.save(user);

	}

	public List<User> getAllUsers() {

		return (List<User>) userRepository.findAll();

	}

	public User getUserByUsername(String username) {

		return userRepository.findByUsername(username);

	}

	public void addUser(User user) {

		User oldUser = userRepository.findBySessionId(user.getSessionId());

		oldUser.setUsername(user.getUsername());
		oldUser.setPassword(MD5Library.md5(user.getPassword()));

		userRepository.save(oldUser);

	}

	public boolean checkUserExisted(String username, String password) {

		password = MD5Library.md5(password);

		return userRepository.findByUsernameAndPassword(username, password) != null;

	}

	public String addGuest(String homeCity) {

		String sessionId = MD5Library.md5(userRepository.findByHighestId(new PageRequest(0, 1)).get(0).getUserId() + 1 + "");

		User user = new User(null, sessionId, homeCity, null, null);

		userRepository.save(user);

		boardService.addDefaultCitiesIntoBoard(sessionId, homeCity);

		return sessionId;

	}

	public void deleteGuest(String sessionId) {

		userRepository.deleteBySessionId(sessionId);

	}

}
