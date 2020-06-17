/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productmanagement.export.application;

import eapli.base.exporter.Exporter;

/**
 * @author Tiago Ribeiro
 */
public class ProductExporterFactory {
    public Exporter build(String format) {
        switch (format) {
            case "XML":
                return new XMLProductExporter();
        }
        throw new IllegalStateException("Unknown format");
    }
}
