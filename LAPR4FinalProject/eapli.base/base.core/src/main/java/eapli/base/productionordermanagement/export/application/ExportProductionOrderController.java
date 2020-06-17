package eapli.base.productionordermanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionordermanagement.domain.ProductionOrder;
import eapli.base.productionordermanagement.repositories.ProductionOrderRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Pedro Borda de Água
 */
public class ExportProductionOrderController {
    private final eapli.base.productionordermanagement.export.application.ProductionOrderExporterFactory factory = new eapli.base.productionordermanagement.export.application.ProductionOrderExporterFactory();
    private final eapli.base.productionordermanagement.export.application.ProductionOrderExporterService exporterService = new eapli.base.productionordermanagement.export.application.ProductionOrderExporterService();
    private final ProductionOrderRepository prodOrderRepo = PersistenceContext.repositories().productionOrderRepository();

    public void export(Document doc, Element root, String format) {
        final Exporter exporter = factory.build(format);

        final Iterable<ProductionOrder> prodOrders = prodOrderRepo.findAll();
        exporterService.export(prodOrders, doc, root, exporter);
    }

    public void exportWithDate(Document doc, Element root, String format, LocalDate leftDateProdOrder, LocalDate rightDateProdOrder) {
        final Exporter exporter = factory.build(format);

        final Iterable<ProductionOrder> prodOrders = prodOrderRepo.productionOrdersBetweenDates(leftDateProdOrder, rightDateProdOrder);
        exporterService.export(prodOrders, doc, root, exporter);
    }
}
