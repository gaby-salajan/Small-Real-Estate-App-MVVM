package com.gabys.ps_tema2.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.viewmodels.commands.CommandAddProperty;
import com.gabys.ps_tema2.viewmodels.commands.CommandUpdateProperty;
import com.gabys.ps_tema2.viewmodels.commands.ICommand;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.stream.Collectors;

public class AddPropertyViewModel extends Observable implements IViewModel {

    private final Context context;
    public MutableLiveData<String> propertyTitle;
    public MutableLiveData<String> propertyLocation;
    public MutableLiveData<String> propertyRoomsNo;
    public MutableLiveData<String> propertyPrice;
    public MutableLiveData<Boolean> propertyAvailability;
    public MutableLiveData<String> propertyImageURL;

    public MutableLiveData<String[]> types;

    public MutableLiveData<Integer> position;

    private final ICommand commandAddProperty;

    public AddPropertyViewModel(Context context) {
        this.context = context;

        types = new MutableLiveData<>(context.getResources().getStringArray(R.array.property_types));
        position = new MutableLiveData<>();

        propertyTitle = new MutableLiveData<>();
        propertyLocation = new MutableLiveData<>();
        propertyRoomsNo = new MutableLiveData<>();
        propertyPrice = new MutableLiveData<>();
        propertyAvailability = new MutableLiveData<>();
        propertyImageURL = new MutableLiveData<>();

        this.commandAddProperty = new CommandAddProperty(context);
    }

    public void onYesButtonClick(View v){
        refresh();

        Property property = new Property(
                propertyTitle.getValue(),
                propertyLocation.getValue(),
                Integer.parseInt(propertyRoomsNo.getValue()),
                types.getValue()[position.getValue()],
                Float.parseFloat(propertyPrice.getValue()),
                propertyAvailability.getValue(),
                propertyImageURL.getValue());

        ((CommandAddProperty)commandAddProperty).setProperty(property);
        commandAddProperty.Execute();

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

    public String getPImageURL() {
        return propertyImageURL.getValue();
    }

}
