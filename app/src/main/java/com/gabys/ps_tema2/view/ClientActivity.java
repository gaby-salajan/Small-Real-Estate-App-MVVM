package com.gabys.ps_tema2.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.view.Adapters.PropertyCardAdapter;
import com.gabys.ps_tema2.viewmodels.ClientViewModel;
import com.gabys.ps_tema2.databinding.ActivityClientBinding;
import java.util.Observable;
import java.util.Observer;

public class ClientActivity extends AppCompatActivity implements Observer {

    private PropertyCardAdapter propertyCardAdapter;

    private ClientViewModel clientViewModel;
    private ActivityClientBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupListPropertiesView();
        setupObserver(clientViewModel);
        clientViewModel.refresh();
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_client);
        clientViewModel = new ClientViewModel(this, getSupportFragmentManager());
        binding.setViewModel(clientViewModel);
    }

    private void setupListPropertiesView() {
        propertyCardAdapter = new PropertyCardAdapter(clientViewModel);
        binding.propertyRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.propertyRecyclerView.setAdapter(propertyCardAdapter);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ClientViewModel) {
            propertyCardAdapter = (PropertyCardAdapter) binding.propertyRecyclerView.getAdapter();
            ClientViewModel clientViewModel = (ClientViewModel) o;
            if (propertyCardAdapter != null) {
                propertyCardAdapter.setProperties(clientViewModel.getPropertiesList());
            }
        }
    }
}
