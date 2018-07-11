package worldclock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import worldclock.entity.City;
import worldclock.entity.User;
import worldclock.repository.UserRepository;
import worldclock.utils.MD5Library;

@Service
public class UserService {

	@Autowired
	private UserRepository  userRepository;

	@Autowired
	private CityInBoardService cityInBoardService;

	public String changeHome(User user) {

		User userNeedChange = userRepository.findBySessionId(user.getSessionId());

		if (userNeedChange == null) {

			return "Error! User is not existed!";

		}

		if (!cityInBoardService.isCityExistInBoard(user.getSessionId(), user.getCity())) {

			return "Error! This cityId is not existed in board for this user!";

		}

		userNeedChange.setCity(user.getCity());

		userRepository.save(userNeedChange);

		return "Update home city successful!";

	}

	public List<User> getAllUsers() {

		return (List<User>) userRepository.findAll();

	}

	public User getUserByUsername(String username) {

		return userRepository.findByUsername(username);

	}

	public String addUser(User user) {

		User oldUser = userRepository.findBySessionId(user.getSessionId());

		if (oldUser == null) {

			return "Error! SessionId does not exist!";

		}

		if (oldUser.getUsername() != null) {

			return "Error! User already logged in!";

		}

		if (userRepository.findByUsername(user.getUsername()) != null) {

			return "Error! Username existed!";

		}

		oldUser.setUsername(user.getUsername());
		oldUser.setPassword(MD5Library.md5(user.getPassword()));

		userRepository.save(oldUser);
		
		return "Sign up success!";

	}

	public boolean checkUserExisted(String username, String password) {

		password = MD5Library.md5(password);

		return userRepository.findByUsernameAndPassword(username, password) != null;

	}

	public String addGuest(Integer homeCityId) {

		int currentHighestId = 0;

		if (userRepository.findByHighestId(new PageRequest(0, 1)).size() > 0) {

			currentHighestId = userRepository.findByHighestId(new PageRequest(0, 1)).get(0).getUserId();

		}

		String sessionId = MD5Library.md5(currentHighestId + 1 + "");

		User user = new User(null, sessionId, homeCityId, null, null);

		userRepository.save(user);

		cityInBoardService.addDefaultCitiesIntoBoard(sessionId, homeCityId);

		return sessionId;

	}

	public void deleteGuest(String sessionId) {

		if (userRepository.findBySessionId(sessionId).getUsername() == null) {

			userRepository.deleteBySessionId(sessionId);

		}

	}
	
	public boolean isLogin(String sessionId) {

		return userRepository.findBySessionId(sessionId).getUsername() != null;

	}

	public Integer getHomeCityId(String sessionId) {

		return userRepository.findBySessionId(sessionId).getCity().getCityId();

	}

}
