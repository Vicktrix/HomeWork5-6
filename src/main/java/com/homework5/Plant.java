package com.homework5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
U každé rostliny budeme mít uložen:
    název (name),
    poznámky (notes),
    datum, kdy byly zasazena (planted),
    datum poslední zálivky (watering)
    běžnou frekvenci zálivky ve dnech (frequency of watering)

 */
public class Plant{
    private String name;
    private List<String> notes = new ArrayList<>();
    private LocalDate planted;
    private LocalDate watering;
    private int daysPerWatering;

    public Plant(String name,List<String> notes,LocalDate planted,LocalDate watering,int daysPerWatering) throws PlantException{
        if(daysPerWatering <= 0) throw new PlantException("daysPerWatering must be greater than 0, name -"+name);
        if(planted.isAfter(watering)) throw new PlantException("watering data can't be earlier than planted data, name -"+name);
//        this.name=name.trim();
        this.name=Settings.parser(name);
        notes.forEach(n -> this.notes.add(Settings.parser(n.trim())));
//        this.notes.addAll(notes);
        this.planted=planted;
        this.watering=watering;
        this.daysPerWatering=daysPerWatering;
    }

    public Plant(String name,LocalDate planted,int daysPerWatering) throws PlantException{
//        this(name, new ArrayList<>(), planted, LocalDate.now(), daysPerWatering);
        this(name, Arrays.asList("bez poznamky"), planted, LocalDate.now(), daysPerWatering);
    }

    public Plant(String name) throws PlantException{
        this(name, LocalDate.now(), 7);
    }
    
    public List<String> getWateringInfo() {
        return Arrays.asList(name, watering.toString(), 
                watering.plusDays(daysPerWatering).toString());
    }
    public void addToNotes(String str) {
        notes.add(str);
    }
    public List<String> getNotes(){
        return notes;
    }
    public String getName(){
        return name;
    }
    public LocalDate getPlanted(){
        return planted;
    }
    public LocalDate getWatering(){
        return watering;
    }
    public int getDaysPerWatering(){
        return daysPerWatering;
    }

    @Override
    public String toString(){
//        return Settings.parser(name)+"; "+Settings.parser(notes)+"; "
//                +daysPerWatering+"; "+planted+"; "+watering;
        return ""+name+"; "+Settings.parser(notes)+"; "
                +daysPerWatering+"; "+planted+"; "+watering;
    }
    
}
