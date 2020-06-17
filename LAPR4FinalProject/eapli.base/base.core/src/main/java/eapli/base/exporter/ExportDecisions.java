package eapli.base.exporter;

import java.time.LocalDate;
import java.util.Date;

public class ExportDecisions {

    private boolean productionOrderDateRange = false;

    private LocalDate leftDateProdOrder;

    private LocalDate rightDateProdOrder;

    private boolean exportMachines = false;

    private boolean machineDateRange = false;

    private Date leftDateMachine;

    private Date rightDateMachine;

    private boolean exportProducts = false;

    private boolean exportRawMaterials = false;

    private boolean exportProductionOrders = false;

    private boolean exportDeposits = false;

    public ExportDecisions() {
    }

    public void activateExportMachines() {
        this.exportMachines = true;
    }

    public void activateExportProducts() {
        this.exportProducts = true;
    }

    public void activateExportRawMaterials() {
        this.exportRawMaterials = true;
    }

    public void activateExportProductionOrders() {
        this.exportProductionOrders = true;
    }

    public void activateExportDeposits() {
        this.exportDeposits = true;
    }

    public boolean exportMachines() {
        return this.exportMachines;
    }

    public boolean exportProducts() {
        return this.exportProducts;
    }

    public boolean exportRawMaterials() {
        return this.exportRawMaterials;
    }

    public boolean exportProductionOrders() {
        return this.exportProductionOrders;
    }

    public boolean exportDeposits() {
        return this.exportDeposits;
    }

    public boolean machineDateRange() {
        return machineDateRange;
    }

    public void activateMachineDateRange() {
        this.machineDateRange = true;
    }

    public void leftDateMachine(Date leftDateMachine) {
        this.leftDateMachine = leftDateMachine;
    }

    public void rightDateMachine(Date rightDateMachine) {
        this.rightDateMachine = rightDateMachine;
    }

    public Date leftDateMachine() {
        return this.leftDateMachine;
    }

    public Date rightDateMachine() {
        return this.rightDateMachine;
    }

    public LocalDate rightDateProdOrder() {
        return this.rightDateProdOrder;
    }

    public void rightDateProdOrder(LocalDate rightDateProdOrder) {
        this.rightDateProdOrder = rightDateProdOrder;
    }

    public LocalDate leftDateProdOrder() {
        return this.leftDateProdOrder;
    }

    public void leftDateProdOrder(LocalDate leftDateProdOrder) {
        this.leftDateProdOrder = leftDateProdOrder;
    }

    public boolean productionOrderDateRange() {
        return this.productionOrderDateRange;
    }

    public void activateProductionOrderDateRange() {
        this.productionOrderDateRange = true;
    }
}
