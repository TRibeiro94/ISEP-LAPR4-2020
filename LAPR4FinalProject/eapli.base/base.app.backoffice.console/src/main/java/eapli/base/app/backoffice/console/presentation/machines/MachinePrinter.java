package eapli.base.app.backoffice.console.presentation.machines;

import eapli.base.machinemanagement.domain.Machine;
import eapli.framework.visitor.Visitor;

public class MachinePrinter implements Visitor<Machine> {

    @Override
    public void visit(Machine visitee) {
        System.out.printf("%s", visitee.identity());
    }
}
