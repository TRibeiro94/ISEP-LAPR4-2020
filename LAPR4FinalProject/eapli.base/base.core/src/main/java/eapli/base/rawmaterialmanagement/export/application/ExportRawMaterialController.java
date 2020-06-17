package eapli.base.rawmaterialmanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ExportRawMaterialController {

    private final RawMaterialExporterFactory factory = new RawMaterialExporterFactory();
    private final RawMaterialExporterService exporterService = new RawMaterialExporterService();
    private final RawMaterialRepository rawMatRepo = PersistenceContext.repositories().rawMaterials();

    public void export(Document doc, Element root, String format) {
        final Exporter exporter = factory.build(format);

        final Iterable<RawMaterial> rawMaterials = rawMatRepo.findAll();
        exporterService.export(rawMaterials, doc, root, exporter);
    }
}
