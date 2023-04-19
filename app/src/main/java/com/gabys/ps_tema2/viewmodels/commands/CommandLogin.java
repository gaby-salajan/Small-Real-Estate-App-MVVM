package com.gabys.ps_tema2.viewmodels.commands;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.gabys.ps_tema2.model.Persistence.UserPersistence;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.view.AdminActivity;
import com.gabys.ps_tema2.view.EmployeeActivity;
import com.google.gson.Gson;

public class CommandLogin implements ICommand{

    private Context context;
    private String username;
    private String password;

    public CommandLogin(Context context) {
        this.context = context;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void Execute() {
        UserPersistence userPersistence = new UserPersistence(context);
        User user = userPersistence.getLoginUser(username);
        if(user != null){

            if(password.equals(user.getPassword())){
                Intent intent = null;
                if(user.getRole() == 1){
                    intent = new Intent(context, EmployeeActivity.class);

                    Gson gson = new Gson();
                    String myJson = gson.toJson(user);
                    intent.putExtra("user", myJson);
                }
                if(user.getRole() == 2){
                    intent = new Intent(context, AdminActivity.class);
                    Gson gson = new Gson();
                    String myJson = gson.toJson(user);
                    intent.putExtra("user", myJson);
                }

                if(intent != null){
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
                else{
                    Toast.makeText(context, "Utilizatorul este doar un client", Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(context, "Parola incorecta", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Utilizatorul nu exista", Toast.LENGTH_SHORT).show();
    }
}
