package com.gabys.ps_tema2.viewmodels;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import java.util.Observable;

public class FilterPropertiesViewModel extends Observable {

    public Float minPrice;
    public Float maxPrice;
    public String location;
    public String type;
    public String roomsNo;

    public FilterPropertiesViewModel() {}

    public FilterPropertiesViewModel(Float minPrice) {
        this.minPrice = minPrice;
    }

    public void refresh() {
    }

    public void yesButtonClick(View v) {

        setChanged();
        notifyObservers();
    }

    public void noButtonClick(View v) {

        setChanged();
        notifyObservers();
    }

    public void onLocationSpinnerItemSelected(AdapterView<?> parent, View view, int position, long id) {
        location = (String) parent.getItemAtPosition(position);
    }


    public String getSelectedType() {
        return "";
    }
}
