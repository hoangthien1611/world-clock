package worldclock.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import worldclock.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, String> {
	
	//List<City> findAll();

}