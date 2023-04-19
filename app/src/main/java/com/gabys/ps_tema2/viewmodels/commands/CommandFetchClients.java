package com.gabys.ps_tema2.viewmodels.commands;

import android.content.Context;

import com.gabys.ps_tema2.model.Persistence.PropertyPersistence;
import com.gabys.ps_tema2.model.Persistence.UserPersistence;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CommandFetchClients implements ICommand {
    private UserPersistence userPersistence;
    private Context context;
    private ArrayList<User> clientList;

    public CommandFetchClients(Context context, ArrayList<User> clientList) {
        this.context = context;
        this.clientList = clientList;
    }

    @Override
    public void Execute() {
        userPersistence = new UserPersistence(context);

        ArrayList<User> users = userPersistence.getClients();

        users = (ArrayList<User>) users.stream()
                .sorted(Comparator.comparing(User::getUsername))
                .collect(Collectors.toList());

        clientList.clear();
        clientList.addAll(users);
    }
}
