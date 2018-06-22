package worldclock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import worldclock.entity.Board;
import worldclock.service.BoardService;

@RestController
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@PostMapping("/addCityIntoBoard")
	public void addCityIntoBoard(@RequestBody Board board) {
		boardService.addCityIntoBoard(board);
	}
	
	@PutMapping("/swapCitiesInBoard")
	public void swapCitiesInBoard(@RequestBody Board[] board) {
		boardService.swapCitiesInBoard(board);
	}
	
	@GetMapping("/getBoard")
	public List<Board> getBoard() {
		return boardService.getBoard();
	}
}
