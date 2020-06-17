package eapli.base.machinemanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.domain.Machine;
import eapli.base.machinemanagement.repositories.MachineRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Date;

public class ExportMachineController {

    private final MachineExporterFactory factory = new MachineExporterFactory();
    private final MachineExporterService exporterService = new MachineExporterService();
    private final MachineRepository machRepo = PersistenceContext.repositories().machineRepository();

    public void export(Document doc, Element root, String format) {
        final Exporter exporter = factory.build(format);

        final Iterable<Machine> machines = machRepo.findAll();
        exporterService.export(machines, doc, root, exporter);
    }

    public void exportWithDate(Document doc, Element root, String format, Date left, Date right) {
        final Exporter exporter = factory.build(format);

        final Iterable<Machine> machines = machRepo.machinesBetweenDates(left, right);
        exporterService.export(machines, doc, root, exporter);
    }
}
