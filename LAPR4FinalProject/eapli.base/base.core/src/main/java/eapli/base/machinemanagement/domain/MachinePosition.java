package eapli.base.machinemanagement.domain;

import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * The information about a Machine Position
 */
@Embeddable
public class MachinePosition implements ValueObject {

    @ManyToOne
    private ProductionLine productionLineCode;

    private Integer position;

    protected MachinePosition() {
    }

    public MachinePosition(ProductionLine prod, Integer position) {
        Preconditions.noneNull(prod, position);
        this.productionLineCode = prod;
        this.position = position;
    }

    public Integer getPosition() {
        return this.position;
    }

    public String prodLineCode() {
        return this.productionLineCode.identity();
    }
}
