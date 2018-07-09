package worldclock.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import worldclock.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, String> {
	
	@Query("select c.cityId from City c \r\n" + 
			"where c.cityId != ?1 and country not in \r\n" + 
			"(select ct.country from City ct where ct.cityId = ?1)\r\n" + 
			"order by random()")
	List<Integer> selectTwoCities(Integer cityId, Pageable pageble);
	
	@Query("select c from City c where LOWER(c.cityName) like ?1")
	List<City> searchCityWithExactName(String key);
	
}