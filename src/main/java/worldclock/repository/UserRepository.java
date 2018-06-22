package worldclock.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import worldclock.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
