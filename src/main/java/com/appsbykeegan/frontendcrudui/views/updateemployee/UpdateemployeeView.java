package com.appsbykeegan.frontendcrudui.views.updateemployee;

import com.appsbykeegan.frontendcrudui.models.EmployeeModel;
import com.appsbykeegan.frontendcrudui.models.enums.EmployeeGender;
import com.appsbykeegan.frontendcrudui.models.enums.EmployeeRole;
import com.appsbykeegan.frontendcrudui.models.records.DepartmentRequestBody;
import com.appsbykeegan.frontendcrudui.models.records.EmployeeRequestBody;
import com.appsbykeegan.frontendcrudui.service.EmployeeRestfulService;
import com.appsbykeegan.frontendcrudui.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@PageTitle("Update employee")
@Route(value = "update/employee", layout = MainLayout.class)
@Uses(Icon.class)
public class UpdateemployeeView extends Div {

    @Autowired
    private EmployeeRestfulService employeeRestfulService;

    private EmailField email = new EmailField("Email address");
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");

    private ComboBox<EmployeeGender> employeeGender = new ComboBox<>("Gender");
    private ComboBox<EmployeeRole> employeeRole = new ComboBox<>("Role");

    private TextField departmentName = new TextField("Department Name");

    private Button find = new Button("Find Employee");
    private Button cancel = new Button("Clear");
    private Button save = new Button("Update");
    private Button delete = new Button("Delete Employee");



    public UpdateemployeeView() {
        addClassName("updateemployee-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        clearForm();

        find.addClickListener(buttonClickEvent -> {

            firstName.clear();
            lastName.clear();
            employeeGender.clear();
            employeeRole.clear();
            departmentName.clear();

            EmployeeModel body = findEmployeeRestFunc(email.getValue());

            if (body == null) {

                clearForm();

                Notification error = Notification.show("Something went wrong!");
                error.setPosition(Notification.Position.BOTTOM_CENTER);
                error.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {

                clearForm();

                find.setEnabled(false);
                save.setEnabled(true);
                delete.setEnabled(true);

                email.setValue(body.getEmailAddress());
                firstName.setValue(body.getEmployeeFirstName());
                lastName.setValue(body.getEmployeeLastName());
                departmentName.setValue(body.getDepartment().getDepartmentName());
                employeeRole.setValue(EmployeeRole.valueOf(body.getEmployeeRole()));
                employeeGender.setValue(EmployeeGender.valueOf(body.getEmployeeGender()));

            }

        });
        cancel.addClickListener(buttonClickEvent -> {
            clearForm();
            save.setEnabled(false);
            find.setEnabled(true);
            delete.setEnabled(false);
        });
        save.addClickListener(buttonClickEvent -> {

            delete.setEnabled(false);
            updateEmplyRestFunc();
            clearForm();
            save.setEnabled(false);

        });

        delete.addClickListener(buttonClickEvent -> {

            find.setEnabled(true);
            save.setEnabled(false);
            delete.setEnabled(false);

            deleteEmplyRestFunc();
        });
    }

    private Component createTitle() {
        return new H3("Manage Employee");
    }

    private Component createFormLayout() {

        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");

        employeeRole.setItems(EmployeeRole.MANAGER,EmployeeRole.PERSONNEL,EmployeeRole.INTERN);
        employeeGender.setItems(EmployeeGender.MALE,EmployeeGender.FEMALE,EmployeeGender.PREFER_NOT_TO_SAY);

        formLayout.add(email, find, firstName, lastName, employeeRole,employeeGender,departmentName);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.setEnabled(false);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        delete.setEnabled(false);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonLayout.add(delete);
        return buttonLayout;
    }

    private void clearForm() {

        firstName.clear();
        lastName.clear();
        email.clear();
        employeeGender.clear();
        employeeRole.clear();
        departmentName.clear();
    }

    private EmployeeModel findEmployeeRestFunc(String email) {

        return employeeRestfulService.findEmployee(email);
    }

    public void updateEmplyRestFunc() {

        boolean isSuccessful = false;

        isSuccessful = employeeRestfulService.UpdateEmployee(new EmployeeRequestBody(

                firstName.getValue(),
                lastName.getValue(),
                employeeGender.getValue().toString(),
                employeeRole.getValue().toString(),
                email.getValue(),
                departmentName.getValue()

        ));

        if (isSuccessful) {

            clearForm();
            Notification notification = Notification.show("Entry Updated!");
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            find.setEnabled(true);

        } else if (!isSuccessful){

            Notification notification = Notification.show("Something went wrong!");
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

    private void deleteEmplyRestFunc() {

        boolean isSuccessful = false;
        String emailAddress = email.getValue();

        isSuccessful = employeeRestfulService.deleteEmployee(emailAddress);

        if (isSuccessful) {

            clearForm();
            Notification notification = Notification.show("Entry Deleted!");
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        } else if (!isSuccessful){

            Notification notification = Notification.show("Something went wrong!");
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

}
