package com.gabys.ps_tema2.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.databinding.ActivityRentPropertyBinding;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.viewmodels.RentViewModel;

import java.util.Observable;
import java.util.Observer;

public class RentActivity extends AppCompatActivity implements Observer {

    private RentViewModel rentViewModel;
    private ActivityRentPropertyBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupObserver(rentViewModel);
        rentViewModel.refresh();
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rent_property);
        rentViewModel = new RentViewModel(this);
        setPropertyImage(rentViewModel.getPropertyImageURL());
        binding.setViewModel(rentViewModel);

        /*ArrayAdapter<User> clientsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rentViewModel.getClients());
        clientsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.clientSpinner.setAdapter(clientsAdapter);*/
    }

    @Override
    public void update(Observable o, Object arg) {
        rentViewModel.setClient((User) binding.clientSpinner.getSelectedItem());
    }

    public void setPropertyImage(String imageURL) {
        Glide.with(binding.getRoot().getContext())
                .load(imageURL)
                .centerCrop()
                .into(binding.propertyImage);
    }
}
