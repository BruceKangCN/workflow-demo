package org.bkcloud.fleet.workflow.service;

import org.bkcloud.fleet.workflow.entity.City;

import java.util.List;

/**
 * city service interface
 */
public interface ICityService {

    List<City> findAll();

}
