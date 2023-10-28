/*
Vytvoř třídu pro ukládání seznamu pokojových květin. 
Jako atribut bude mít kolekci, uchovávající objekty s informacemi o květinách. 
Chceme mít možnost vytvářet více seznamů a jednotlivé seznamy exportovat do souboru či je ze souboru načítat.
*/
package com.homework5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ListOfPlant{
    private List<Plant> listOfPlants = new ArrayList<>();
    private List<Plant> listOfPlantsFromFile = new ArrayList<>();
    private List<Plant> listOfPlantsToFile = new ArrayList<>();
    //Přidej metody pro přidání nové květiny, získání květiny na zadaném indexu a odebrání květiny ze seznamu.
    public void addPlant(Plant plant) {
        listOfPlants.add(plant);
    }
    public Plant getPlantByIndex(int index) {
        return listOfPlants.get(index);
    }
    public void removePlant(Plant plant) {
        listOfPlants.remove(plant);
    }
//Přidej do seznamu květin metodu pro načtení květin ze souboru. 
    public static List<Plant> readFromFile(String src) {
        List<Plant> list = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(src))) {
            int numOfLine = 1;
            Plant plant;
            while(sc.hasNextLine()) {
                plant = parseLineOfFile(sc.nextLine(), numOfLine);
                if(plant == null) {
                    System.out.println("Wrong parsing infile "+src+", in line "+(numOfLine++));
                    continue;
                }
                list.add(plant);
                numOfLine++;
            }
        } catch(FileNotFoundException ex) {
            System.out.println("Error in read File "+src+"\n "+ex);
        }
        return list;
    }
    public static void writeToNewFile(String src, List<Plant> list) {
        writeToFile(src,list,false);
    }
    public static void appendsToFile(String src, List<Plant> list) {
        writeToFile(src,list,true);
    }
    private static void writeToFile(String src, List<Plant> list, boolean appends) {
        try(PrintWriter toFile = new PrintWriter(new FileWriter(src, appends))) {
            list.forEach(toFile::println);
        } catch(IOException ex) {
          System.out.println("Error in write File "+src+"\n "+ex);
        }
    }
    private static Plant parseLineOfFile(String line, int numOfLine) {
        Plant plant = null;
        try{
            String[] args = line.split(Settings.delimiter);
            if(args.length != 5) throw new PlantException("Wrong delimiter or number of arguments in line "+numOfLine);
            plant=new Plant(args[0],
                    Arrays.asList(args[1].split(", ")),
                    LocalDate.parse(args[3]),
                    LocalDate.parse(args[4]),
                    Integer.parseInt(args[2]));
        }catch(PlantException ex){
//V případě chybného vstupu vyhoď výjimku.
            System.out.println("Error in parsing File of Plant, "+ex);
        } 
        return plant;
    }
    public List<Plant> getListOfPlants(){
        return listOfPlants;
    }
    public List<Plant> getListOfPlantsFromFile(){
        return listOfPlantsFromFile;
    }
    public List<Plant> getListOfPlantsToFile(){
        return listOfPlantsToFile;
    }
}