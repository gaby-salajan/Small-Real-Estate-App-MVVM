package com.gabys.ps_tema2.model;

import java.util.ArrayList;

public class PropertiesListObject {
    private ArrayList<Property> propertiesList;

    public PropertiesListObject(ArrayList<Property> propertiesList) {
        this.propertiesList = propertiesList;
    }

    public ArrayList<Property> getPropertiesList() {
        return propertiesList;
    }

    public void setPropertiesList(ArrayList<Property> propertiesList) {
        this.propertiesList = propertiesList;
    }
}
