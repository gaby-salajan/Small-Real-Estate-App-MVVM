package com.gabys.ps_tema2.view.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gabys.ps_tema2.databinding.CardviewPropertyBinding;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.viewmodels.CardViewPropertyViewModel;
import com.gabys.ps_tema2.viewmodels.IViewModel;

import java.util.ArrayList;

public class PropertyCardAdapter extends RecyclerView.Adapter<PropertyCardAdapter.PropertyAdapterViewHolder> {
    private ArrayList<Property> propertiesList;
    private User user;

    private IViewModel viewModel;

    public PropertyCardAdapter(IViewModel viewModel) {
        this.propertiesList = new ArrayList<>();
        this.user = new User();
        this.viewModel = viewModel;
    }

    public void setLoggedUser(User user) {
        this.user = user;
        notifyDataSetChanged();
    }

    public void setProperties(ArrayList<Property> properties) {
        this.propertiesList.clear();
        this.propertiesList.addAll(properties);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PropertyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardviewPropertyBinding cardviewPropertyBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cardview_property, parent, false);
        return new PropertyAdapterViewHolder(cardviewPropertyBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyAdapterViewHolder holder, int position) {
        holder.bindPropertyAndUser(viewModel, propertiesList.get(position), user);
        holder.setPropertyImage(propertiesList.get(position));

    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    static class PropertyAdapterViewHolder extends RecyclerView.ViewHolder {
        CardviewPropertyBinding binding;
        PropertyAdapterViewHolder(CardviewPropertyBinding binding) {
            super(binding.cardProperty);
            this.binding = binding;
        }

        void bindPropertyAndUser(IViewModel viewModel, Property property, User user) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new CardViewPropertyViewModel(viewModel,property, user));
            }
            else {
                binding.getViewModel().setProperty(property);
            }
        }

        public void setPropertyImage(Property property) {
            Glide.with(binding.getRoot().getContext())
                    .load(property.getImageURL())
                    .centerCrop()
                    .into(binding.cardImage);
        }
    }
}
