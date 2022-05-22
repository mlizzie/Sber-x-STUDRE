package ru.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CityUtils {

    public static List<City> parse(File file) {

        List<City> cityList = new ArrayList<>();
        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                cityList.add(parse(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public static City parse(String line) {

        int index = 0;

        City city = new City();
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        while (scanner.hasNext()) {
            String data = scanner.next();
            switch (index) {
                case 0:break;
                case 1: city.setName(data); break;
                case 2: city.setRegion(data);break;
                case 3: city.setDistrict(data);break;
                case 4: city.setPopulation(data);break;
                case 5: city.setFoundation(data);break;
                default:throw new RuntimeException("Некорректные данные:" + data);
            }
            index++;
        }
        return (city);
    }

    public static void SortName(List<City> cityList){

        Collections.sort(cityList,(city1, city2)-> city1.getName().toLowerCase().compareTo(city2.getName().toLowerCase()) );
    }

    public static void SortNameAndDistrict(List<City> cityList){

        Collections.sort(cityList,(city1, city2)-> {
            int result = city1.getDistrict().compareTo(city2.getDistrict());
            if(result == 0) {
               return city1.getName().compareTo(city2.getName());
            }
            return result;
        });
    }

    public static void print(List<City> cityList){
        for (City city : cityList) {
            System.out.println(city);
        }
    }
}
