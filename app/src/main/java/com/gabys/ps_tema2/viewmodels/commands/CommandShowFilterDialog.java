package com.gabys.ps_tema2.viewmodels.commands;

import androidx.fragment.app.FragmentManager;

import com.gabys.ps_tema2.view.FilterPropertiesDialog;
import com.gabys.ps_tema2.viewmodels.IViewModel;

public class CommandShowFilterDialog implements ICommand {

    private FragmentManager fragmentManager;
    private CommandFilterProperties commandFilterProperties;

    private IViewModel viewModel;

    public CommandShowFilterDialog(IViewModel viewModel, FragmentManager fragmentManager, CommandFilterProperties commandFilterProperties) {
        this.fragmentManager = fragmentManager;
        this.commandFilterProperties = commandFilterProperties;
        this.viewModel = viewModel;
    }

    @Override
    public void Execute() {
        FilterPropertiesDialog filterDialog = new FilterPropertiesDialog(viewModel, commandFilterProperties);
        filterDialog.show(fragmentManager, "filter_dialog");
    }
}
