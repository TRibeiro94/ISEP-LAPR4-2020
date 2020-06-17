/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;


/**
 * @author Tiago Ribeiro
 */
@Embeddable
public class Quantity implements ValueObject {

    /**
     * unit measures, unit, kilogram, gram, liter, centiliter
     */
    public static enum measureUnitValues {
        UN {
            @Override
            public String toString() {
                return "UN";
            }
        },
        KG {
            @Override
            public String toString() {
                return "KG";
            }
        },
        GR {
            @Override
            public String toString() {
                return "GR";
            }
        },
        LT {
            @Override
            public String toString() {
                return "LT";
            }
        },
        CL {
            @Override
            public String toString() {
                return "CL";
            }
        };
    }

    private int quantity;
    private String measureUnit;

    protected Quantity() {
        //for ORM
    }

    /**
     * New quantity, if the measureUnit isn't recognized, the standard is "Unit"
     *
     * @param quantity
     * @param measureUnit
     */
    public Quantity(int quantity, String measureUnit) {
        Preconditions.noneNull(quantity, measureUnit);
        this.quantity = quantity;
        if (validate(measureUnit) == true) {
            this.measureUnit = measureUnit;
        } else {
            this.measureUnit = measureUnitValues.UN.toString();
        }
    }

    /**
     * validates if the inserted measure unit is within the desired parameters
     *
     * @param measureUnit
     * @return
     */
    public static boolean validate(String measureUnit) {
        for (Object value : measureUnitValues.values()) {
            if (value.toString().equals(measureUnit.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public int checkQuantity() {
        return this.quantity;
        //System.out.printf("\n%d %s", this.quantity, this.measureUnit);
    }

    public String measureUnit() {
        return this.measureUnit;
    }
}
