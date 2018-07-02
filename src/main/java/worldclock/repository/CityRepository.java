package worldclock.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import worldclock.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, String> {
	
	@Query("select c.cityName from City c \r\n" + 
			"where c.cityName != ?1 and country not in \r\n" + 
			"(select ct.country from City ct where ct.cityName = ?1)\r\n" + 
			"order by random()")
	List<String> selectTwoCities(String cityName, Pageable pageble);
}