package com.gabys.ps_tema2.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.viewmodels.commands.CommandUpdateProperty;
import com.gabys.ps_tema2.viewmodels.commands.ICommand;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.stream.Collectors;

public class EditPropertyViewModel extends Observable implements IViewModel{

    private final Context context;
    public MutableLiveData<String> propertyId;
    public MutableLiveData<String> propertyTitle;
    public MutableLiveData<String> propertyLocation;
    public MutableLiveData<String> propertyRoomsNo;
    public MutableLiveData<String> propertyPrice;
    public MutableLiveData<Boolean> propertyAvailability;
    public MutableLiveData<String> propertyImageURL;

    public MutableLiveData<String[]> types;

    public MutableLiveData<Integer> position;


    private final ICommand commandUpdateProperty;

    public EditPropertyViewModel(Context context) {
        this.context = context;

        Gson gson = new Gson();
        Property property = gson.fromJson(((Activity)context).getIntent().getStringExtra("property"), Property.class);

        types = new MutableLiveData<>(context.getResources().getStringArray(R.array.property_types));
        position = new MutableLiveData<>(Arrays.stream(types.getValue())
                .collect(Collectors.toList())
                .indexOf(property.getType()));


        propertyId = new MutableLiveData<>(String.valueOf(property.getId()));
        propertyTitle = new MutableLiveData<>(property.getTitle());
        propertyLocation = new MutableLiveData<>(property.getLocation());
        propertyRoomsNo = new MutableLiveData<>(String.valueOf(property.getRoomsNo()));
        propertyPrice = new MutableLiveData<>(String.valueOf(property.getPrice()));
        propertyAvailability = new MutableLiveData<>(property.isAvailable());
        propertyImageURL = new MutableLiveData<>(property.getImageURL());

        this.commandUpdateProperty = new CommandUpdateProperty(context);
    }

    public void onYesButtonClick(View v){
        refresh();

        Property property = new Property(Integer.parseInt(
                propertyId.getValue()),
                propertyTitle.getValue(),
                propertyLocation.getValue(),
                Integer.parseInt(propertyRoomsNo.getValue()),
                types.getValue()[position.getValue()],
                Float.parseFloat(propertyPrice.getValue()),
                propertyAvailability.getValue(),
                propertyImageURL.getValue());

        ((CommandUpdateProperty)commandUpdateProperty).setProperty(property);
        commandUpdateProperty.Execute();

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
