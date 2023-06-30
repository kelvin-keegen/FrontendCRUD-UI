package com.appsbykeegan.frontendcrudui.views.department;

import com.appsbykeegan.frontendcrudui.models.DepartmentModel;
import com.appsbykeegan.frontendcrudui.service.DepartmentRestfulService;
import com.appsbykeegan.frontendcrudui.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.List;


@PageTitle("Departments")
@Route(value = "retrieve/departments", layout = MainLayout.class)
@Uses(Icon.class)
public class DepartmentsView extends Div {

    private final DepartmentRestfulService departmentRestfulService;

    public DepartmentsView(DepartmentRestfulService departmentRestfulService) {
        this.departmentRestfulService = departmentRestfulService;
        setSizeFull();
        addClassNames("departments-view");

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

        List<DepartmentModel> departmentModelList = departmentRestfulService.getAllDepartments();

        Grid<DepartmentModel> grid = new Grid<>(DepartmentModel.class, false);

        grid.addColumn("departmentId").setAutoWidth(true);
        grid.addColumn("departmentName").setAutoWidth(true);
        grid.addColumn("departmentFloorNumber").setAutoWidth(true);
        grid.addColumn("departmentDescription").setAutoWidth(true);
        grid.addColumn("departmentBudget").setAutoWidth(true);
        grid.addColumn("departmentCreationDate").setAutoWidth(true);

        grid.setItems(departmentModelList);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }

}
