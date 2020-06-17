package eapli.base.exporter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface Exporter {

    Element begin(Document doc, Element root);

    void element(Document doc, Element parent, Object e);

    void elementSeparator();

    void end();

    void cleanup();

}
