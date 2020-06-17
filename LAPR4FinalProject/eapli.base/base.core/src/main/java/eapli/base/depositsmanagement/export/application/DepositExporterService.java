/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.depositsmanagement.export.application;

import eapli.base.depositsmanagement.domain.Deposit;
import eapli.base.exporter.Exporter;
import eapli.framework.util.TemplateMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Tiago Ribeiro
 */
public class DepositExporterService {

    @TemplateMethod
    public void export(Iterable<Deposit> deposits, Document doc, Element root, Exporter exporter) {
        try {
            final Element parent = exporter.begin(doc, root);

            boolean hasPrevious = false;
            for (final Deposit dep : deposits) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }
                exporter.element(doc, parent, dep);
                hasPrevious = true;
            }

            exporter.end();
        } finally {
            exporter.cleanup();
        }
    }
}
