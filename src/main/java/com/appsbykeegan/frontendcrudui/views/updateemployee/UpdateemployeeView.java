package com.appsbykeegan.frontendcrudui.views.updateemployee;

import com.appsbykeegan.frontendcrudui.models.EmployeeModel;
import com.appsbykeegan.frontendcrudui.models.enums.EmployeeGender;
import com.appsbykeegan.frontendcrudui.models.enums.EmployeeRole;
import com.appsbykeegan.frontendcrudui.views.MainLayout;
import com.appsbykeegan.frontendcrudui.views.createdepartment.CreatedepartmentView;
import com.appsbykeegan.frontendcrudui.views.createemployee.CreateemployeeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Update employee")
@Route(value = "update/employee", layout = MainLayout.class)
@Uses(Icon.class)
public class UpdateemployeeView extends Div {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField email = new EmailField("Email address");
    private ComboBox<EmployeeGender> employeeGender = new ComboBox<>("Gender");
    private ComboBox<EmployeeRole> employeeRole = new ComboBox<>("Role");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");


    public UpdateemployeeView() {
        addClassName("updateemployee-view");

        add(createTitle());
        add(createFormLayout());
        add(createEmply());
        add(createButtonLayout());

        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            clearForm();
        });
    }

    private Component createEmply() {

        return null;
    }

    private void clearForm() {

    }

    private Component createTitle() {
        return new H3("Update Employee");
    }

    private Component createFormLayout() {

        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");

        employeeRole.setItems(EmployeeRole.MANAGER,EmployeeRole.PERSONNEL,EmployeeRole.INTERN);
        employeeGender.setItems(EmployeeGender.MALE,EmployeeGender.FEMALE,EmployeeGender.PREFER_NOT_TO_SAY);

        formLayout.add(firstName, lastName, email,employeeRole,employeeGender);
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

}
