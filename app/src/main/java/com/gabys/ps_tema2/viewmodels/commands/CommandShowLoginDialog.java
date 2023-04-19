package com.gabys.ps_tema2.viewmodels.commands;

import androidx.fragment.app.FragmentManager;

import com.gabys.ps_tema2.view.LoginDialog;
import com.gabys.ps_tema2.viewmodels.IViewModel;

public class CommandShowLoginDialog implements ICommand{
    private FragmentManager fragmentManager;

    private CommandLogin commandLogin;
    private IViewModel viewModel;

    public CommandShowLoginDialog(IViewModel viewModel, FragmentManager fragmentManager, CommandLogin commandLogin) {
        this.fragmentManager = fragmentManager;
        this.viewModel = viewModel;
        this.commandLogin = commandLogin;
    }

    @Override
    public void Execute() {
        LoginDialog loginDialog = new LoginDialog(viewModel, commandLogin);
        loginDialog.show(fragmentManager, "login_dialog");
    }
}
