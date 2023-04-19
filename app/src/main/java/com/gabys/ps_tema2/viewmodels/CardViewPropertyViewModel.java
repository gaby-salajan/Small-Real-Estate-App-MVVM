package com.gabys.ps_tema2.viewmodels;

import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.view.EditPropertyActivity;
import com.gabys.ps_tema2.view.RentActivity;
import com.gabys.ps_tema2.viewmodels.commands.CommandDeleteProperty;
import com.gabys.ps_tema2.viewmodels.commands.ICommand;
import com.google.gson.Gson;

public class CardViewPropertyViewModel extends BaseObservable {
    private Property property;
    private User loggedUser;
    private CommandDeleteProperty commandDeleteProperty;

    public CardViewPropertyViewModel(IViewModel viewModel, Property property, User user) {
        this.property = property;
        this.loggedUser = user;
        this.commandDeleteProperty = new CommandDeleteProperty(viewModel, property);
    }

    public void setProperty(Property property) {
        this.property = property;
        notifyChange();
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Boolean isEmployee(){
        return loggedUser.getRole() == 1;
    }

    public String getPropertyTitle() {
        return property.getTitle();
    }

    public String getPropertyLocation() {
        return property.getLocation();
    }

    public String getPropertyRoomsNo() {
        return String.valueOf(property.getRoomsNo());
    }

    public String getPropertyType() {
        return property.getType();
    }

    public String getPropertyPrice() {
        return String.valueOf(property.getPrice());
    }

    public String getPropertyAvailability() {
        return property.isAvailableToString();
    }

    public String getPropertyImageURL() {
        return property.getImageURL();
    }

    public void onDeleteButtonClick(View view) {
        commandDeleteProperty.setContext(view.getContext());
        commandDeleteProperty.Execute();
    }

    public void onModifyButtonClick(View v) {
        Intent intent = new Intent(v.getContext(), EditPropertyActivity.class);

        Gson gson = new Gson();
        String myJson = gson.toJson(property);
        intent.putExtra("property", myJson);

        v.getContext().startActivity(intent);
    }

    public void onRentButtonClick(View v) {
        //context.startActivity(PeopleDetailActivity.launchDetail(view.getContext(), people));
        Intent intent = new Intent(v.getContext(), RentActivity.class);

        Gson gson = new Gson();
        String propertyJS = gson.toJson(property);
        String userJS = gson.toJson(loggedUser);
        intent.putExtra("property", propertyJS);
        intent.putExtra("user", userJS);

        v.getContext().startActivity(intent);
    }
}
