package com.gabys.ps_tema2.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.viewmodels.commands.CommandAddUser;
import com.gabys.ps_tema2.viewmodels.commands.ICommand;
import com.google.gson.Gson;

import java.util.Observable;

public class AddUserViewModel extends Observable implements IViewModel {

    private final Context context;
    public MutableLiveData<String> userUsername;
    public MutableLiveData<String> userPassword;

    public MutableLiveData<String[]> roles;
    public MutableLiveData<Integer> position;

    private final ICommand commandAddUser;

    public AddUserViewModel(Context context) {
        this.context = context;

        Gson gson = new Gson();
        User user = gson.fromJson(((Activity)context).getIntent().getStringExtra("user"), User.class);

        if(user.getRole() == 1){
            roles = new MutableLiveData<>(new String[]{"Client"});
            position = new MutableLiveData<>(0);
        }
        if(user.getRole() == 2){
            roles = new MutableLiveData<>(context.getResources().getStringArray(R.array.user_roles));
            position = new MutableLiveData<>(1);
        }

        userUsername = new MutableLiveData<>();
        userPassword = new MutableLiveData<>();

        this.commandAddUser = new CommandAddUser(context);
    }

    public void onYesButtonClick(View v){
        refresh();

        User user = new User(
                userUsername.getValue(),
                userPassword.getValue(),
                position.getValue());

        ((CommandAddUser)commandAddUser).setUser(user);
        commandAddUser.Execute();

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
