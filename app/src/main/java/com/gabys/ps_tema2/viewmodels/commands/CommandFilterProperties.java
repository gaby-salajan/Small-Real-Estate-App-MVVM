package com.gabys.ps_tema2.viewmodels.commands;

import com.gabys.ps_tema2.model.Property;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommandFilterProperties implements ICommand{

    private ArrayList<Property> propertiesList;
    private Float minPrice;
    private Float maxPrice;
    private String location;
    private String type;
    private String roomsNo;

    public CommandFilterProperties() {
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoomsNo(String roomsNo) {
        this.roomsNo = roomsNo;
    }

    public void setPropertiesList(ArrayList<Property> propertiesList) {
        this.propertiesList = propertiesList;
    }

    @Override
    public void Execute() {
        ArrayList<Property> filteredProperties = new ArrayList<>();

        for (Property p : propertiesList) {
            if (roomsNo != null && !roomsNo.isEmpty()) {
                if(roomsNo.equals("Toate")){
                    filteredProperties.add(p);
                    continue;
                }
                if(roomsNo.equals("4+")){
                    if(p.getRoomsNo() >= 4){
                        filteredProperties.add(p);
                    }
                    continue;
                }

                if(Integer.parseInt(roomsNo) == p.getRoomsNo()){
                    filteredProperties.add(p);
                }

            }
        }

        filteredProperties = filteredProperties.stream()
                .filter(p -> p.getLocation().equals(location) || location.equals("Toate"))
                .filter(p -> p.getType().equals(type) || type.equals("Toate"))
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toCollection(ArrayList::new));

        propertiesList.clear();
        propertiesList.addAll(filteredProperties);
    }

    public ArrayList<Property> getPropertiesList() {
        return propertiesList;
    }
}
