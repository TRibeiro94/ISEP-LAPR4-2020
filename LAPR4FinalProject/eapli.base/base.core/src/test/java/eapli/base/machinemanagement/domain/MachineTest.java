package eapli.base.machinemanagement.domain;

import eapli.base.productionlinemanagement.domain.ProductionLine;
import org.junit.Test;

import java.util.Date;

public class MachineTest {

    private static final String CODE = "PRT5703";

    private static final String BRAND = "Bosch";

    private static final String MODEL = "Modelo24";

    private static final String SERIAL_NUMBER = "A84JDK692H4";

    private static final String DESCRIPTION = "Maquina de cortar";

    private static final Date INSTALLATION_DATE = new Date(2020, 02, 20);

    private static final Integer POSITION = 3;

    private static final ProductionLine PRODUCTION_LINE = new ProductionLine("PROD_1");
    
    private static final String PROTOCOL_ID = "135";

    @Test(expected = IllegalArgumentException.class)
    public void ensureMachineCantHaveNullCode() {
        new Machine(null, SERIAL_NUMBER, PROTOCOL_ID, DESCRIPTION, BRAND, MODEL, INSTALLATION_DATE, PRODUCTION_LINE, POSITION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMachineCantHaveNullBrand() {
        new Machine(CODE, SERIAL_NUMBER, PROTOCOL_ID, DESCRIPTION, null, MODEL, INSTALLATION_DATE, PRODUCTION_LINE, POSITION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMachineCantHaveNullModel() {
        new Machine(CODE, SERIAL_NUMBER, PROTOCOL_ID, DESCRIPTION, BRAND, null, INSTALLATION_DATE, PRODUCTION_LINE, POSITION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMachineCantHaveNullSerialNumber() {
        new Machine(CODE, null, PROTOCOL_ID, DESCRIPTION, BRAND, MODEL, INSTALLATION_DATE, PRODUCTION_LINE, POSITION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMachineCantHaveNullDescription() {
        new Machine(CODE, SERIAL_NUMBER, PROTOCOL_ID, null, BRAND, MODEL, INSTALLATION_DATE, PRODUCTION_LINE, POSITION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMachineCantHaveNullInstallationDate() {
        new Machine(CODE, SERIAL_NUMBER, PROTOCOL_ID, DESCRIPTION, BRAND, MODEL, null, PRODUCTION_LINE, POSITION);
    }
}