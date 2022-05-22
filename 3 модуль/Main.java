package ru.test;

import java.io.File;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        File file = new File("src/main/resources/city_ru.csv");
        List<City> cityList = CityUtils.parse(file);
        System.out.println(CityUtils.getMaxPopulation(cityList));

    }
}
