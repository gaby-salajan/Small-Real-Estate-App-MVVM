package com.gabys.ps_tema2.viewmodels.commands;

import android.content.Context;

import com.gabys.ps_tema2.model.Persistence.PropertyPersistence;
import com.gabys.ps_tema2.model.Property;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CommandFetchProperties implements ICommand{
    PropertyPersistence propertyPersistence;
    Context context;
    ArrayList<Property> propertiesList;

    public CommandFetchProperties(Context context, ArrayList<Property> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @Override
    public void Execute() {
        propertyPersistence = new PropertyPersistence(context);

        ArrayList<Property> properties = propertyPersistence.getProperties();

        properties = (ArrayList<Property>) properties.stream()
                .sorted(Comparator.comparing(Property::getLocation)
                        .thenComparing(Property::getPrice))
                .collect(Collectors.toList());

        propertiesList.clear();
        propertiesList.addAll(properties);
    }

}
