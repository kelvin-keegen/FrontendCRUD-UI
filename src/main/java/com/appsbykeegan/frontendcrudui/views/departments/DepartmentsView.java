package com.appsbykeegan.frontendcrudui.views.departments;

import com.appsbykeegan.frontendcrudui.models.DepartmentModel;
import com.appsbykeegan.frontendcrudui.service.DepartmentRestfulService;
import com.appsbykeegan.frontendcrudui.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.ArrayList;
import java.util.List;


@PageTitle("Departments")
@Route(value = "retrieve/departments", layout = MainLayout.class)
@Uses(Icon.class)
public class DepartmentsView extends Div {

    private final DepartmentRestfulService departmentRestfulService;
    private Grid<DepartmentModel> grid;


    public DepartmentsView(DepartmentRestfulService departmentRestfulService) {
        this.departmentRestfulService = departmentRestfulService;
        setSizeFull();
        addClassNames("departments-view");

        VerticalLayout layout = new VerticalLayout(createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }
    private Component createGrid() {

        List<DepartmentModel> departmentModelList = new ArrayList<>();

        departmentModelList = departmentRestfulService.getAllDepartments();

        ArrayList<DepartmentModel> departmentModelArrayList = (ArrayList<DepartmentModel>) departmentModelList;

        grid = new Grid<>(DepartmentModel.class, false);
        grid.setItems(departmentModelArrayList);
        grid.addColumn(DepartmentModel::getDepartmentId).setHeader("departmentId").setAutoWidth(true);
        grid.addColumn(DepartmentModel::getDepartmentName).setHeader("departmentName").setAutoWidth(true);
        grid.addColumn(DepartmentModel::getDepartmentFloorNumber).setHeader("departmentFloorNumber").setAutoWidth(true);
        grid.addColumn(DepartmentModel::getDepartmentDescription).setHeader("departmentDescription").setAutoWidth(true);
        grid.addColumn(DepartmentModel::getDepartmentBudget).setHeader("departmentBudget").setAutoWidth(true);
        grid.addColumn(DepartmentModel::getDepartmentCreationDate).setHeader("departmentCreationDate").setAutoWidth(true);
        grid.addColumn(DepartmentModel::getEmployees).setHeader("employees").setAutoWidth(true);

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }

}
