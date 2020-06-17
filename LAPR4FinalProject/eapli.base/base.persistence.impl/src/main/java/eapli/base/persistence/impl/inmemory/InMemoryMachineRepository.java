package eapli.base.persistence.impl.inmemory;

import eapli.base.machinemanagement.domain.Machine;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Date;
import java.util.List;

public class InMemoryMachineRepository extends InMemoryDomainRepository<String, Machine> implements MachineRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<Machine> machinesWithXProductionLine(String code) {

        return null;
    }

    @Override
    public List<Machine> machinesBetweenDates(Date left, Date right) {
        return null;
    }

    @Override
    public Machine machineByID(String machineCode) {
        return null;
    }
}
