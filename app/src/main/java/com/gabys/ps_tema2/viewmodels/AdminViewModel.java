package com.gabys.ps_tema2.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.gabys.ps_tema2.model.Persistence.PropertyPersistence;
import com.gabys.ps_tema2.model.Persistence.UserPersistence;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.view.AddUserActivity;
import com.gabys.ps_tema2.view.ClientActivity;
import com.gabys.ps_tema2.viewmodels.commands.CommandFetchClients;
import com.gabys.ps_tema2.viewmodels.commands.CommandFetchProperties;
import com.gabys.ps_tema2.viewmodels.commands.CommandFetchUsers;
import com.gabys.ps_tema2.viewmodels.commands.CommandFilterProperties;
import com.gabys.ps_tema2.viewmodels.commands.CommandShowFilterDialog;
import com.gabys.ps_tema2.viewmodels.commands.ICommand;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;
import java.util.stream.Collectors;

public class AdminViewModel extends Observable implements IViewModel{
    private Context context;
    private ArrayList<Property> propertiesList;
    private ArrayList<User> usersList;
    private User user;

    protected ICommand commandFetchProperties;

    protected ICommand commandFetchUsers;
    protected ICommand commandShowFilterDialog;
    protected ICommand commandFilter;

    public AdminViewModel(Context context, FragmentManager fragmentManager){
        this.context = context;
        this.propertiesList = new ArrayList<>();
        this.usersList = new ArrayList<>();

        Gson gson = new Gson();
        this.user = gson.fromJson(((Activity)context).getIntent().getStringExtra("user"), User.class);

        commandFetchProperties = new CommandFetchProperties(context, propertiesList);
        fetchProperties();

        commandFetchUsers = new CommandFetchUsers(context, usersList);
        fetchUsers();

        commandFilter = new CommandFilterProperties();
        commandShowFilterDialog = new CommandShowFilterDialog(this, fragmentManager, (CommandFilterProperties) commandFilter);
    }

    public void fetchProperties(){
        commandFetchProperties.Execute();
    }

    public void fetchUsers(){
        commandFetchUsers.Execute();
    }
    public void onFilterButtonClick(View v){
        commandFetchProperties.Execute();
        ((CommandFilterProperties)commandFilter).setPropertiesList(propertiesList);
        commandShowFilterDialog.Execute();
    }
    public void onAddUserButtonClick(View v){
        Intent intent = new Intent(context, AddUserActivity.class);
        intent.putExtra("user", new Gson().toJson(user));
        context.startActivity(intent);
    }

    public void onLogoutButtonClick(View v) {
        Intent intent = new Intent(context, ClientActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public void refresh() {
        setChanged();
        notifyObservers();
    }

    public ArrayList<Property> getProperties() {
        return propertiesList;
    }

    public ArrayList<User> getUsers() {
        return usersList;
    }
}
