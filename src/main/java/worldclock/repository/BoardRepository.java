package worldclock.repository;

import org.springframework.data.repository.CrudRepository;

import worldclock.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Integer> {

}
