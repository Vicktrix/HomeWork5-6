package com.homework5;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 @author vitck
 */
public class Main{

    public static void main(String[] args){
        ListOfPlant listOfPlants = new ListOfPlant();
        List<Plant> plants = listOfPlants.getListOfPlants();
        try{
            Plant fialka = new Plant("Fialka",Arrays.asList("1","Popis fialky - je fialova a hezka"),
                    LocalDate.of(2021,Month.JANUARY,1),LocalDate.of(2021,Month.MAY,12),3);
            plants.add(fialka);
            Plant vanocni = new Plant("Vanocni",Arrays.asList("Hvezda","z poznamkou \"bez poznamky\""),
                    LocalDate.of(2021,Month.APRIL,1),LocalDate.of(2021,Month.MAY,10),4);
            plants.add(vanocni);
            Plant bazalka = new Plant("Bazalka",Arrays.asList("v kuchyni"),
                    LocalDate.of(2021,Month.SEPTEMBER,4),LocalDate.of(2021,Month.SEPTEMBER,4),3);
            plants.add(bazalka);
            Plant kaktus = new Plant("Kaktus",Arrays.asList("Pozor! ostry"),
                    LocalDate.of(2020,Month.JULY,14),LocalDate.of(2021,Month.SEPTEMBER,4),33);
            plants.add(kaktus);
            kaktus.addToNotes("Ne zalivat!");
            Plant divneJmeno = new Plant("  Divne; Jmeno;",Arrays.asList(";Pro kontrolu"," "," neco; "),
                    LocalDate.of(2020,Month.OCTOBER,11),LocalDate.of(2021,Month.SEPTEMBER,4),13);
            plants.add(divneJmeno);
            Plant kratky = new Plant("Ahoj");
            plants.add(kratky);
            Plant dalsi = new Plant("Dalsi",LocalDate.now(),4);
            plants.add(dalsi);
            dalsi.addToNotes("Pridame poznamku");
            dalsi.addToNotes("a Dalsi poznamku");
            dalsi.addToNotes("az tri krat");
            Plant wrongDate = new Plant("Test 3",Arrays.asList("popis"),
                    LocalDate.of(2021,Month.APRIL,24),LocalDate.of(2021,Month.APRIL,4),5);
            plants.add(wrongDate);
        }catch(PlantException ex){
            System.out.println("Wrong arguments in Plant-constructor : "+ex);
        }
        try{
            Plant wrongAgain = new Plant("Next Test",Arrays.asList("popis"),
                    LocalDate.of(2021,Month.APRIL,24),LocalDate.of(2020,Month.APRIL,4),5);
            plants.add(wrongAgain);
        }catch(PlantException ex){
            System.out.println("Wrong arguments in Plant-constructor : "+ex);
        }
        try{
            Plant wrongNext = new Plant("Watering",Arrays.asList("popis"),
                    LocalDate.of(2021,Month.APRIL,24),LocalDate.of(2022,Month.APRIL,4),-2);
            plants.add(wrongNext);
        }catch(PlantException ex){
            System.out.println("Wrong arguments in Plant-construktor : "+ex);
        }
        System.out.println("\n\t\tShow all Plants description \n");
        printAllPlantDescription(plants);
        System.out.println("\n\t\tShow all WateringInfo \n");
        printAllPlantWateringInfo(plants);
        System.out.println("\n\t\t Save to File : "+Settings.toFile);
        ListOfPlant.writeToNewFile(Settings.toFile,plants);
        System.out.println("Read from File : "+Settings.toFile);
        listOfPlants.getListOfPlantsFromFile().addAll(ListOfPlant.readFromFile(Settings.toFile));
        System.out.println("\n\t\tShow all Plants description \n");
        printAllPlantDescription(listOfPlants.getListOfPlantsFromFile());
        System.out.println("\n\t\t Try read file with rwong format ");
        plants = ListOfPlant.readFromFile(Settings.wrongFormatFile);
        printAllPlantDescription(plants);
        System.out.println("\n\tAdd Plant to list, remove from list and Sort list\n");
        plants = ListOfPlant.readFromFile(Settings.toFile);
        try{
            Plant ruze = new Plant("Rose",Arrays.asList("Big and Red"),
                    LocalDate.of(2021,Month.APRIL,24),LocalDate.of(2022,Month.APRIL,4),2);
            plants.add(ruze);
        }catch(PlantException ex){
            System.out.println("Wrong arguments in Plant-construktor : "+ex);
        }
        try{
            Plant ruze = new Plant("Rose blue",Arrays.asList("Big and Blue"),
                    LocalDate.of(2021,Month.APRIL,24),LocalDate.of(2021,Month.NOVEMBER,4),4);
            plants.add(ruze);
        }catch(PlantException ex){
            System.out.println("Wrong arguments in Plant-construktor : "+ex);
        }
        try{
            Plant ruze = new Plant("Remove",Arrays.asList("This for remove"),
                    LocalDate.of(2021,Month.APRIL,24),LocalDate.of(2021,Month.JUNE,4),4);
            plants.add(ruze);
        }catch(PlantException ex){
            System.out.println("Wrong arguments in Plant-construktor : "+ex);
        }
        try{
            Plant ruze = new Plant("Rose Next One",Arrays.asList("R","O","S","E"),
                    LocalDate.of(2023,Month.APRIL,24),LocalDate.of(2023,Month.NOVEMBER,4),41);
            plants.add(ruze);
        }catch(PlantException ex){
            System.out.println("Wrong arguments in Plant-construktor : "+ex);
        }
        System.out.println("\n \tbefore remove\n");
        printAllPlantDescription(plants);
        ListOfPlant.appendsToFile(Settings.fromFile,plants);
        Plant plantToRemove = plants.stream().filter(p -> p.getName().contains("Rem")).findFirst().get();
        plants.remove(plantToRemove);
        Plant plantToRemove2 = plants.stream().filter(p -> p.getName().contains("Ah")).findFirst().get();
        plants.remove(plantToRemove2);
        Collections.sort(plants,(a,b) -> a.getWatering().compareTo(b.getWatering()));
// after remove and sort append(for compare) to File        
//        ListOfPlant.appendsToFile(Settings.fromFile,plants);
        ListOfPlant.writeToNewFile(Settings.fromFile,plants);
        plants = ListOfPlant.readFromFile(Settings.fromFile);
// print result and compare version        
        System.out.println("\n \tafter remove by name, and sort by Watering, print two version for compare\n");
        printAllPlantDescription(plants);
        System.out.println("\n \t after sort by name\n");
        Collections.sort(plants,(a,b) -> a.getName().compareTo(b.getName()));
        printAllPlantDescription(plants);
    }
    public static void printAllPlantDescription(List<Plant> plants) {
        plants.forEach(System.out::println);
    }
    public static void printAllPlantWateringInfo(List<Plant> plants) {
        plants.forEach(p -> System.out.println(p.getWateringInfo()) );
    }
}
