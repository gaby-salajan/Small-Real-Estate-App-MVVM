package com.gabys.ps_tema2.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.databinding.ActivityEmployeeBinding;
import com.gabys.ps_tema2.view.Adapters.PropertyCardAdapter;
import com.gabys.ps_tema2.view.Adapters.UserCardAdapter;
import com.gabys.ps_tema2.view.Adapters.ViewPagerAdapter;
import com.gabys.ps_tema2.viewmodels.EmployeeViewModel;

import java.util.Observable;
import java.util.Observer;


public class EmployeeActivity extends AppCompatActivity implements Observer {
    private PropertyCardAdapter propertyCardAdapter;

    private UserCardAdapter clientCardAdapter;
    private EmployeeViewModel employeeViewModel;
    private ActivityEmployeeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupListPropertiesView();
        setupObserver(employeeViewModel);

        employeeViewModel.refresh();
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee);
        employeeViewModel = new EmployeeViewModel(this, getSupportFragmentManager());
        binding.setViewModel(employeeViewModel);
    }

    private void setupListPropertiesView() {
        propertyCardAdapter = new PropertyCardAdapter(employeeViewModel);
        clientCardAdapter = new UserCardAdapter(employeeViewModel);

        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), propertyCardAdapter, clientCardAdapter));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof EmployeeViewModel) {
            EmployeeViewModel employeeViewModel = (EmployeeViewModel) o;
            if (propertyCardAdapter != null) {
                propertyCardAdapter.setProperties(employeeViewModel.getPropertiesList());
                propertyCardAdapter.setLoggedUser(employeeViewModel.getLoggedUser());
            }
            if(clientCardAdapter != null) {
                clientCardAdapter.setUsers(employeeViewModel.getClients());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        employeeViewModel.fetchProperties();
        employeeViewModel.fetchClients();
        employeeViewModel.refresh();
    }
}
