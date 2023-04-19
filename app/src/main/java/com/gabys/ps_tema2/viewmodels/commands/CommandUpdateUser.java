package com.gabys.ps_tema2.viewmodels.commands;

import android.content.Context;

import com.gabys.ps_tema2.model.Persistence.UserPersistence;
import com.gabys.ps_tema2.model.User;

public class CommandUpdateUser implements ICommand {
    private User user;
    private UserPersistence userPersistence;

    public CommandUpdateUser(Context context) {
        this.userPersistence = new UserPersistence(context);
    }

    @Override
    public void Execute() {
        userPersistence.updateUser(user);
    }

    public void setProperty(User user) {
        this.user = user;
    }
}
