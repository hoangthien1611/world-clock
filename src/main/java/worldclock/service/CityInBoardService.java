package worldclock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import worldclock.entity.CityInBoard;
import worldclock.model.CityInBoardDetail;
import worldclock.repository.CityInBoardRepository;
import worldclock.repository.CityRepository;

@Service
public class CityInBoardService {

	@Autowired
	CityInBoardRepository cityInBoardRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	UserService userService;

	public void addCityIntoBoard(CityInBoard city) {

		int maxOrdinalNumber = cityInBoardRepository.findBySessionId(city.getSessionId()).size();

		if (!userService.isLogin(city.getSessionId()) && maxOrdinalNumber >= 5) {

			return;

		}

		city.setOrdinalNumber(maxOrdinalNumber + 1);

		cityInBoardRepository.save(city);

	}

	public void addDefaultCitiesIntoBoard(String sessionId, Integer cityId) {

		CityInBoard city = new CityInBoard(null, 1, sessionId, cityId);

		cityInBoardRepository.save(city);

		List<Integer> cityIds = cityRepository.selectTwoCities(cityId, new PageRequest(0, 2));

		cityInBoardRepository.save(new CityInBoard(null, 2, sessionId, cityIds.get(0)));
		cityInBoardRepository.save(new CityInBoard(null, 3, sessionId, cityIds.get(1)));

	}

	public void swapCitiesInBoard(CityInBoard[] board) {

		String sessionId = board[0].getSessionId();

		Integer currentPosition = board[0].getOrdinalNumber();
		Integer nextPosition = board[1].getOrdinalNumber();

		List<CityInBoard> cities = (ArrayList<CityInBoard>) cityInBoardRepository.findBySessionId(sessionId);
		
		CityInBoard currentCity = null;

		for (CityInBoard city : cities) {

			Integer tmpPosition = city.getOrdinalNumber();

			if (currentPosition < nextPosition && tmpPosition > currentPosition && tmpPosition <= nextPosition) {

				city.setOrdinalNumber(tmpPosition - 1);

				cityInBoardRepository.save(city);

			} else if (currentPosition > nextPosition && tmpPosition < currentPosition && tmpPosition >= nextPosition) {

				city.setOrdinalNumber(tmpPosition + 1);

				cityInBoardRepository.save(city);

			} else if (currentPosition == tmpPosition) {
				currentCity = city;
			}

		}

		currentCity.setOrdinalNumber(nextPosition);

		cityInBoardRepository.save(currentCity);

	}

	public List<CityInBoardDetail> getBoard(String sessionId) {

		List<CityInBoard> cities = (ArrayList<CityInBoard>) cityInBoardRepository.findBySessionId(sessionId);

		quickSort(cities, 0, cities.size() - 1);

		List<CityInBoardDetail> citiesInBoard = new ArrayList<>();

		Integer homeCityId = userService.getHomeCityId(sessionId);

		for (CityInBoard city : cities) {

			boolean isHomeCity = city.getCity().getCityId() == homeCityId;

			citiesInBoard.add(new CityInBoardDetail(city, isHomeCity));

		}

		return citiesInBoard;
	}

	public static void quickSort(List<CityInBoard> cities, int left, int right) {

		if (left < right) {

			int seperatedPosition = separateCities(cities, left, right);

			quickSort(cities, left, seperatedPosition - 1);
			quickSort(cities, seperatedPosition + 1, right);

		}

	}

	public static int separateCities(List<CityInBoard> cities, int left, int right) {

		int lastIndex = left - 1;
		int pivot = cities.get(right).getOrdinalNumber();

		for (int index = left; index <= right; index++) {

			if (cities.get(index).getOrdinalNumber() <= pivot) {

				lastIndex++;

				CityInBoard tmp = new CityInBoard(cities.get(lastIndex));

				cities.get(lastIndex).copyValueFromOtherCity(cities.get(index));
				cities.get(index).copyValueFromOtherCity(tmp);

			}

		}

		return lastIndex;

	}

	public void deleteCityFromBoard(String sessionId, Integer ordinalNumber) {

		List<CityInBoard> boards = cityInBoardRepository.findBySessionId(sessionId);

		for (CityInBoard board : boards) {

			if (board.getOrdinalNumber() == ordinalNumber) {

				cityInBoardRepository.delete(board.getCityInBoardId());

			} else if (board.getOrdinalNumber() > ordinalNumber) {

				Integer newOrdinalNumber = board.getOrdinalNumber() - 1;

				board.setOrdinalNumber(newOrdinalNumber);

				cityInBoardRepository.save(board);

			}

		}

	}

}
