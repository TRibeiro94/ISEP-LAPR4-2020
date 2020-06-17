package eapli.base.machinemanagement.export.application;

import eapli.base.exporter.Exporter;

public class MachineExporterFactory {

    public Exporter build(String format) {
        switch (format) {
            case "XML":
                return new XMLMachineExporter();
        }
        throw new IllegalStateException("Unknown format");
    }

}
