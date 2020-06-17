package eapli.base.machinemanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.machinemanagement.domain.Machine;
import eapli.framework.util.TemplateMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MachineExporterService {

    @TemplateMethod
    public void export(Iterable<Machine> machines, Document doc, Element root, Exporter exporter) {
        try {
            final Element parent = exporter.begin(doc, root);

            boolean hasPrevious = false;
            for (final Machine e : machines) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }
                exporter.element(doc, parent, e);
                hasPrevious = true;
            }

            exporter.end();
        } finally {
            exporter.cleanup();
        }
    }
}
