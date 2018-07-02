package worldclock.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import worldclock.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	public User findByUsername(String username); 
	public User findByUsernameAndPassword(String username, String password);

	@Transactional
	@Modifying
	@Query("delete from User u where u.sessionId = ?1")
	public void deleteBySessionId(String sessionId);
	public User findBySessionId(String sessionId);

	@Query("select u from User u order by u.userId desc")
	public List<User> findByHighestId(Pageable pageble);

}
