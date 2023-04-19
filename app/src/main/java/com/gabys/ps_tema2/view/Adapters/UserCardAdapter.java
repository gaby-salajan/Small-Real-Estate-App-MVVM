package com.gabys.ps_tema2.view.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.gabys.ps_tema2.databinding.CardviewUserBinding;
import com.gabys.ps_tema2.model.User;
import com.gabys.ps_tema2.R;
import com.gabys.ps_tema2.viewmodels.CardViewUserViewModel;
import com.gabys.ps_tema2.viewmodels.IViewModel;

import java.util.ArrayList;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.UserAdapterViewHolder> {
    private ArrayList<User> userList;
    private IViewModel viewModel;

    public UserCardAdapter(IViewModel viewModel) {
        this.userList = new ArrayList<>();
        this.viewModel = viewModel;
    }

    public void setUsers(ArrayList<User> users) {
        this.userList.clear();
        this.userList.addAll(users);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserCardAdapter.UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardviewUserBinding cardviewUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cardview_user, parent, false);
        return new UserCardAdapter.UserAdapterViewHolder(cardviewUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCardAdapter.UserAdapterViewHolder holder, int position) {
        holder.bindUser(viewModel, userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    static class UserAdapterViewHolder extends RecyclerView.ViewHolder {
        CardviewUserBinding binding;
        UserAdapterViewHolder(CardviewUserBinding binding) {
            super(binding.cardProperty);
            this.binding = binding;
        }

        void bindUser(IViewModel viewModel, User user) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new CardViewUserViewModel(viewModel, user));
            }
            else {
                binding.getViewModel().set(user);
            }
        }
    }
}
