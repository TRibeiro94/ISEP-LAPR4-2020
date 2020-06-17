package eapli.base.app.backoffice.console.presentation.exporter;

import eapli.base.exporter.ExportDecisions;
import eapli.base.exporter.ExportToAXMLFileController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

import javax.xml.parsers.ParserConfigurationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExportToAXMLFileUI extends AbstractUI {

    private final ExportToAXMLFileController theController = new ExportToAXMLFileController();
    private final ExportDecisions decision = new ExportDecisions();

    public ExportToAXMLFileUI() throws ParserConfigurationException {
    }

    @Override
    protected boolean doShow() {
        System.out.println("Do you want to export machines? 1-Yes, 0-No");
        int selectedOption;
        selectedOption = Console.readOption(0, 1, -1);
        if (selectedOption == -1) {
            return false;
        }
        if (selectedOption == 1) {
            System.out.println("Do you want a specific range on the installation date?");
            selectedOption = Console.readOption(0, 1, -1);
            if (selectedOption == 1) {
                this.decision.leftDateMachine(Console.readDate("Insert the oldest date"));
                this.decision.rightDateMachine(Console.readDate("Insert the most recent date"));
                this.decision.activateMachineDateRange();
            }
            this.decision.activateExportMachines();
        }
        System.out.println("Do you want to export Raw Materials? 1-Yes, 0-No");
        selectedOption = Console.readOption(0, 1, -1);
        if (selectedOption == -1) {
            return false;
        }
        if (selectedOption == 1) {
            this.decision.activateExportRawMaterials();
        }
        System.out.println("Do you want to export Production Orders? 1-Yes, 0-No");
        selectedOption = Console.readOption(0, 1, -1);
        if (selectedOption == -1) {
            return false;
        }
        if (selectedOption == 1) {
            System.out.println("Do you want a specific range on the emission date?");
            selectedOption = Console.readOption(0, 1, -1);
            if (selectedOption == 1) {
                this.decision.leftDateProdOrder(convertStringToDate(Console.readLine("Insert the oldest date")));
                this.decision.rightDateProdOrder(convertStringToDate(Console.readLine("Insert the most recent date")));
                this.decision.activateProductionOrderDateRange();
            }
            this.decision.activateExportProductionOrders();
        }
        System.out.println("Do you want to export Deposits? 1-Yes, 0-No");
        selectedOption = Console.readOption(0, 1, -1);
        if (selectedOption == -1) {
            return false;
        }
        if (selectedOption == 1) {
            this.decision.activateExportDeposits();
        }
        System.out.println("Do you want to export Products? 1-Yes, 0-No");
        selectedOption = Console.readOption(0, 1, -1);
        if (selectedOption == -1) {
            return false;
        }
        if (selectedOption == 1) {
            this.decision.activateExportProducts();
        }
        this.theController.export(decision);
        System.out.println("File saved!");
        return true;
    }

    @Override
    public String headline() {
        return "Export Factory Information to a XML File";
    }

    public LocalDate convertStringToDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate valueDate = LocalDate.parse(value, formatter);
        return valueDate;
    }
}
