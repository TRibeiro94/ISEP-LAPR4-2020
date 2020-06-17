package eapli.base.app.backoffice.console.presentation.exporter;

import eapli.framework.actions.Action;

import javax.xml.parsers.ParserConfigurationException;

public class ExportToAXMLFileAction implements Action {

    @Override
    public boolean execute() {
        try {
            return new ExportToAXMLFileUI().show();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return false;
    }
}
