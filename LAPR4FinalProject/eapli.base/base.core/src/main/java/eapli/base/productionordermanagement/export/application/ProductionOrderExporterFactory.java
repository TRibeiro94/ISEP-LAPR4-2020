package eapli.base.productionordermanagement.export.application;

import eapli.base.exporter.Exporter;

/**
 *
 * @author Pedro Borda de Água
 */
public class ProductionOrderExporterFactory {

    public Exporter build(String format) {
        switch (format) {
            case "XML":
                return new XMLProductionOrderExporter();
        }
        throw new IllegalStateException("Unknown format");
    }
}
