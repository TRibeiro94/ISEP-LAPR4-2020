package eapli.base.rawmaterialmanagement.export.application;

import eapli.base.exporter.Exporter;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLRawMaterialExporter implements Exporter {

    @Override
    public Element begin(Document doc, Element root) {
        Element rawMaterialRoot = doc.createElement("rawMaterials");
        root.appendChild(rawMaterialRoot);
        return rawMaterialRoot;
    }

    @Override
    public void element(Document doc, Element parent, Object e) {
        RawMaterial rawMaterial = (RawMaterial) e;
        Element raw = doc.createElement("rawMaterial");

        Element code = doc.createElement("code");
        code.appendChild(doc.createTextNode(rawMaterial.identity()));
        raw.appendChild(code);

        Element technicalSheet = doc.createElement("technicalSheet");
        technicalSheet.appendChild(doc.createTextNode(rawMaterial.technicalSheet()));
        raw.appendChild(technicalSheet);

        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode(rawMaterial.description()));
        raw.appendChild(description);

        Element category = doc.createElement("category");
        raw.appendChild(category);

        Element catId = doc.createElement("id");
        catId.appendChild(doc.createTextNode(rawMaterial.categoryId()));
        category.appendChild(catId);

        Element catDesc = doc.createElement("categoryDescription");
        catDesc.appendChild(doc.createTextNode(rawMaterial.categoryDescription()));
        category.appendChild(catDesc);

        parent.appendChild(raw);
    }

    @Override
    public void elementSeparator() {
        //Nothing to do
    }

    @Override
    public void end() {
        //Nothing to do
    }

    @Override
    public void cleanup() {
        //Nothing to do
    }
}
