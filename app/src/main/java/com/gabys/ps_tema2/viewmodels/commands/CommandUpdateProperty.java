package com.gabys.ps_tema2.viewmodels.commands;

import android.content.Context;

import com.gabys.ps_tema2.model.Persistence.PropertyPersistence;
import com.gabys.ps_tema2.model.Property;

public class CommandUpdateProperty implements ICommand {
    private Property property;
    private PropertyPersistence propertyPersistence;

    public CommandUpdateProperty(Context context) {
        this.propertyPersistence = new PropertyPersistence(context);
    }

    @Override
    public void Execute() {
        propertyPersistence.updateProperty(property);
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
