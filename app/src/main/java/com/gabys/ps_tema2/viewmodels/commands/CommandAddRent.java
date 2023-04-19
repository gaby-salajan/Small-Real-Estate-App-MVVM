package com.gabys.ps_tema2.viewmodels.commands;

import android.content.Context;

import com.gabys.ps_tema2.model.Persistence.PropertyPersistence;
import com.gabys.ps_tema2.model.Persistence.RentPersistence;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.model.Rent;
import com.gabys.ps_tema2.model.User;

public class CommandAddRent implements ICommand {

    private RentPersistence rentPersistence;

    private PropertyPersistence propertyPersistence;

    private Property property;
    private User client;

    public CommandAddRent(Context context, Property property) {
        rentPersistence = new RentPersistence(context);
        propertyPersistence = new PropertyPersistence(context);

        this.property = property;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public void Execute() {
        property.setAvailable(false);
        System.out.println(client + " " + property);

        Rent rent = new Rent(client, property);

        rentPersistence.addRent(rent);
        propertyPersistence.updateProperty(property);
    }
}
