package com.gabys.ps_tema2.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.gabys.ps_tema2.databinding.ActivityAdminBinding;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.view.Adapters.PropertyCardAdapter;
import com.gabys.ps_tema2.view.Adapters.UserCardAdapter;
import com.gabys.ps_tema2.view.Adapters.ViewPagerAdapter;
import com.gabys.ps_tema2.viewmodels.AdminViewModel;
import com.gabys.ps_tema2.viewmodels.EmployeeViewModel;
import com.gabys.ps_tema2.viewmodels.IViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class AdminActivity extends AppCompatActivity implements Observer {
    private PropertyCardAdapter propertyCardAdapter;
    private UserCardAdapter userCardAdapter;
    private ActivityAdminBinding binding;
    private AdminViewModel adminViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupListPropertiesView();
        setupObserver(adminViewModel);
        adminViewModel.refresh();
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin);
        adminViewModel = new AdminViewModel(this, getSupportFragmentManager());
        binding.setViewModel(adminViewModel);
    }

    private void setupListPropertiesView() {
        propertyCardAdapter = new PropertyCardAdapter(adminViewModel);
        userCardAdapter = new UserCardAdapter(adminViewModel);

        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), propertyCardAdapter, userCardAdapter));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        adminViewModel.fetchProperties();
        adminViewModel.fetchUsers();
        adminViewModel.refresh();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof AdminViewModel) {
            AdminViewModel adminViewModel = (AdminViewModel) o;
            if (propertyCardAdapter != null) {
                propertyCardAdapter.setProperties(adminViewModel.getProperties());
            }
            if(userCardAdapter != null) {
                userCardAdapter.setUsers(adminViewModel.getUsers());
            }
        }
    }
}
