package eapli.base.rawmaterialmanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.framework.util.TemplateMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RawMaterialExporterService {

    @TemplateMethod
    public void export(Iterable<RawMaterial> rawMaterials, Document doc, Element root, Exporter exporter) {
        try {
            final Element parent = exporter.begin(doc, root);

            boolean hasPrevious = false;
            for (final RawMaterial e : rawMaterials) {
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
