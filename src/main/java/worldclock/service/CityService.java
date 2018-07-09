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
	
	public List<City> getCitesByName(String cityName) {
		
		cityName = cityName.replace('_', ' ');
		
		List<City> cities = new ArrayList<>();

		cityRepository.findAll().forEach(cities::add);

		cityName = cityName.toLowerCase();

		int lengthOfSearchingString = cityName.length();
		int currentLength = lengthOfSearchingString / 2 + 1;
		int maxNumberOfResult = 10;
		int index = -1;

		int[] lengthOfCommonSubstring = new int[maxNumberOfResult];

		City[] citiesSearched = new City[maxNumberOfResult];
		

		for (City city : cities) {
			
			int indexOfCityInString = city.getCityName().indexOf('/') + 1;

			String stringCompared = city.getCityName().substring(indexOfCityInString).toLowerCase();

			//int tmp = longestCommonSubstring(cityName, stringCompared, cityName.length() - 1,
			//		stringCompared.length() - 1, new int[cityName.length()][stringCompared.length()]);
			
			int tmp = longestCommonSubstringTabutation(cityName, stringCompared);
			
			if (tmp >= currentLength) {

				index++;

				citiesSearched[index % maxNumberOfResult] = city;
				lengthOfCommonSubstring[index % maxNumberOfResult] = tmp;
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

				} else if (lengthOfCommonSubstring[j] == lengthOfCommonSubstring[j + 1]
						&& lengthOfCommonSubstring[j] != 0
						&& Math.abs(citiesSearched[j].getCityName().length() - cityName.length()) > Math
								.abs(citiesSearched[j + 1].getCityName().length() - cityName.length())) {

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
			
			if (city != null) {
				result.add(city);
			}

		}
		
		return result;
	}
	
	/**
	 * lcs(str1, str2, lengthOfString1, lengthOfString2) = Max { 
	 *                               1 + lcs(str1, str2, lengthOfString1 - 1, lengthOfString2 - 2),
	 *                               lcs(str1, str2, lengthOfString1 - 1, lengthOfString2), 
	 *                               lcs(str1, str2, lengthOfString1, lengthOfString2 - 1) 
	 *                               }
	 * 
	 * @return longest common substring of two strings
	 * @param string1
	 * @param string2
	 * @param lengthOfString1
	 * @param lengthOfString2
	 * @param memoization
	 */
	public static int longestCommonSubstring(String string1, String string2, int lengthOfString1, int lengthOfString2, int[][] memoization) {
		
		if (lengthOfString1 < 0 || lengthOfString2 < 0) {
			
			return 0;

		}
		
		if (memoization[lengthOfString1][lengthOfString2] != 0) {

			return memoization[lengthOfString1][lengthOfString2];			

		}
		
		if (string1.charAt(lengthOfString1) == string2.charAt(lengthOfString2)) {

			memoization[lengthOfString1][lengthOfString2] = 1 + longestCommonSubstring(string1, string2, lengthOfString1 - 1, lengthOfString2 - 1, memoization);
		} else {
			
			memoization[lengthOfString1][lengthOfString2] = longestCommonSubstring(string1, string2, lengthOfString1 - 1, lengthOfString2, memoization);

			int tmp = longestCommonSubstring(string1, string2, lengthOfString1, lengthOfString2 - 1, memoization);
			
			if (tmp > memoization[lengthOfString1][lengthOfString2]) {
			
				memoization[lengthOfString1][lengthOfString2]= tmp;

			}
			
		}

		return memoization[lengthOfString1][lengthOfString2];

	}
	
	int longestCommonSubstringTabutation(String string1, String string2) {
		
		int[][] memoization = new int[string1.length()][string2.length()];
		
		int lengthOfString1 = string1.length();
		int lengthOfString2 = string2.length();
		
		for (int i = 0; i < lengthOfString1; i++) {
			
			for (int j = 0; j < lengthOfString2; j++) {
				
				if (string1.charAt(i) == string2.charAt(j)) {
					
					if (i != 0 && j !=0 ) {
						
						memoization[i][j] = 1 + memoization[i - 1][j - 1];
						
					} else {
						
						memoization[i][j] = 1;
						
					}
					
					
				} else {
					
					if (i != 0) {	
						
						memoization[i][j] = memoization[i - 1][j];
					
					}
					
					if (j != 0 && memoization[i][j] < memoization[i][j - 1]) {
						
						memoization[i][j] = memoization[i][j - 1];
					
					}
				
				}
					
			}
			
		}
		
		return memoization[lengthOfString1 - 1][lengthOfString2 - 1];
		
	}

	public List<City> searchCityWithExactName(String key) {

		return cityRepository.searchCityWithExactName("%" + key.toLowerCase().replace('_', ' ') + "%");
		
	}
}
