package com.gabys.ps_tema2.viewmodels;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.viewmodels.commands.CommandFetchProperties;
import com.gabys.ps_tema2.viewmodels.commands.CommandFilterProperties;
import com.gabys.ps_tema2.viewmodels.commands.CommandLogin;
import com.gabys.ps_tema2.viewmodels.commands.CommandShowFilterDialog;
import com.gabys.ps_tema2.viewmodels.commands.CommandShowLoginDialog;
import com.gabys.ps_tema2.viewmodels.commands.ICommand;

import java.util.ArrayList;
import java.util.Observable;
import java.util.stream.Collectors;

public class ClientViewModel extends Observable implements IViewModel {
    protected ArrayList<Property> propertiesList;
    protected ICommand commandFetchProperties;
    protected ICommand commandShowFilterDialog;
    protected ICommand commandFilter;

    protected ICommand commandShowLoginDialog;
    protected ICommand commandLogin;


    public ClientViewModel(Context context, FragmentManager fragmentManager){
        this.propertiesList = new ArrayList<>();

        /*propertyPersistence.createPropertyTable();
        propertyPersistence.insertDummyValues();*/
        /*userPersistence.createUserTable();
        */
        //new UserPersistence(context).insertDummyValues();


        commandFetchProperties = new CommandFetchProperties(context, propertiesList);
        fetchProperties();

        commandFilter = new CommandFilterProperties();
        commandLogin = new CommandLogin(context);

        commandShowFilterDialog = new CommandShowFilterDialog(this, fragmentManager, (CommandFilterProperties) commandFilter);
        commandShowLoginDialog = new CommandShowLoginDialog(this, fragmentManager, (CommandLogin) commandLogin);
    }
    @Override
    public void refresh(){
        setChanged();
        notifyObservers();
    }

    private void fetchProperties(){
        commandFetchProperties.Execute();
        propertiesList = (ArrayList<Property>) propertiesList.stream().filter(property -> property.isAvailable()).collect(Collectors.toList());
        refresh();
    }

    public ArrayList<Property> getPropertiesList() {
        return propertiesList;
    }

    public void onLoginButtonClick(View v){
        commandShowLoginDialog.Execute();
    }

    public void onFilterButtonClick(View v){
        commandFetchProperties.Execute();
        ((CommandFilterProperties)commandFilter).setPropertiesList(propertiesList);
        commandShowFilterDialog.Execute();
    }
}
