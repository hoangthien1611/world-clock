package worldclock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import worldclock.entity.CityInBoard;
import worldclock.service.CityInBoardService;

@RestController
public class BoardController {

	@Autowired
	CityInBoardService boardService;

	@PostMapping("/addCityIntoBoard")
	public void addCityIntoBoard(@RequestBody CityInBoard board) {
		
		boardService.addCityIntoBoard(board);
	}

	@PutMapping("/swapCitiesInBoard")
	public void swapCitiesInBoard(@RequestBody CityInBoard[] board) {
		
		boardService.swapCitiesInBoard(board);
	}

	@GetMapping("/getBoard/{sessionId}")
	public List<CityInBoard> getBoard(@PathVariable String sessionId) {
		
		return boardService.getBoard(sessionId);
	}
	
	@DeleteMapping("deleteCityFromBoard/{sessionId}/{ordinalNumber}")
	public void deleteCityFromBoard(@PathVariable String sessionId, @PathVariable Integer ordinalNumber) {
		
		boardService.deleteCityFromBoard(sessionId, ordinalNumber);
		
	}

}
