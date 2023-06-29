package com.appsbykeegan.frontendcrudui.views;

import com.appsbykeegan.frontendcrudui.views.department.CreateDepartmentView;
import com.appsbykeegan.frontendcrudui.views.employee.CreateemployeeView;
import com.appsbykeegan.frontendcrudui.views.department.DepartmentsView;
import com.appsbykeegan.frontendcrudui.views.employee.EmployeesView;
import com.appsbykeegan.frontendcrudui.views.splash.SplashView;
import com.appsbykeegan.frontendcrudui.views.department.DepartmentManagementView;
import com.appsbykeegan.frontendcrudui.views.employee.EmployeeManagementView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Crud-UI App");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Splash", SplashView.class, LineAwesomeIcon.FILE_POWERPOINT.create()));

        nav.addItem(new SideNavItem(""));

        nav.addItem(
                new SideNavItem("Create department", CreateDepartmentView.class, LineAwesomeIcon.BUILDING.create()));
        nav.addItem(new SideNavItem("Manage department", DepartmentManagementView.class,
                LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem("Departments", DepartmentsView.class, LineAwesomeIcon.CITY_SOLID.create()));

        nav.addItem(new SideNavItem(""));

        nav.addItem(new SideNavItem("Create employee", CreateemployeeView.class, LineAwesomeIcon.USER.create()));
        nav.addItem(new SideNavItem("Manage employee", EmployeeManagementView.class,
                LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem("Employees", EmployeesView.class, LineAwesomeIcon.PEOPLE_CARRY_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
