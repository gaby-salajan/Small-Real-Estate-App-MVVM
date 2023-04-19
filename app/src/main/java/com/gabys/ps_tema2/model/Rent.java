package com.gabys.ps_tema2.model;

public class Rent {
    private int id;
    private int clientId;
    private int propertyId;

    public Rent(int id, int clientId, int propertyId) {
        this.id = id;
        this.clientId = clientId;
        this.propertyId = propertyId;
    }

    public Rent(int clientId, int propertyId) {
        this.clientId = clientId;
        this.propertyId = propertyId;
    }

    public Rent() {

    }

    public Rent(User client, Property property) {
        this.clientId = client.getId();
        this.propertyId = property.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }
}
