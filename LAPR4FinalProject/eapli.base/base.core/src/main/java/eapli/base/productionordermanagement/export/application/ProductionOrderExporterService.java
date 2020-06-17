package eapli.base.productionordermanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.productionordermanagement.domain.ProductionOrder;
import eapli.framework.util.TemplateMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Pedro Borda de Água
 */
public class ProductionOrderExporterService {

    @TemplateMethod
    public void export(Iterable<ProductionOrder> productionOrders, Document doc, Element root, Exporter exporter) {
        try {
            final Element parent = exporter.begin(doc, root);

            boolean hasPrevious = false;
            for (final ProductionOrder po : productionOrders) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }

                exporter.element(doc, parent, po);
                hasPrevious = true;
            }
            exporter.end();
        } finally {
            exporter.cleanup();
        }
    }
}
