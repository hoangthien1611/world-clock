package worldclock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import worldclock.entity.City;
import worldclock.service.CityService;


@Controller
public class HomeController {
	
	@Autowired
	private CityService cityService;

	@RequestMapping("/search-city/{cityName}")
	@ResponseBody
	public List<City> getCitiesByName(@PathVariable String cityName) {
		return cityService.getCitiesByName(cityName);
	}
}