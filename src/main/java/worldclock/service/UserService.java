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

	public void changeHome(User user) {

		User userNeedChange = userRepository.findBySessionId(user.getSessionId());

		userNeedChange.setCity(user.getCity());

		userRepository.save(userNeedChange);

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

		userRepository.deleteBySessionId(sessionId);

	}
	
	public boolean isLogin(String sessionId) {

		return userRepository.findBySessionId(sessionId).getUsername() != null;

	}

	public Integer getHomeCityId(String sessionId) {

		return userRepository.findBySessionId(sessionId).getCity().getCityId();

	}

}
