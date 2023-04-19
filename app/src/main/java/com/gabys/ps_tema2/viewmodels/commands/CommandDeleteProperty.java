package com.gabys.ps_tema2.viewmodels.commands;

import android.content.Context;

import com.gabys.ps_tema2.model.Persistence.PropertyPersistence;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.viewmodels.EmployeeViewModel;
import com.gabys.ps_tema2.viewmodels.IViewModel;

public class CommandDeleteProperty implements ICommand {

    private PropertyPersistence propertyPersistence;
    private IViewModel viewModel;
    private Property property;

    public CommandDeleteProperty(IViewModel viewModel, Property property) {
        this.viewModel = viewModel;
        this.property = property;

    }

    public void setContext(Context context){
        this.propertyPersistence = new PropertyPersistence(context);
    }

    @Override
    public void Execute() {
        propertyPersistence.deleteProperty(property.getId());
        ((EmployeeViewModel)viewModel).deleteProperty(property);
        viewModel.refresh();
    }
}
