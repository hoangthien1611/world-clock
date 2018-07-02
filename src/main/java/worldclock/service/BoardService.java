package worldclock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import worldclock.entity.Board;
import worldclock.repository.BoardRepository;
import worldclock.repository.CityRepository;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	CityRepository cityRepository;

	public void addCityIntoBoard(Board board) {

		int maxOrdinalNumber = boardRepository.findBySessionId(board.getSessionId()).size();

		board.setOrdinalNumber(maxOrdinalNumber + 1);

		boardRepository.save(board);

	}
	
	public void addDefaultCitiesIntoBoard(String sessionId, String cityName) {

		Board board = new Board(null, 1, sessionId, cityName);
		boardRepository.save(board);
		List<String> cityNames = cityRepository.selectTwoCities(cityName, new PageRequest(0, 2));
		boardRepository.save(new Board(null, 2, sessionId, cityNames.get(0)));
		boardRepository.save(new Board(null, 3, sessionId, cityNames.get(1)));
		
	}

	public void swapCitiesInBoard(Board[] board) {

		board[0] = boardRepository.findBySessionIdAndOrdinalNumber(board[0].getSessionId(),
				board[0].getOrdinalNumber());
		board[1] = boardRepository.findBySessionIdAndOrdinalNumber(board[1].getSessionId(),
				board[1].getOrdinalNumber());

		int tmp = board[0].getOrdinalNumber();

		board[0].setOrdinalNumber(board[1].getOrdinalNumber());
		board[1].setOrdinalNumber(tmp);

		boardRepository.save(board[0]);
		boardRepository.save(board[1]);

	}

	public List<Board> getBoard(String sessionId) {

		List<Board> cities = (ArrayList<Board>) boardRepository.findBySessionId(sessionId);

		quickSort(cities, 0, cities.size() - 1);

		return cities;

	}

	public static void quickSort(List<Board> cities, int left, int right) {

		if (left < right) {

			int seperatedPosition = separateCities(cities, left, right);

			quickSort(cities, left, seperatedPosition - 1);
			quickSort(cities, seperatedPosition + 1, right);

		}

	}

	public static int separateCities(List<Board> cities, int left, int right) {

		int lastIndex = left - 1;
		int pivot = cities.get(right).getOrdinalNumber();

		for (int index = left; index <= right; index++) {

			if (cities.get(index).getOrdinalNumber() <= pivot) {

				lastIndex++;

				Board tmp = new Board(cities.get(lastIndex));

				cities.get(lastIndex).copyValueFromOtherCity(cities.get(index));
				cities.get(index).copyValueFromOtherCity(tmp);

			}

		}

		return lastIndex;

	}

	public void deleteCityFromBoard(String sessionId, Integer ordinalNumber) {

		List<Board> boards = boardRepository.findBySessionId(sessionId);

		for (Board board : boards) {

			if (board.getOrdinalNumber() == ordinalNumber) {

				boardRepository.delete(board.getBoardId());

			} else if (board.getOrdinalNumber() > ordinalNumber) {

				Integer newOrdinalNumber = board.getOrdinalNumber() - 1;

				board.setOrdinalNumber(newOrdinalNumber);

				boardRepository.save(board);

			}

		}

	}

}
