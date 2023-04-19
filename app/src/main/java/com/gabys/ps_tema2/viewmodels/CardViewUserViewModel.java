package com.gabys.ps_tema2.viewmodels;

import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.view.EditUserActivity;
import com.gabys.ps_tema2.viewmodels.commands.CommandDeleteUser;
import com.google.gson.Gson;

public class CardViewUserViewModel extends BaseObservable {
    private User user;
    private CommandDeleteUser commandDeleteUser;

    public CardViewUserViewModel(IViewModel viewModel, User user) {
        this.user = user;
        this.commandDeleteUser = new CommandDeleteUser(viewModel, user);
    }

    public void set(User user) {
        this.user = user;
        notifyChange();
    }

    public String getUserId() {
        return String.valueOf(user.getId());
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getUserPassword() {
        return user.getPassword();
    }

    public String getUserRole() {
        return user.getRoleToString();
    }


    public void onDeleteButtonClick(View v) {
        commandDeleteUser.setContext(v.getContext());
        commandDeleteUser.Execute();
    }

    public void onModifyButtonClick(View v) {
        Intent intent = new Intent(v.getContext(), EditUserActivity.class);

        Gson gson = new Gson();
        String myJson = gson.toJson(user);
        intent.putExtra("user", myJson);

        v.getContext().startActivity(intent);
    }
}
