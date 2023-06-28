package com.appsbykeegan.frontendcrudui.views.createemployee;

import com.appsbykeegan.frontendcrudui.models.DepartmentModel;
import com.appsbykeegan.frontendcrudui.models.enums.EmployeeGender;
import com.appsbykeegan.frontendcrudui.models.enums.EmployeeRole;
import com.appsbykeegan.frontendcrudui.models.records.DepartmentRequestBody;
import com.appsbykeegan.frontendcrudui.models.records.EmployeeRequestBody;
import com.appsbykeegan.frontendcrudui.service.EmployeeRestfulService;
import com.appsbykeegan.frontendcrudui.views.MainLayout;
import com.appsbykeegan.frontendcrudui.views.createdepartment.CreatedepartmentView;
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
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@PageTitle("Create employee")
@Route(value = "create/employee", layout = MainLayout.class)
@Uses(Icon.class)
public class CreateemployeeView extends Div {

    @Autowired
    private EmployeeRestfulService employeeRestfulService;

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private EmailField email = new EmailField("Email address");
    private ComboBox<EmployeeGender> employeeGender = new ComboBox<>("Gender");
    private ComboBox<EmployeeRole> employeeRole = new ComboBox<>("Role");

    private TextField departmentName = new TextField("Department Name");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    public CreateemployeeView() {
        addClassName("createemployee-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {

            createEmployRestFunc();

        });
    }

    private void clearForm() {

        firstName.clear();
        lastName.clear();
        email.clear();
        employeeRole.clear();
        employeeGender.clear();
        departmentName.clear();
    }

    private Component createTitle() {
        return new H3("Employee information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");

        employeeRole.setItems(EmployeeRole.MANAGER,EmployeeRole.PERSONNEL,EmployeeRole.INTERN);
        employeeGender.setItems(EmployeeGender.MALE,EmployeeGender.FEMALE,EmployeeGender.PREFER_NOT_TO_SAY);

        formLayout.add(firstName, lastName, email,employeeRole,employeeGender,departmentName);
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

    public void createEmployRestFunc() {

        boolean isSuccessful = false;

        isSuccessful = employeeRestfulService.createEmployee(new EmployeeRequestBody(
                firstName.getValue(),
                lastName.getValue(),
                employeeGender.getValue().toString(),
                employeeRole.getValue().toString(),
                email.getValue(),
                departmentName.getValue()
                )
        );

        if (isSuccessful) {

            clearForm();
            Notification notification = Notification.show("Entry Created!");
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        } else if (!isSuccessful){

            Notification notification = Notification.show("Something went wrong!");
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}
