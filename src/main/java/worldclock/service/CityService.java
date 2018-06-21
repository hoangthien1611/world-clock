package worldclock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worldclock.entity.City;
import worldclock.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	CityRepository cityRepository;
	
	public List<City> getCitiesByName(String cityName) {
		List<City> cities = new ArrayList<>();
		cityRepository.findAll().forEach(cities::add);

		cityName = cityName.toLowerCase();
		int lengthOfSearchingString = cityName.length();
		int currentLength = lengthOfSearchingString / 2 + 1;
		int maxNumberOfResult = 10;
		int index = -1;
		City[] citiesSearched = new City[maxNumberOfResult];
		int[] lengthOfCommonSubstring = new int[maxNumberOfResult];

		for (City city : cities) {
			int indexOfCityInString = city.getCityName().indexOf('/') + 1;
			String stringCompared = city.getCityName().substring(indexOfCityInString).toLowerCase();
			int tmp = longestCommonSubstring(cityName, stringCompared, cityName.length() - 1,
					stringCompared.length() - 1, new int[cityName.length()][stringCompared.length()]);
			if (tmp >= currentLength) {
				citiesSearched[++index % maxNumberOfResult] = city;
				lengthOfCommonSubstring[(index + 1) % maxNumberOfResult] = tmp;
				currentLength = tmp;
			}
		}

		for (int i = 0; i < maxNumberOfResult - 1; i++) {
			for (int j = 0; j < maxNumberOfResult - i - 1; j++) {
				if (lengthOfCommonSubstring[j] < lengthOfCommonSubstring[j + 1]) {
					int tmp = lengthOfCommonSubstring[j];
					lengthOfCommonSubstring[j] = lengthOfCommonSubstring[j + 1];
					lengthOfCommonSubstring[j + 1] = tmp;

					City tmpCity = citiesSearched[j];
					citiesSearched[j] = citiesSearched[j + 1];
					citiesSearched[j + 1] = tmpCity;
				}
			}
		}
		
		List<City> result = new ArrayList<City>();
		for (City city : citiesSearched) {
			if (city != null)
				result.add(city);
		}
		
		return result;
	}
	
	/**
	 * lcs(str1, str2, n1, n2) = Max { 
	 *                               1 + lcs(str1, str2, n1 - 1, n2 - 2),
	 *                               lcs(str1, str2, n1 - 1, n2), 
	 *                               lcs(str1, str2, n1, n2 - 1) 
	 *                               }
	 * 
	 * @return longest common substring of two strings
	 * @param string1
	 * @param string2
	 * @param n1
	 * @param n2
	 * @param memoization
	 */
	public static int longestCommonSubstring(String string1, String string2, int n1, int n2, int[][] memoization) {
		if (n1 < 0 || n2 < 0)
			return 0;
		if (memoization[n1][n2] != 0)
			return memoization[n1][n2];
		if (string1.charAt(n1) == string2.charAt(n2))
			memoization[n1][n2] = 1 + longestCommonSubstring(string1, string2, n1 - 1, n2 - 1, memoization);
		else {
			memoization[n1][n2] = longestCommonSubstring(string1, string2, n1 - 1, n2, memoization);
			int tmp = longestCommonSubstring(string1, string2, n1, n2 - 1, memoization);
			if (tmp > memoization[n1][n2])
				memoization[n1][n2]= tmp;
		}
		return memoization[n1][n2];
	}
}
