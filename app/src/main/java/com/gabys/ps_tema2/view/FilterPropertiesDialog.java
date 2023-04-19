package com.gabys.ps_tema2.view;

import android.os.Bundle;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;

import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.viewmodels.commands.CommandFilterProperties;
import com.gabys.ps_tema2.viewmodels.IViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FilterPropertiesDialog extends DialogFragment {
    private CommandFilterProperties commandFilterProperties;
    private IViewModel viewModel;
    public FilterPropertiesDialog(IViewModel viewModel, CommandFilterProperties commandFilterProperties) {
        this.commandFilterProperties = commandFilterProperties;
        this.viewModel = viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View dialogView = inflater.inflate(R.layout.dialog_filter_properties, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);

        EditText minPriceEditText = dialogView.findViewById(R.id.price_min);
        EditText maxPriceEditText = dialogView.findViewById(R.id.price_max);
        Spinner locationSpinner = dialogView.findViewById(R.id.spinner_location);
        Spinner typeSpinner = dialogView.findViewById(R.id.spinner_type);
        Spinner roomSpinner = dialogView.findViewById(R.id.spinner_roomsNo);

        Button yesButton = dialogView.findViewById(R.id.filter_yes);
        Button noButton = dialogView.findViewById(R.id.filter_no);

        ArraySet<String> locations = new ArraySet<>();
        locations.add("Toate");
        for(Property p : commandFilterProperties.getPropertiesList()){
            locations.add(p.getLocation());
        }

        ArrayList<String> types = Arrays.stream(dialogView.getContext().getResources().getStringArray(R.array.property_types)).sorted().collect(Collectors.toCollection(ArrayList::new));

        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(dialogView.getContext(), android.R.layout.simple_spinner_item, locations.stream().sorted().collect(Collectors.toList()));
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
        locationSpinner.setSelection(locations.size()-1);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(dialogView.getContext(), android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setSelection(types.size()-1);

        ArrayList<String> roomsNo = Arrays.stream(dialogView.getContext().getResources().getStringArray(R.array.rooms_no)).sorted().collect(Collectors.toCollection(ArrayList::new));
        ArrayAdapter<String> roomsAdapter = new ArrayAdapter<>(dialogView.getContext(), android.R.layout.simple_spinner_item, roomsNo);
        roomsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomSpinner.setAdapter(roomsAdapter);
        roomSpinner.setSelection(roomsNo.size()-1);

        yesButton.setOnClickListener(view -> {
            String minPriceS = String.valueOf(minPriceEditText.getText());
            String maxPriceS = String.valueOf(maxPriceEditText.getText());
            String location = (String) locationSpinner.getSelectedItem();
            String type = (String) typeSpinner.getSelectedItem();
            String roomNo = (String) roomSpinner.getSelectedItem();

            float minPrice = 0f;
            float maxPrice = Float.MAX_VALUE;

            if(!minPriceS.isEmpty())
                minPrice = Float.parseFloat(minPriceS);

            if(!maxPriceS.isEmpty())
                maxPrice = Float.parseFloat(maxPriceS);

            commandFilterProperties.setMinPrice(minPrice);
            commandFilterProperties.setMaxPrice(maxPrice);
            commandFilterProperties.setLocation(location);
            commandFilterProperties.setType(type);
            commandFilterProperties.setRoomsNo(roomNo);

            commandFilterProperties.Execute();

            viewModel.refresh();
            dismiss();

        });

        noButton.setOnClickListener(view -> dismiss());

        return dialogView;
    }

}
