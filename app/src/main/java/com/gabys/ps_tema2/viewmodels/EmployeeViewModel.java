package com.gabys.ps_tema2.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.gabys.ps_tema2.model.PropertiesListObject;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.view.AddPropertyActivity;
import com.gabys.ps_tema2.view.AddUserActivity;
import com.gabys.ps_tema2.view.ClientActivity;
import com.gabys.ps_tema2.view.ExportPropertiesActivity;
import com.gabys.ps_tema2.viewmodels.commands.CommandFetchClients;
import com.gabys.ps_tema2.viewmodels.commands.CommandFetchProperties;
import com.gabys.ps_tema2.viewmodels.commands.CommandFilterProperties;
import com.gabys.ps_tema2.viewmodels.commands.CommandShowFilterDialog;
import com.gabys.ps_tema2.viewmodels.commands.ICommand;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Observable;

public class EmployeeViewModel extends Observable implements IViewModel {
    private User user;
    protected ArrayList<Property> propertiesList;

    private ArrayList<User> clientsList;

    protected ICommand commandFetchProperties;

    protected ICommand commandFetchClients;
    protected ICommand commandShowFilterDialog;
    protected ICommand commandFilter;

    private Context context;

    public EmployeeViewModel(Context context, FragmentManager fragmentManager){
        this.context = context;
        this.propertiesList = new ArrayList<>();
        this.clientsList = new ArrayList<>();

        Gson gson = new Gson();
        this.user = gson.fromJson(((Activity)context).getIntent().getStringExtra("user"), User.class);

        commandFetchProperties = new CommandFetchProperties(context, propertiesList);
        fetchProperties();

        commandFetchClients = new CommandFetchClients(context, clientsList);
        fetchClients();

        commandFilter = new CommandFilterProperties();
        commandShowFilterDialog = new CommandShowFilterDialog(this, fragmentManager, (CommandFilterProperties) commandFilter);

    }

    @Override
    public void refresh(){
        setChanged();
        notifyObservers();
    }
    public void fetchClients() {
        commandFetchClients.Execute();
    }
    public void fetchProperties(){
        commandFetchProperties.Execute();
    }

    public ArrayList<Property> getPropertiesList() {
        return propertiesList;
    }

    public User getLoggedUser() {
        return user;
    }

    public void onFilterButtonClick(View v){
        commandFetchProperties.Execute();
        ((CommandFilterProperties)commandFilter).setPropertiesList(propertiesList);
        commandShowFilterDialog.Execute();
    }

    public void onLogoutButtonClick(View v){
        Intent intent = new Intent(context, ClientActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public void onAddPropertyButtonClick(View v){
        Intent intent = new Intent(context, AddPropertyActivity.class);
        context.startActivity(intent);
    }

    public void onAddClientButtonClick(View v){
        Intent intent = new Intent(context, AddUserActivity.class);
        intent.putExtra("user", new Gson().toJson(user));

        context.startActivity(intent);
    }

    public void onExportPropertiesButtonClick (View v){
        Intent intent = new Intent(context, ExportPropertiesActivity.class);
        Gson gson = new Gson();
        PropertiesListObject propertiesListObject = new PropertiesListObject(propertiesList);
        intent.putExtra("propertiesList", gson.toJson(propertiesListObject));

        context.startActivity(intent);
    }


    public void deleteProperty(Property property) {
        propertiesList.remove(property);
    }

    public ArrayList<User> getClients() {
        return clientsList;
    }

    public void deleteClient(User user) {
        clientsList.remove(user);
    }
}
