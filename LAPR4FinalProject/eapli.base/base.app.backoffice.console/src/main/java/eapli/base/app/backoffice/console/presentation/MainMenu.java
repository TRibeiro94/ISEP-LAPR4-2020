/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation;

import eapli.base.app.backoffice.console.presentation.exporter.ExportToAXMLFileAction;
import eapli.base.app.backoffice.console.presentation.machines.RegisterMachineAction;
//import eapli.base.app.backoffice.console.presentation.productionorders.CheckPOByOrderAction;
//import eapli.base.app.backoffice.console.presentation.productionorders.CheckPOByStateAction;
import eapli.base.app.backoffice.console.presentation.productionorders.ImporterPOAction;
import eapli.base.app.backoffice.console.presentation.productionorders.ProductionOrderAction;
import eapli.base.app.backoffice.console.presentation.products.CheckProductsWithoutBOMAction;
import eapli.base.app.backoffice.console.presentation.rawmaterials.RegisterRawMaterialAction;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.billofmaterials.SpecifyBOMAction;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.app.backoffice.console.presentation.deposits.SpecifyDepositAction;
import eapli.base.app.backoffice.console.presentation.exporter.XMLtoHTMLAction;
import eapli.base.app.backoffice.console.presentation.exporter.XMLtoJSONAction;
import eapli.base.app.backoffice.console.presentation.exporter.XMLtoTXTAction;
import eapli.base.app.backoffice.console.presentation.machines.ChooseConfigFileTCPAction;
import eapli.base.app.backoffice.console.presentation.messages.ActivateRecurrentMessageProcessingAction;
import eapli.base.app.backoffice.console.presentation.messages.CheckLastMessageProcessingWasExecutedAction;
import eapli.base.app.backoffice.console.presentation.messages.CheckProductionLineMessageProcessingStateAction;
import eapli.base.app.backoffice.console.presentation.messages.DeactivateRecurrentMessageProcessingAction;
import eapli.base.app.backoffice.console.presentation.messages.RequestMessageProcessingAction;
import eapli.base.app.backoffice.console.presentation.notification.ArchiveNotificationAction;
import eapli.base.app.backoffice.console.presentation.notification.ArchivedNotificationsAction;
import eapli.base.app.backoffice.console.presentation.notification.NotificationAction;
import eapli.base.app.backoffice.console.presentation.productionorders.ProductionOrderByOrderAction;
import eapli.base.app.backoffice.console.presentation.productionorders.ProductionOrderByStateAction;
import eapli.base.app.backoffice.console.presentation.products.RegisterProductAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import eapli.base.app.backoffice.console.presentation.products.ImporterAction;
import eapli.base.app.backoffice.console.presentation.rawmaterials.RegisterRawMaterialCategoryAction;
import eapli.base.app.backoffice.console.presentation.smm.ResetRequestAction;
import eapli.base.app.backoffice.console.presentation.smm.SMMAction;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;

    //MACHINES
    private static final int REGISTER_MACHINE_OPTION = 1;
    private static final int ADD_CONFIG_FILE_OPTION = 2;
    private static final int ADD_CONFIG_FILE_TCP_OPTION = 3;

    //PRODUCTS
    private static final int CHECK_PRODUCTS_WITHOUT_BOM = 1;
    private static final int ADD_PRODUCT_OPTION = 2;
    private static final int IMPORT_PRODUCTS_OPTION = 3;

    //RAW MATERIALS
    private static final int ADD_RAW_MATERIAL_OPTION = 1;
    private static final int ADD_RAW_MATERIAL_CATEGORY_OPTION = 2;

    //DEPOSITS
    private static final int ADD_DEPOSIT_OPTION = 1;

    //BOM
    private static final int ADD_BOM = 1;

    //IMPORTER
    private static final int IMPORT = 1;

    //POM
    private static final int ADD_POM = 1;
    private static final int ADD_POM_MANUAL = 2;
    private static final int CHECK_POM_BY_ORDER = 3;
    private static final int CHECK_POM_BY_STATE = 4;
    
    //NOTIFICATION
    private static final int CHECK_ERRORS = 1;
    private static final int ARCHIVE_ERRORS = 2;
    private static final int CHECK_ARCHIVED_NOTIFICATIONS = 3;

    //EXPORT
    private static final int EXPORT_TO_A_XML_FILE_OPTION = 1;
    private static final int XML_TO_HTML = 2;
    private static final int XML_TO_JSON = 3;
    private static final int XML_TO_TXT = 4;
    
    //SMM
    private static final int SMM = 1;
    private static final int RESET = 2;
    
    //SPM
    private static final int CHECK_PRODUCTION_LINE_MESSAGE_PROCESSING_STATE = 1;
    private static final int ACTIVATE_RECORRENT_MESSAGE_PROCESSING = 2;
    private static final int DEACTIVATE_RECORRENT_MESSAGE_PROCESSING = 3;
    private static final int REQUEST_MESSAGE_PROCESSING = 4;
    private static final int CHECK_LAST_TIME_MESSAGE_PROCESSING_WAS_EXECUTED = 5;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int RAWMATERIALS_OPTION = 3;
    private static final int SETTINGS_OPTION = 4;
    private static final int PRODUCTS_OPTION = 5;
    private static final int EXPORT_OPTION = 6;
    private static final int TRACEABILITY_OPTION = 6;
    private static final int MACHINES_OPTION = 7;
    private static final int DEPOSIT_OPTION = 8;
    private static final int BOM_OPTION = 9;
    private static final int IMPORT_OPTION = 10;
    private static final int IMPORT_OPTION_POM = 11;
    private static final int NOTIFICATION_OPTION = 12;
    private static final int SMM_OPTION = 13;
    private static final int MESSAGES_PROCESSING_OPTION = 14;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.PRODUCT_MANAGER)) {
            final Menu productsMenu = buildProductsMenu();
            mainMenu.addSubMenu(PRODUCTS_OPTION, productsMenu);
            final Menu rawMaterialsMenu = buildRawMaterialMenu();
            mainMenu.addSubMenu(RAWMATERIALS_OPTION, rawMaterialsMenu);
            final Menu bomMenu = buildBOMMenu();
            mainMenu.addSubMenu(BOM_OPTION, bomMenu);
            final Menu exportMenu = buildExportMenu();
            mainMenu.addSubMenu(EXPORT_OPTION, exportMenu);
            final Menu importerMenu = buildImporterMenu();
            mainMenu.addSubMenu(IMPORT_OPTION, importerMenu);
            final Menu importerPOMMenu = buildPOMMenu();
            mainMenu.addSubMenu(IMPORT_OPTION_POM, importerPOMMenu);
        }
        
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.SMM)){
            final Menu smmMenu = buildSMMMenu();
            mainMenu.addSubMenu(SMM_OPTION, smmMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.FACTORY_FLOOR_MANAGER)) {
            final Menu machineMenu = buildMachinesMenu();
            mainMenu.addSubMenu(MACHINES_OPTION, machineMenu);
            final Menu depositMenu = buildDepositMenu();
            mainMenu.addSubMenu(DEPOSIT_OPTION, depositMenu);
            final Menu archiveNotificationsMenu = buildArchiveNotificationMenu();
            mainMenu.addSubMenu(NOTIFICATION_OPTION , archiveNotificationsMenu);
            final Menu messagesProcessingMenu = buildMessagesProcessingMenu();
            mainMenu.addSubMenu(MESSAGES_PROCESSING_OPTION , messagesProcessingMenu);
            
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildExportMenu() {
        final Menu menu = new Menu("Exporter >");

        menu.addItem(EXPORT_TO_A_XML_FILE_OPTION, "Export Factory Floor Info to a XML file", new ExportToAXMLFileAction());
        menu.addItem(XML_TO_HTML, "Transform Factory Info XML into HTML", new XMLtoHTMLAction());
        menu.addItem(XML_TO_JSON, "Transform Factory Info XML into JSON", new XMLtoJSONAction());
        menu.addItem(XML_TO_TXT, "Transform Factory Info XML into TXT", new XMLtoTXTAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildMachinesMenu() {
        final Menu menu = new Menu("Machines >");

        menu.addItem(REGISTER_MACHINE_OPTION, "Register Machine", new RegisterMachineAction());
        menu.addItem(ADD_CONFIG_FILE_OPTION, "Add Configuration File to A Machine", new AddConfigFileAction());
        menu.addItem(ADD_CONFIG_FILE_TCP_OPTION, "By TCP, send a Config File to a Machine.", new ChooseConfigFileTCPAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildProductsMenu() {
        final Menu menu = new Menu("Products >");

        menu.addItem(CHECK_PRODUCTS_WITHOUT_BOM, "Check Products without Bill Of Material", new CheckProductsWithoutBOMAction());
        menu.addItem(ADD_PRODUCT_OPTION, "Add a new Product to the Product Catalog", new RegisterProductAction());
        //menu.addItem(IMPORT_PRODUCTS_OPTION, "Import new Products to the Product Catalog from a text file", new ImporterAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildRawMaterialMenu() {
        final Menu menu = new Menu("Raw Materials >");

        menu.addItem(ADD_RAW_MATERIAL_OPTION, "Add a new Raw Material", new RegisterRawMaterialAction());
        menu.addItem(ADD_RAW_MATERIAL_CATEGORY_OPTION, "Add a new Raw Material Category", new RegisterRawMaterialCategoryAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildDepositMenu() {
        final Menu menu = new Menu("Deposits >");

        menu.addItem(ADD_DEPOSIT_OPTION, "Specify a new deposit", new SpecifyDepositAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildBOMMenu() {
        final Menu menu = new Menu("Bill of Materials >");

        menu.addItem(ADD_BOM, "Insert a product's bill of materials", new SpecifyBOMAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildImporterMenu() {

        final Menu menu = new Menu("Import Product Catalog >");

        menu.addItem(IMPORT, "Import Product Catalog from a CSV", new ImporterAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildPOMMenu() {
        final Menu menu = new Menu("Production Order >");

        menu.addItem(ADD_POM, "Import Production Order from a CSV", new ImporterPOAction());
        menu.addItem(ADD_POM_MANUAL, "New Production Order", new ProductionOrderAction());
        menu.addItem(CHECK_POM_BY_ORDER, "Consult Production Orders by a given Order:", new ProductionOrderByOrderAction());
        menu.addItem(CHECK_POM_BY_STATE, "Consult Production Orders by a given State:", new ProductionOrderByStateAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }
    

    
    private Menu buildArchiveNotificationMenu(){
        final Menu menu = new Menu("Notification Errors >\n");
        menu.addItem(CHECK_ERRORS, "Consult Non Archived Notification Errors", new NotificationAction());
        menu.addItem(ARCHIVE_ERRORS, "Archive Notification Errors", new ArchiveNotificationAction());
        menu.addItem(CHECK_ARCHIVED_NOTIFICATIONS, "Consult archived Notification Errors", new ArchivedNotificationsAction());
        return menu;
    }
    
    private Menu buildMessagesProcessingMenu() {
        final Menu menu = new Menu("Messages Processing >");

        menu.addItem(CHECK_PRODUCTION_LINE_MESSAGE_PROCESSING_STATE, "Check whether a lines's message processing is active or not", new CheckProductionLineMessageProcessingStateAction());
        menu.addItem(ACTIVATE_RECORRENT_MESSAGE_PROCESSING, "Activate recorrently message processing for a production line", new ActivateRecurrentMessageProcessingAction());
        menu.addItem(DEACTIVATE_RECORRENT_MESSAGE_PROCESSING, "Deactivate recorrently message processing for a production line", new DeactivateRecurrentMessageProcessingAction());
        menu.addItem(REQUEST_MESSAGE_PROCESSING, "Request message processing for a production line", new RequestMessageProcessingAction());
        menu.addItem(CHECK_LAST_TIME_MESSAGE_PROCESSING_WAS_EXECUTED, "Check when was the last time the message processing for a production line was executed", new CheckLastMessageProcessingWasExecutedAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }
    
    private Menu buildSMMMenu(){
        final Menu menu = new Menu("Machine Monitorization System >\n");
        menu.addItem(SMM, "Monitorize Machine", new SMMAction());
        menu.addItem(RESET, "Send reset request", new ResetRequestAction());
        return menu;
    }
}
