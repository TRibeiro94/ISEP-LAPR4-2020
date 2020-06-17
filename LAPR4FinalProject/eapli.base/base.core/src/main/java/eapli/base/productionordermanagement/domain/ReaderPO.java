package eapli.base.productionordermanagement.domain;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.productionordermanagement.application.ProductionOrderController;
import eapli.base.productionordermanagement.repositories.ProductionOrderRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.System.exit;

/**
 *
 * @author Pedro Borda de Água
 */
public class ReaderPO {

    private final ProductionOrderRepository repository = PersistenceContext.repositories().productionOrderRepository();

    public void importProductionOrder(String fileName){

        try {
            Scanner reader = new Scanner(new File(fileName));
            reader.nextLine();
            reader.useDelimiter(";|\n");

            try {
                while (reader.hasNext()) {
                    String idOrder = reader.next().trim();
                    LocalDate emissionDate = convertStringToDate(reader.next().trim());
                    LocalDate expectedDate = convertStringToDate(reader.next().trim());
                    String productCode = reader.next().trim();
                    String quantity = reader.next().trim();
                    String measureUnit = reader.next().trim();
                    String requestCodes = reader.next().trim();

                    ProductionOrderController poc = new ProductionOrderController();
                    poc.prodOrder(idOrder, emissionDate, expectedDate, productCode, Integer.parseInt(quantity), measureUnit, requestCodes);

                }
            } catch (NoSuchElementException e) {
                System.out.println("Error reading the file. Please check the corresponding.csv file.");
                e.printStackTrace();
                exit(0);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The specified file " +fileName+ " doesn't exist.");
            e.printStackTrace();
            exit(0);
        }
    }

    public LocalDate convertStringToDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate valueDate = LocalDate.parse(value, formatter);
        return valueDate;
    }

}
