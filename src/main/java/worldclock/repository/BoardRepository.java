package worldclock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import worldclock.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Integer> {

	List<Board> findBySessionId(String sessionId);

	Board findBySessionIdAndOrdinalNumber(String sessionId, Integer ordinalNumber);

}
