/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productmanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Tiago Ribeiro
 */
public class ExportProductController {
    private final ProductExporterFactory factory = new ProductExporterFactory();
    private final ProductExporterService exporterService = new ProductExporterService();
    private final ProductRepository prodRepo = PersistenceContext.repositories().productRepository();

    public void export(Document doc, Element root, String format) {
        final Exporter exporter = factory.build(format);

        final Iterable<Product> products = prodRepo.findAll();
        exporterService.export(products, doc, root, exporter);
    }
}
