package eapli.base.machinemanagement.repositories;

import eapli.base.machinemanagement.domain.Machine;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Date;
import java.util.List;

public interface MachineRepository extends DomainRepository<String, Machine> {

    List<Machine> machinesWithXProductionLine(String code);

    List<Machine> machinesBetweenDates(Date left, Date right);
    
    Machine machineByID(String machineCode);
}