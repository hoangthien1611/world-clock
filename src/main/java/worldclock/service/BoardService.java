package worldclock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worldclock.entity.Board;
import worldclock.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	public void addCityIntoBoard(Board board) {
		int maxOrdinalNumber = (int) boardRepository.count();
		board.setOrdinalNumber(maxOrdinalNumber + 1);
		boardRepository.save(board);
	}

	public void swapCitiesInBoard(Board[] board) {
		int tmp = board[0].getOrdinalNumber();
		board[0].setOrdinalNumber(board[1].getOrdinalNumber());
		board[1].setOrdinalNumber(tmp);
		boardRepository.save(board[0]);
		boardRepository.save(board[1]);
	}

	public List<Board> getBoard() {
		List<Board> board = (ArrayList<Board>) boardRepository.findAll();
		//opp, misstake in reference
		quickSort(board, 0, board.size() - 1);	
		return board;
	}

	public static void quickSort(List<Board> a, int l, int r) {
		if (l < r) {
			int s = separate(a, l, r);
			quickSort(a, l, s - 1);
			quickSort(a, s + 1, r);
		}
	}

	public static int separate(List<Board> a, int l, int r) {
		int lastIndex = l - 1;
		int pivot = a.get(r).getOrdinalNumber();
		for (int i = l; i <= r; i++) {
			if (a.get(i).getOrdinalNumber() <= pivot) {
				lastIndex++;
				Board tmp = new Board(a.get(lastIndex));
				a.get(lastIndex).copyValueFromOtherCity(a.get(i));
				a.get(i).copyValueFromOtherCity(tmp);
			}
		}
		return lastIndex;
	}
}
