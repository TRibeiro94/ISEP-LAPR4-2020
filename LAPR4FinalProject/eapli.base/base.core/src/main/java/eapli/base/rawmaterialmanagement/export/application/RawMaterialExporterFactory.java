package eapli.base.rawmaterialmanagement.export.application;

import eapli.base.exporter.Exporter;

public class RawMaterialExporterFactory {

    public Exporter build(String format) {
        switch (format) {
            case "XML":
                return new XMLRawMaterialExporter();
        }
        throw new IllegalStateException("Unknown format");
    }
}
