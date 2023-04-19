package com.gabys.ps_tema2.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.viewmodels.commands.CommandLogin;
import com.gabys.ps_tema2.viewmodels.IViewModel;

public class LoginDialog extends DialogFragment {
    private IViewModel viewModel;
    private CommandLogin commandLogin;
    public LoginDialog(IViewModel viewModel, CommandLogin commandLogin) {
        this.viewModel = viewModel;
        this.commandLogin = commandLogin;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View dialogView = inflater.inflate(R.layout.dialog_login, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_login_bg);

        EditText usernameEditText = dialogView.findViewById(R.id.usernameField);
        EditText passwordEditText = dialogView.findViewById(R.id.passwordField);

        Button yesButton = dialogView.findViewById(R.id.filter_yes);
        Button noButton = dialogView.findViewById(R.id.filter_no);

        yesButton.setOnClickListener(view -> {
            String username = String.valueOf(usernameEditText.getText());
            String password = String.valueOf(passwordEditText.getText());

            ((CommandLogin)commandLogin).setUsername(username);
            ((CommandLogin)commandLogin).setPassword(password);
            commandLogin.Execute();

            dismiss();
        });

        noButton.setOnClickListener(view -> dismiss());

        return dialogView;
    }
}
