package org.bkcloud.fleet.workflow.controller;

import org.bkcloud.fleet.workflow.entity.City;
import org.bkcloud.fleet.workflow.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

/**
 * a controller to display city information
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityService cityService;

    /**
     * a page to display all the cities
     * @return a view rendered by freemarker template with a list of city from JPA
     */
    @GetMapping("/all")
    public ModelAndView displayCities() {
        List<City> cities = cityService.findAll();
        HashMap<String, List<City>> map = new HashMap<>();
        map.put("cities", cities);
        return new ModelAndView("showCities", map);
    }
}
