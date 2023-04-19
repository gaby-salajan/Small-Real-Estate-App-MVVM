package com.gabys.ps_tema2.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.databinding.ActivityEditUserBinding;
import com.gabys.ps_tema2.viewmodels.EditUserViewModel;

import java.util.Observable;
import java.util.Observer;

public class EditUserActivity extends AppCompatActivity implements Observer {
    private EditUserViewModel editUserViewModel;
    private ActivityEditUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupObserver(editUserViewModel);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_user);
        editUserViewModel = new EditUserViewModel(this);
        binding.setViewModel(editUserViewModel);

    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        //rentViewModel.setClient((User) binding.clientSpinner.getSelectedItem());
    }
}
