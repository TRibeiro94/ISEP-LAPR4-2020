package eapli.base.productionlinemanagement.domain;

import eapli.base.machinemanagement.domain.MachinePosition;
import org.junit.Test;

public class MachinePositionTest{

    @Test(expected = IllegalArgumentException.class)
    public void ensureMachinePositionHasNoNullFields(){
        new MachinePosition(null, 5);
    }
}