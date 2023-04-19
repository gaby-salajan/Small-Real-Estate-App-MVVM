package com.gabys.ps_tema2.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.databinding.ActivityAddPropertyBinding;
import com.gabys.ps_tema2.viewmodels.AddPropertyViewModel;

import java.util.Observable;
import java.util.Observer;

public class AddPropertyActivity extends AppCompatActivity implements Observer {
    private AddPropertyViewModel addPropertyViewModel;
    private ActivityAddPropertyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupObserver(addPropertyViewModel);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_property);
        addPropertyViewModel = new AddPropertyViewModel(this);
        binding.setViewModel(addPropertyViewModel);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        //rentViewModel.setClient((User) binding.clientSpinner.getSelectedItem());
    }

}
