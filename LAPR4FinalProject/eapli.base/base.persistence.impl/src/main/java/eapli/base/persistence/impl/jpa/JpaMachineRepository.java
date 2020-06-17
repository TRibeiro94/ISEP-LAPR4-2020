package eapli.base.persistence.impl.jpa;

import eapli.base.machinemanagement.domain.Machine;
import eapli.base.machinemanagement.repositories.MachineRepository;
import java.util.ArrayList;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

public class JpaMachineRepository extends BasepaRepositoryBase<Machine, String, String> implements MachineRepository {

    public JpaMachineRepository(String persistenceUnitName) {
        super(persistenceUnitName, "code");
    }

    @Override
    public List<Machine> machinesWithXProductionLine(String code) {
        return match("e.machinePos.productionLineCode.code=:x", "x", code);
    }

    @Override
    public List<Machine> machinesBetweenDates(Date left, Date right) {
        final TypedQuery<Machine> query = createQuery("SELECT m FROM Machine m WHERE m.installationDate BETWEEN :dataInicio AND :dataFim", Machine.class);
        query.setParameter("dataInicio", left);
        query.setParameter("dataFim", right);
        return query.getResultList();
    }
    
    @Override
    public Machine machineByID(String machineCode){
        final TypedQuery<Machine> query = createQuery("SELECT m FROM Machine m WHERE m.protocolCode = :machineCode", Machine.class);
        query.setParameter("machineCode", machineCode);
        try{
            return query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
}
