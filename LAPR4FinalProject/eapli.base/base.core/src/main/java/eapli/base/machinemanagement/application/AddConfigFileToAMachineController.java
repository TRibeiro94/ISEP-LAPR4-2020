package eapli.base.machinemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.domain.Machine;
import eapli.base.machinemanagement.repositories.MachineRepository;

public class AddConfigFileToAMachineController {

    private final ListMachineService listMachineService = new ListMachineService();
    private final MachineRepository machRepo = PersistenceContext.repositories().machineRepository();

    public AddConfigFileToAMachineController() {
    }

    public Iterable<Machine> listAllMachines() {
        return this.listMachineService.allMachines();
    }

    public boolean addConfigFile(Machine machToUpdate, String configFile) {
        machToUpdate.addConfigFile(configFile);
        this.machRepo.save(machToUpdate);
        return true;
    }
}