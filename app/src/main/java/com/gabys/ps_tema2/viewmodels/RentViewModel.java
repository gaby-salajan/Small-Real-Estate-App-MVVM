package com.gabys.ps_tema2.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.viewmodels.commands.CommandAddRent;
import com.gabys.ps_tema2.viewmodels.commands.CommandFetchClients;
import com.gabys.ps_tema2.viewmodels.commands.ICommand;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Observable;
import java.util.stream.Collectors;

import kotlin.jvm.internal.IntCompanionObject;

public class RentViewModel extends Observable implements IViewModel{

    private final Context context;
    private Property property;
    private User client;

    private ArrayList<User> clients;

    private ICommand commandFetchClients;

    private ICommand commandAddRent;

    public RentViewModel(Context context) {
        this.context = context;
        Gson gson = new Gson();
        this.clients = new ArrayList<>();
        this.client = new User();
        this.property = gson.fromJson(((Activity)context).getIntent().getStringExtra("property"), Property.class);

        commandAddRent = new CommandAddRent(context, property);

        commandFetchClients = new CommandFetchClients(context, clients);
        commandFetchClients.Execute();
    }

    public String getPropertyImageURL() {
        return property.getImageURL();
    }

    public String getPropertyTitle() {
        return property.getTitle();
    }

    public ArrayList<User> getClients() {
        return clients;
    }


    public void onYesButtonClick(View v){
        refresh();
        ((CommandAddRent)commandAddRent).setClient(client);
        commandAddRent.Execute();
        ((Activity)context).finish();
    }

    @Override
    public void refresh() {
        setChanged();
        notifyObservers();
    }

    public void setClient(User selectedClient) {
        this.client = selectedClient;
    }
}
