package com.appsbykeegan.frontendcrudui.views.employee;

import com.appsbykeegan.frontendcrudui.models.DepartmentModel;
import com.appsbykeegan.frontendcrudui.models.EmployeeModel;
import com.appsbykeegan.frontendcrudui.models.records.EmployeeRequestBody;
import com.appsbykeegan.frontendcrudui.service.EmployeeRestfulService;
import com.appsbykeegan.frontendcrudui.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Employees")
@Route(value = "retrieve/employees", layout = MainLayout.class)
@Uses(Icon.class)
public class EmployeesView extends Div {

    private final EmployeeRestfulService employeeRestfulService;

    public EmployeesView(EmployeeRestfulService employeeRestfulService) {
        this.employeeRestfulService = employeeRestfulService;
        setSizeFull();
        addClassNames("employees-view");

        VerticalLayout layout = new VerticalLayout();

        try {
            layout.add(createGrid());
        } catch (Exception exception) {

            H3 information = new H3();
            information.setText("API connection is unavailable! ⚠");
            information.addClassNames(LumoUtility.Margin.Top.XLARGE, LumoUtility.Margin.Bottom.MEDIUM);
            layout.setAlignItems(FlexComponent.Alignment.CENTER);
            layout.add(information);
        }

        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }

    private Component createGrid() {

        List<EmployeeModel> employeeModelList = employeeRestfulService.getAllEmployees();

        Grid<EmployeeModel> grid = new Grid<>(EmployeeModel.class, false);
        grid.addColumn("employeeId").setAutoWidth(true);
        grid.addColumn("emailAddress").setAutoWidth(true);
        grid.addColumn("employeeFirstName").setAutoWidth(true);
        grid.addColumn("employeeLastName").setAutoWidth(true);
        grid.addColumn(EmployeeModel::getDepartmentName).setHeader("Department").setAutoWidth(true);
        //grid.addColumn("employeeGender").setAutoWidth(true);
        grid.addColumn("employeeRole").setAutoWidth(true);
        grid.addColumn("startDate").setAutoWidth(true);

        grid.setItems(employeeModelList);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }

}
