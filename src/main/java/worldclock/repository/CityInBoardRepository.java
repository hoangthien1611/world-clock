package worldclock.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import worldclock.entity.City;
import worldclock.entity.CityInBoard;

public interface CityInBoardRepository extends CrudRepository<CityInBoard, Integer> {

	List<CityInBoard> findBySessionId(String sessionId);
	CityInBoard findBySessionIdAndOrdinalNumber(String sessionId, Integer ordinalNumber);
	CityInBoard findBySessionIdAndCity(String sessionId, City city);
}
