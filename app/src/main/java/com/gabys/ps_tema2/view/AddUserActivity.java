package com.gabys.ps_tema2.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.databinding.ActivityAddUserBinding;
import com.gabys.ps_tema2.viewmodels.AddUserViewModel;

import java.util.Observable;
import java.util.Observer;

public class AddUserActivity extends AppCompatActivity implements Observer {
    private AddUserViewModel addUserViewModel;
    private ActivityAddUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupObserver(addUserViewModel);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_user);
        addUserViewModel = new AddUserViewModel(this);
        binding.setViewModel(addUserViewModel);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        //rentViewModel.setClient((User) binding.clientSpinner.getSelectedItem());
    }
}
