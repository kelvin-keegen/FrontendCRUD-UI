package com.appsbykeegan.frontendcrudui.views.updatedepartment;

import com.appsbykeegan.frontendcrudui.models.DepartmentModel;
import com.appsbykeegan.frontendcrudui.views.MainLayout;
import com.appsbykeegan.frontendcrudui.views.createdepartment.CreatedepartmentView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Update department")
@Route(value = "update/department", layout = MainLayout.class)
public class UpdatedepartmentView extends Div {

    private TextField departmentName = new TextField("Department Name");
    private NumberField departmentFloorNumber = new NumberField("Floor Number");
    private TextArea departmentDescription = new TextArea("Description");
    private ComboBox<Double> departmentBudget = new ComboBox<>("Budget");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");


    public UpdatedepartmentView() {
        addClassName("updatedepartment-view");

        add(createTitle());
        add(createFormLayout());
        add(createDept());
        add(createButtonLayout());


        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            clearForm();
        });
    }

    private Component createDept() {

        return null;
    }

    private Component createTitle() {
        return new H3("Update Department");
    }

    private Component createFormLayout() {

        departmentDescription.setPlaceholder("Write department info here...");
        departmentBudget.setItems(1000.00,10000.00,100000.00,1000000.00);

        FormLayout formLayout = new FormLayout();
        formLayout.add(departmentName);
        formLayout.add(departmentFloorNumber);
        formLayout.add(departmentBudget);
        formLayout.add(departmentDescription,2);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

    private void clearForm() {

    }

}
