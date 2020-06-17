/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productmanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.util.TemplateMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Tiago Ribeiro
 */
public class ProductExporterService {

    @TemplateMethod
    public void export(Iterable<Product> prods, Document doc, Element root, Exporter exporter) {
        try {
            final Element parent = exporter.begin(doc, root);

            boolean hasPrevious = false;
            for (final Product p : prods) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }
                exporter.element(doc, parent, p);
                hasPrevious = true;
            }

            exporter.end();
        } finally {
            exporter.cleanup();
        }
    }
}
