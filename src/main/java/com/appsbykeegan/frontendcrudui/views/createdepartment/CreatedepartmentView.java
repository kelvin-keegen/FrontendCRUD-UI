package com.appsbykeegan.frontendcrudui.views.createdepartment;

import com.appsbykeegan.frontendcrudui.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Create department")
@Route(value = "create/department", layout = MainLayout.class)
public class CreatedepartmentView extends Div {

    private TextField street = new TextField("Street address");
    private TextField postalCode = new TextField("Postal code");
    private TextField city = new TextField("City");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    public CreatedepartmentView() {
        addClassName("createdepartment-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        clearForm();

        cancel.addClickListener(buttonClickEvent -> clearForm());
        save.addClickListener(buttonClickEvent -> clearForm());
    }

    private Component createTitle() {
        return new H3("Department");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(street, 2);
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
        // clear all text fields
    }

}
