package eapli.base.machinemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.domain.Machine;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.base.productionlinemanagement.application.ProductionLineService;
import eapli.base.productionlinemanagement.domain.ProductionLine;

import java.util.Date;
import java.util.List;

public class RegisterMachineController {

    private final MachineRepository machineRepo = PersistenceContext.repositories().machineRepository();
    private final ProductionLineService listProdServ = new ProductionLineService();

    public Iterable<ProductionLine> getProductionLines() {
        return this.listProdServ.allProductionLines();
    }

    private void checkAvailablePosition(ProductionLine prodLine, Integer position) {
        final List<Machine> machines = this.machineRepo.machinesWithXProductionLine(prodLine.identity());
        Machine.verifyPosition(machines, position);
    }

    public void registerMachine(ProductionLine theProductionLine, String machineCode, String serialNumber, String protocol, String description, String brand, String model, Date installDate, Integer position) {
        checkAvailablePosition(theProductionLine, position);
        Machine newM = new Machine(machineCode, serialNumber, protocol, description, brand, model, installDate, theProductionLine, position);
        this.machineRepo.save(newM);
    }
}
