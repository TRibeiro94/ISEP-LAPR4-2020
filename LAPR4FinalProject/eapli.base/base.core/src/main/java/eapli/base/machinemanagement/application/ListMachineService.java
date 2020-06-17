package eapli.base.machinemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.domain.Machine;
import eapli.base.machinemanagement.repositories.MachineRepository;

public class ListMachineService {

    private final MachineRepository machRepo = PersistenceContext.repositories().machineRepository();


    public Iterable<Machine> allMachines() {
        return this.machRepo.findAll();
    }

    public boolean checkIdMachine(String idmachine) {
        for (Machine m : allMachines()) {
            if (m.protocol().equalsIgnoreCase(idmachine)) {
                return true;
            }
        }
        return false;
    }

    public String getIDPLByIdMachine(String idmachine) {
        for (Machine m : allMachines()) {
            if (m.protocol().equalsIgnoreCase(idmachine)) {
                return m.prodLineCode();
            }
        }
        return null;
    }
}
