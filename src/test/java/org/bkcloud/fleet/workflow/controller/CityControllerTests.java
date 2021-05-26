package org.bkcloud.fleet.workflow.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.bkcloud.fleet.workflow.entity.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
public class CityControllerTests {

    @Autowired
    private Configuration configuration;

    private final Writer writer = new PrintWriter(System.out);

    @Test
    public void canProcessTemplate() throws IOException {
        Template template = configuration.getTemplate("displayCities.ftl");

        List<City> cities = new ArrayList<>();
        cities.add(new City(1, "Beijing", 21540000L));
        cities.add(new City(2, "Shanghai", 26230000L));

        HashMap<String, List<City>> map = new HashMap<>();
        map.put("cities", cities);

        Assertions.assertAll(
                () -> template.process(map, writer)
        );
    }
}
