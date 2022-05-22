package ru.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.*;

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
                case 4: city.setPopulation(Integer.parseInt(data));break;
                case 5: city.setFoundation(data);break;
                default:throw new RuntimeException("Некорректные данные:" + data);
            }
            index++;
        }
        scanner.close();
        return (city);
    }

    public static void sortName(List<City> cityList){

        Collections.sort(cityList,(city1, city2)-> city1.getName().toLowerCase().compareTo(city2.getName().toLowerCase()) );
    }

    public static void sortNameAndDistrict(List<City> cityList){

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

    public static  String getMaxPopulation(List<City> cityList){

        City [] cityArray = new City[cityList.size()];
        cityArray = cityList.toArray(cityArray);

        int max = 0;
        int index = 0;

        for (int i = 0; i < cityArray.length; i++){
            if (max < cityArray[i].getPopulation()){
                index = i;
                max = cityArray[i].getPopulation();
            }
        }
        NumberFormat f = NumberFormat.getInstance();
        return ("[" + index + "] = " + f.format(max));
    }

    public static void sortRegion(List<City> cityList){

        Collections.sort(cityList,(city1, city2)-> city1.getRegion().compareTo(city2.getRegion()) );
    }

    public static void  printNumberOfCitiesByRegion(List<City> cityList)
    {
        sortRegion(cityList);
        String region;
        int count;
        int i = 0;

        while (i < cityList.size()){
            count = 0;
            region = cityList.get(i).getRegion();
            while ( i < cityList.size() && region.equals(cityList.get(i).getRegion())){
                count++;
                i++;
            }
            System.out.println(region + " " + count);
        }

    }
}
