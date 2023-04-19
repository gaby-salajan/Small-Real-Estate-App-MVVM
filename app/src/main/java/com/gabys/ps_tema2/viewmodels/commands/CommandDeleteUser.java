package com.gabys.ps_tema2.viewmodels.commands;

import android.content.Context;

import com.gabys.ps_tema2.model.Persistence.UserPersistence;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.viewmodels.IViewModel;

public class CommandDeleteUser implements ICommand  {

    private UserPersistence userPersistence;
    private IViewModel viewModel;
    private User user;

    public CommandDeleteUser(IViewModel viewModel, User user) {
        this.viewModel = viewModel;
        this.user = user;

    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContext(Context context){
        this.userPersistence = new UserPersistence(context);
    }

    @Override
    public void Execute() {
        userPersistence.deleteUser(user.getId());
        viewModel.refresh();
    }
}
