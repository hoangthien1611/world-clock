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
	UserRepository  userRepository;
	
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
		User user1 = userRepository.findBySessionId(user.getSessionId());
		user1.setUsername(user.getUsername());
		user1.setPassword(MD5Library.md5(user.getPassword()));
		userRepository.save(user1);
	}
	
	public boolean checkUserExisted(String username, String password) {
		password = MD5Library.md5(password);
		if (userRepository.findByUsernameAndPassword(username, password) != null)
			return true;
		return false;
	}

	public String addGuest() {
		String sessionId = MD5Library.md5(userRepository.findByHighestId(new PageRequest(0, 1)).get(0).getUserId() + 1 + "");
		User user = new User(null, sessionId, "Da_Nang", null, null);
		userRepository.save(user);
		return sessionId;
	}

	public void deleteGuest(String sessionId) {
		userRepository.deleteBySessionId(sessionId);
		
	}

}
