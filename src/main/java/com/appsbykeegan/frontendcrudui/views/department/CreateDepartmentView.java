package com.appsbykeegan.frontendcrudui.views.department;

import com.appsbykeegan.frontendcrudui.models.records.DepartmentRequestBody;
import com.appsbykeegan.frontendcrudui.service.DepartmentRestfulService;
import com.appsbykeegan.frontendcrudui.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@PageTitle("Create department")
@Route(value = "create/department", layout = MainLayout.class)
@Slf4j
public class CreateDepartmentView extends Div {

    private final DepartmentRestfulService departmentRestfulService;

    private TextField departmentName = new TextField("Department Name");
    private NumberField departmentFloorNumber = new NumberField("Floor Number");
    private TextArea departmentDescription = new TextArea("Description");
    private ComboBox<Double> departmentBudget = new ComboBox<>("Budget");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    public CreateDepartmentView(DepartmentRestfulService departmentRestfulService) {
        this.departmentRestfulService = departmentRestfulService;
        addClassName("createdepartment-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        clearForm();

            cancel.addClickListener(buttonClickEvent -> clearForm());
            save.addClickListener(buttonClickEvent -> {

                createDeptRestFunc();

            });

    }

    private Component createTitle() {
        return new H3("Department information");
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
        // clear all text fields
        departmentName.clear();
        departmentFloorNumber.clear();
        departmentDescription.clear();
        departmentBudget.clear();
    }

    public void createDeptRestFunc() {

        try {

            boolean isSuccessful = false;

            isSuccessful = departmentRestfulService.createDepartment(new DepartmentRequestBody(
                    departmentName.getValue(),
                    departmentFloorNumber.getValue().intValue(),
                    departmentDescription.getValue(),
                    BigDecimal.valueOf(departmentBudget.getValue())
            ));

            if (isSuccessful) {

                clearForm();
                Notification notification = Notification.show("Entry Created!");
                notification.setPosition(Notification.Position.BOTTOM_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

            } else if (!isSuccessful){

                Notification notification = Notification.show("Creation Failed");
                notification.setPosition(Notification.Position.BOTTOM_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }

        } catch (Exception exception) {

            log.error(exception.getMessage());

            Notification notification = Notification.show("Something went wrong!");
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}
