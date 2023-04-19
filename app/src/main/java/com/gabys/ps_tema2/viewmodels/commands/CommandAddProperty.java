package com.gabys.ps_tema2.viewmodels.commands;

import android.content.Context;

import com.gabys.ps_tema2.model.Persistence.PropertyPersistence;
import com.gabys.ps_tema2.model.Property;

public class CommandAddProperty implements ICommand{
    private Property property;
    private PropertyPersistence propertyPersistence;

    public CommandAddProperty(Context context) {
        this.propertyPersistence = new PropertyPersistence(context);
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public void Execute() {
        propertyPersistence.addProperty(property);
    }
}
