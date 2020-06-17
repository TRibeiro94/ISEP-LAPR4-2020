package eapli.base.productmanagement.domain;

import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.OneToOne;

@Entity
public class BillOfMaterials implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @ManyToOne
    private RawMaterial rawMaterial;
    @ManyToOne
    private Product product;
    private Quantity quantity;

    protected BillOfMaterials() {
        //for ORM
    }

    public BillOfMaterials(RawMaterial rawMaterial, Integer quantity, String unitMeasure) {
        Preconditions.noneNull(rawMaterial, quantity);
        this.rawMaterial = rawMaterial;
        this.quantity = new Quantity(quantity, unitMeasure);
    }

    public BillOfMaterials(Product product, Integer quantity, String unitMeasure) {
        Preconditions.noneNull(product, quantity);
        this.product = product;
        this.quantity = new Quantity(quantity, unitMeasure);
    }

    public String checkBOMItemIdentifier() {
        if (this.product == null) {
            return rawMaterial.identity();
        } else {
            return product.identity();
        }
    }

    public int checkQuantity() {
        return this.quantity.checkQuantity();
    }

    public String checkMeasureUnit() {
        return String.format(this.quantity.measureUnit());
    }

    public String identity() {
        return String.valueOf(this.pk);
    }

    public Product isItProduct() {
        return this.product;
    }

    public RawMaterial isItRawMaterial() {
        return this.rawMaterial;
    }
}
