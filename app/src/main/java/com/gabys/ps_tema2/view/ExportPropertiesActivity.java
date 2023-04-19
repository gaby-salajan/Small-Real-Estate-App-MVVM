package com.gabys.ps_tema2.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.databinding.ActivityAddPropertyBinding;
import com.gabys.ps_tema2.databinding.ActivityExportPropertiesBinding;
import com.gabys.ps_tema2.viewmodels.AddPropertyViewModel;
import com.gabys.ps_tema2.viewmodels.ExportPropertiesViewModel;

import java.util.Observable;
import java.util.Observer;

public class ExportPropertiesActivity extends AppCompatActivity implements Observer {
    private ExportPropertiesViewModel exportPropertiesViewModel;
    private ActivityExportPropertiesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupObserver(exportPropertiesViewModel);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_export_properties);
        exportPropertiesViewModel = new ExportPropertiesViewModel(this);
        binding.setViewModel(exportPropertiesViewModel);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        //rentViewModel.setClient((User) binding.clientSpinner.getSelectedItem());
    }
}
