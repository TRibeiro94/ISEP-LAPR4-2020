package eapli.base.productionordermanagement.application;

import eapli.base.productionordermanagement.domain.ReaderPO;

/**
 *
 * @author Pedro Borda de Água
 */
public class ProductionOrderImport {

    ReaderPO reader = new ReaderPO();

    public void importer(String fileName){
        reader.importProductionOrder(fileName);
    }
}
