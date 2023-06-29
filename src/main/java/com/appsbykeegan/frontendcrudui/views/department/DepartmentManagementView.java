package com.appsbykeegan.frontendcrudui.views.department;

import com.appsbykeegan.frontendcrudui.models.DepartmentModel;
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

@PageTitle("Manage department")
@Route(value = "update/department", layout = MainLayout.class)
@Slf4j
public class DepartmentManagementView extends Div {

    private final DepartmentRestfulService departmentRestfulService;

    private TextField departmentName = new TextField("Department Name");
    private NumberField departmentFloorNumber = new NumberField("Floor Number");
    private TextArea departmentDescription = new TextArea("Description");
    private ComboBox<Double> departmentBudget = new ComboBox<>("Budget");

    private Button find = new Button("Find Department");
    private Button cancel = new Button("Clear");
    private Button save = new Button("Update");
    private Button delete = new Button("Delete Department");

    public DepartmentManagementView(DepartmentRestfulService departmentRestfulService) {
        this.departmentRestfulService = departmentRestfulService;
        addClassName("updatedepartment-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        clearForm();

            find.addClickListener(buttonClickEvent -> {

                departmentFloorNumber.clear();
                departmentBudget.clear();
                departmentDescription.clear();

                DepartmentModel body = findDepartmentRestFunc(departmentName.getValue());

                if (body == null) {

                    clearForm();

                    Notification error = Notification.show("Could not find Department");
                    error.setPosition(Notification.Position.BOTTOM_CENTER);
                    error.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else {

                    clearForm();

                    find.setEnabled(false);
                    save.setEnabled(true);
                    delete.setEnabled(true);
                    departmentName.setValue(body.getDepartmentName());
                    departmentFloorNumber.setValue((double) body.getDepartmentFloorNumber());
                    departmentBudget.setValue(body.getDepartmentBudget().doubleValue());
                    departmentDescription.setValue(body.getDepartmentDescription());

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
                updateDeptRestFunc();
                clearForm();
                save.setEnabled(false);

            });
            delete.addClickListener(buttonClickEvent -> {

                find.setEnabled(true);
                save.setEnabled(false);
                delete.setEnabled(false);

                deleteDeptRestFunc();
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
        formLayout.add(find);
        find.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        formLayout.add(departmentFloorNumber);
        formLayout.add(departmentBudget);
        formLayout.add(departmentDescription,2);

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
        // clear all text fields
        departmentName.clear();
        departmentFloorNumber.clear();
        departmentDescription.clear();
        departmentBudget.clear();

    }

    private DepartmentModel findDepartmentRestFunc(String deptName) {

        try {

            return departmentRestfulService.findDepartment(deptName);

        } catch (Exception exception) {

            log.error(exception.getMessage());
            return null;
        }
    }

    public void updateDeptRestFunc() {

        try {

            boolean isSuccessful = false;

            isSuccessful = departmentRestfulService.UpdateDepartment(new DepartmentRequestBody(

                    departmentName.getValue(),
                    departmentFloorNumber.getValue().intValue(),
                    departmentDescription.getValue(),
                    BigDecimal.valueOf(departmentBudget.getValue())
            ));

            if (isSuccessful) {

                clearForm();
                Notification notification = Notification.show("Entry Updated!");
                notification.setPosition(Notification.Position.BOTTOM_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                find.setEnabled(true);

            } else if (!isSuccessful){

                Notification notification = Notification.show("Update Failed");
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

    private void deleteDeptRestFunc() {

        try {

            boolean isSuccessful = false;
            String deptName = departmentName.getValue();

            isSuccessful = departmentRestfulService.deleteDepartment(deptName);

            if (isSuccessful) {

                clearForm();
                Notification notification = Notification.show("Entry Deleted!");
                notification.setPosition(Notification.Position.BOTTOM_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

            } else if (!isSuccessful){

                Notification notification = Notification.show("Deletion Failed");
                notification.setPosition(Notification.Position.BOTTOM_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        } catch (Exception exception) {

            log.error(exception.getMessage());
            Notification notification = Notification.show("Something went wrong");
            notification.setPosition(Notification.Position.BOTTOM_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

    }

}
