/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.depositsmanagement.export.application;

import eapli.base.depositsmanagement.domain.Deposit;
import eapli.base.depositsmanagement.repositories.DepositRepository;
import eapli.base.exporter.Exporter;
import eapli.base.infrastructure.persistence.PersistenceContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Tiago Ribeiro
 */
public class ExportDepositsController {
    private final DepositExporterFactory factory = new DepositExporterFactory();
    private final DepositExporterService exporterService = new DepositExporterService();
    private final DepositRepository repository = PersistenceContext.repositories().depositRepository();

    public void export(Document doc, Element root, String format) {
        final Exporter exporter = factory.build(format);

        final Iterable<Deposit> machines = repository.findAll();
        exporterService.export(machines, doc, root, exporter);
    }
}
