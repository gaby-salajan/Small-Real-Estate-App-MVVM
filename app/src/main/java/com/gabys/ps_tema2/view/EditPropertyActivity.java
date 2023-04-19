package com.gabys.ps_tema2.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.databinding.ActivityEditPropertyBinding;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.viewmodels.EditPropertyViewModel;
import com.gabys.ps_tema2.viewmodels.RentViewModel;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

//TODO EditPropertyActivity
public class EditPropertyActivity extends AppCompatActivity implements Observer {

    private EditPropertyViewModel editPropertyViewModel;
    private ActivityEditPropertyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupObserver(editPropertyViewModel);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_property);
        editPropertyViewModel = new EditPropertyViewModel(this);
        binding.setViewModel(editPropertyViewModel);

        setPropertyImage(editPropertyViewModel.getPImageURL());
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        //rentViewModel.setClient((User) binding.clientSpinner.getSelectedItem());
    }

    public void setPropertyImage(String imageURL) {
        Glide.with(binding.getRoot().getContext())
                .load(imageURL)
                .centerCrop()
                .into(binding.propertyImage);
    }
}