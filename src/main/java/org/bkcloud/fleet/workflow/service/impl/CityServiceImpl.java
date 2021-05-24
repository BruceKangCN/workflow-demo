package org.bkcloud.fleet.workflow.service.impl;

import org.bkcloud.fleet.workflow.entity.City;
import org.bkcloud.fleet.workflow.repository.ICityRepository;
import org.bkcloud.fleet.workflow.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * city service implementation class
 */
@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private ICityRepository cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
