package worldclock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worldclock.entity.User;
import worldclock.repository.UserRepository;

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

}
