/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productmanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.productmanagement.domain.BillOfMaterials;
import eapli.base.productmanagement.domain.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Tiago Ribeiro
 */
public class XMLProductExporter implements Exporter {

    @Override
    public Element begin(Document doc, Element root) {
        Element prodRoot = doc.createElement("products");
        root.appendChild(prodRoot);
        return prodRoot;
    }

    @Override
    public void element(Document doc, Element parent, Object p) {
        Product prod = (Product) p;
        Element product = doc.createElement("product");

        Element code = doc.createElement("code");
        code.appendChild(doc.createTextNode(prod.identity()));
        product.appendChild(code);

        Element commercialCode = doc.createElement("commercialCode");
        commercialCode.appendChild(doc.createTextNode(prod.commercialCode()));
        product.appendChild(commercialCode);

        Element briefDescription = doc.createElement("briefDescription");
        briefDescription.appendChild(doc.createTextNode(prod.getBriefDescription()));
        product.appendChild(briefDescription);

        Element fullDescription = doc.createElement("fullDescription");
        fullDescription.appendChild(doc.createTextNode(prod.getFullDescription()));
        product.appendChild(fullDescription);

        Element category = doc.createElement("category");
        category.appendChild(doc.createTextNode(prod.productCategory()));
        product.appendChild(category);

        Element mu = doc.createElement("measureUnit");
        mu.appendChild(doc.createTextNode(prod.measureUnit()));
        product.appendChild(mu);

        Element hasBOM = doc.createElement("existenceOfBOM");
        hasBOM.appendChild(doc.createTextNode(String.valueOf(prod.doesProductHaveBOM())));
        product.appendChild(hasBOM);

        Element billOfMaterials = doc.createElement("billOfMaterials");
        product.appendChild(billOfMaterials);

        for (BillOfMaterials b : prod.consultBOMS()) {
            Element bill = doc.createElement("identity");
            bill.appendChild(doc.createTextNode(b.identity()));
            billOfMaterials.appendChild(bill);

            if (b.isItProduct() != null) {
                Element prodId = doc.createElement("id");
                prodId.appendChild(doc.createTextNode(b.isItProduct().identity()));
                billOfMaterials.appendChild(prodId);
            } else {
                Element prodId = doc.createElement("id");
                prodId.appendChild(doc.createTextNode(b.isItRawMaterial().identity()));
                billOfMaterials.appendChild(prodId);
            }

            Element quantity = doc.createElement("quantity");
            quantity.appendChild(doc.createTextNode(String.valueOf(b.checkQuantity())));
            billOfMaterials.appendChild(quantity);

            Element meas = doc.createElement("measureUnit");
            meas.appendChild(doc.createTextNode(String.valueOf(b.checkMeasureUnit())));
            billOfMaterials.appendChild(meas);
        }

        parent.appendChild(product);
    }

    @Override
    public void elementSeparator() {
        // N/A
    }

    @Override
    public void end() {
        // N/A
    }

    @Override
    public void cleanup() {
        // N/A
    }

}
