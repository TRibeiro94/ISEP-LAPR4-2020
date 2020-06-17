package eapli.base.machinemanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.machinemanagement.domain.Machine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLMachineExporter implements Exporter {

    @Override
    public Element begin(Document doc, Element root) {
        Element machRoot = doc.createElement("machines");
        root.appendChild(machRoot);
        return machRoot;
    }

    @Override
    public void element(Document doc, Element parent, Object machinew) {
        Machine machine = (Machine) machinew;
        //Crio o elemento pai machine
        Element mach = doc.createElement("machine");

        //Crio o elemento filho code e adiciono ao elemento pai machine
        Element code = doc.createElement("code");
        code.appendChild(doc.createTextNode(machine.identity()));
        mach.appendChild(code);

        //Repetir o processo para os outros atributos todos
        Element brand = doc.createElement("brand");
        brand.appendChild(doc.createTextNode(machine.brand()));
        mach.appendChild(brand);

        Element model = doc.createElement("model");
        model.appendChild(doc.createTextNode(machine.model()));
        mach.appendChild(model);

        //Element protocolCode = doc.createElement("protocol");
        //protocolCode.appendChild(doc.createTextNode(machine.protocol()));
        //mach.appendChild(protocolCode);

        Element installDate = doc.createElement("installDate");
        installDate.appendChild(doc.createTextNode(machine.installDate().toString()));
        mach.appendChild(installDate);

        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode(machine.description()));
        mach.appendChild(description);

        Element serialN = doc.createElement("serialNumber");
        serialN.appendChild(doc.createTextNode(machine.serialNumber()));
        mach.appendChild(serialN);

        Element machPos = doc.createElement("machinePosition");
        Element prodLineCode = doc.createElement("productionLine");
        prodLineCode.appendChild(doc.createTextNode(machine.prodLineCode()));
        Element position = doc.createElement("position");
        position.appendChild(doc.createTextNode(machine.position().toString()));
        machPos.appendChild(prodLineCode);
        machPos.appendChild(position);
        mach.appendChild(machPos);

        //No fim de criar o elemento machine completo, adiciono ao pai "machines"
        parent.appendChild(mach);
    }

    @Override
    public void elementSeparator() {
        //nothing to do
    }

    @Override
    public void end() {
        //nothing to do
    }

    @Override
    public void cleanup() {
        //nothing to do
    }
}
