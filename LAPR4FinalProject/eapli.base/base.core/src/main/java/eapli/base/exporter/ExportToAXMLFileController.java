package eapli.base.exporter;

import eapli.base.depositsmanagement.export.application.ExportDepositsController;
import eapli.base.machinemanagement.export.application.ExportMachineController;
import eapli.base.machinemanagement.export.application.XMLMachineExporter;
import eapli.base.productionordermanagement.export.application.ExportProductionOrderController;
import eapli.base.productmanagement.export.application.ExportProductController;
import eapli.base.rawmaterialmanagement.export.application.ExportRawMaterialController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import java.time.LocalDate;
import java.util.Date;

public class ExportToAXMLFileController {

    private static final String XML_FORMAT = "XML";
    private final XMLBaseExporter baseExporter = new XMLBaseExporter();
    private final Document doc = baseExporter.document();
    private final Element root = baseExporter.rootElement();

    public ExportToAXMLFileController() throws ParserConfigurationException {
    }

    public void export(ExportDecisions decision) {
        if (decision.exportMachines()) {
            if (decision.machineDateRange()) {
                exportMachinesWithDate(decision.leftDateMachine(), decision.rightDateMachine());
            } else {
                exportMachines();
            }
        }
        if (decision.exportDeposits()) {
            exportDeposits();
        }
        if (decision.exportProductionOrders()) {
            if (decision.productionOrderDateRange()) {
                exportProductionOrdersWithDate(decision.leftDateProdOrder(), decision.rightDateProdOrder());
            } else {
                exportProductionorders();
            }
        }
        if (decision.exportProducts()) {
            exportProducts();
        }
        if (decision.exportRawMaterials()) {
            exportRawMaterials();
        }
        this.finishDocument();
    }

    private void exportProductionOrdersWithDate(LocalDate leftDateProdOrder, LocalDate rightDateProdOrder) {
        ExportProductionOrderController exporter = new ExportProductionOrderController();
        exporter.exportWithDate(doc, root, XML_FORMAT, leftDateProdOrder, rightDateProdOrder);
    }

    private void exportMachinesWithDate(Date left, Date right) {
        ExportMachineController exporter = new ExportMachineController();
        exporter.exportWithDate(doc, root, XML_FORMAT, left, right);
    }

    public void exportMachines() {
        ExportMachineController exporter = new ExportMachineController();
        exporter.export(doc, root, XML_FORMAT);
    }

    public void exportRawMaterials() {
        ExportRawMaterialController exporter = new ExportRawMaterialController();
        exporter.export(doc, root, XML_FORMAT);
    }

    public void exportProductionorders() {
        ExportProductionOrderController exporter = new ExportProductionOrderController();
        exporter.export(doc, root, XML_FORMAT);
    }

    public void exportDeposits() {
        ExportDepositsController exporter = new ExportDepositsController();
        exporter.export(doc, root, XML_FORMAT);
    }

    public void exportProducts() {
        ExportProductController exporter = new ExportProductController();
        exporter.export(doc, root, XML_FORMAT);
    }

    public void finishDocument() {
        baseExporter.outputToAFile();
    }
}
