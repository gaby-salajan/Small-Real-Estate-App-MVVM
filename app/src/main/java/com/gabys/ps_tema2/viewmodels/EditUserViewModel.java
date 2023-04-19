package com.gabys.ps_tema2.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.viewmodels.commands.CommandUpdateUser;
import com.gabys.ps_tema2.viewmodels.commands.ICommand;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Observable;

public class EditUserViewModel extends Observable implements IViewModel{

    private final Context context;
    public MutableLiveData<String> userId;
    public MutableLiveData<String> userUsername;
    public MutableLiveData<String> userPassword;

    public MutableLiveData<String[]> roles;
    public MutableLiveData<Integer> position;

    private final ICommand commandUpdateUser;

    public EditUserViewModel(Context context) {
        this.context = context;

        Gson gson = new Gson();
        User user = gson.fromJson(((Activity)context).getIntent().getStringExtra("user"), User.class);

        roles = new MutableLiveData<>(context.getResources().getStringArray(R.array.user_roles));
        position = new MutableLiveData<>(user.getRole());


        userId = new MutableLiveData<>(String.valueOf(user.getId()));
        userUsername = new MutableLiveData<>(user.getUsername());
        userPassword = new MutableLiveData<>(user.getPassword());

        this.commandUpdateUser = new CommandUpdateUser(context);
    }

    public void onYesButtonClick(View v){
        refresh();

        User user = new User(
                Integer.parseInt(userId.getValue()),
                userUsername.getValue(),
                userPassword.getValue(),
                position.getValue());

        ((CommandUpdateUser)commandUpdateUser).setProperty(user);
        commandUpdateUser.Execute();
        ((Activity)context).finish();
    }

    public void onNoButtonClick(View v){
        ((Activity)context).finish();
    }

    @Override
    public void refresh() {
        setChanged();
        notifyObservers();
    }
}
