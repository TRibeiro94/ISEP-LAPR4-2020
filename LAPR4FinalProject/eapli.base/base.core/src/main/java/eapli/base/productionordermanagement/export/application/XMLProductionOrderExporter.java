package eapli.base.productionordermanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.productionordermanagement.domain.ProductionOrder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Pedro Borda de Água
 */
public class XMLProductionOrderExporter implements Exporter {

    @Override
    public Element begin(Document doc, Element root) {
        Element prodOrderRoot = doc.createElement("prodOrders");
        root.appendChild(prodOrderRoot);
        return prodOrderRoot;
    }

    @Override
    public void element(Document doc, Element parent, Object e) {
        ProductionOrder po = (ProductionOrder) e;
        Element prodOrder = doc.createElement("ProductionOrder");

        Element code = doc.createElement("code");
        code.appendChild(doc.createTextNode(po.identity()));
        prodOrder.appendChild(code);

        Element emissionDate = doc.createElement("EmissionDate");
        emissionDate.appendChild(doc.createTextNode(po.emissionDate().toString()));
        prodOrder.appendChild(emissionDate);

        Element expectedDate = doc.createElement("ExpectedDate");
        expectedDate.appendChild(doc.createTextNode(po.expectedDate().toString()));
        prodOrder.appendChild(expectedDate);

        Element productCode = doc.createElement("ProductCode");
        productCode.appendChild(doc.createTextNode(po.prodCode()));
        prodOrder.appendChild(productCode);

        Element quantity = doc.createElement("Quantity");
        quantity.appendChild(doc.createTextNode(String.valueOf(po.quantity().checkQuantity())));
        prodOrder.appendChild(quantity);

        Element unity = doc.createElement("Unit");
        unity.appendChild(doc.createTextNode(po.quantity().measureUnit()));
        prodOrder.appendChild(unity);

        Element reqCodes = doc.createElement("ReqCodes");
        reqCodes.appendChild(doc.createTextNode(po.reqCodes()));
        prodOrder.appendChild(reqCodes);

        parent.appendChild(prodOrder);
    }

    @Override
    public void elementSeparator() {
        //Nothing to do
    }

    @Override
    public void end() {
        //Nothing to do
    }

    @Override
    public void cleanup() {
        //Nothing to do
    }
}
