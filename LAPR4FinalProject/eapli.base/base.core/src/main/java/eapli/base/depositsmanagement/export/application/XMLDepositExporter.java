/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.depositsmanagement.export.application;

import eapli.base.depositsmanagement.domain.Deposit;
import eapli.base.exporter.Exporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Tiago Ribeiro
 */
public class XMLDepositExporter implements Exporter {

    @Override
    public Element begin(Document doc, Element root) {
        Element depRoot = doc.createElement("deposits");
        root.appendChild(depRoot);
        return depRoot;
    }

    @Override
    public void element(Document doc, Element parent, Object dep) {
        Deposit deposit = (Deposit) dep;

        Element d = doc.createElement("deposit");

        Element code = doc.createElement("code");
        code.appendChild(doc.createTextNode(deposit.identity()));
        d.appendChild(code);

        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode(deposit.consultDepositsDescription()));
        d.appendChild(description);

        parent.appendChild(d);
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
