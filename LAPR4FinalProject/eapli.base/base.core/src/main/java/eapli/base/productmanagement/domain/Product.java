package eapli.base.productmanagement.domain;

import static eapli.base.productmanagement.domain.Quantity.validate;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product implements AggregateRoot<String> {

    @Id
    private String code;
    private String commercialCode;
    private String briefDescription;
    private String fullDescription;
    private String category;
    private String measureUnit;

    private boolean hasABillOfMaterials;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<BillOfMaterials> productionSheet = new HashSet<>();


    protected Product() {
        //for ORM
    }

    public Product(String code, String commercialCode, String briefDescription, String fullDescription, String category, String measureUnit) {
        this.code = code;
        this.commercialCode = commercialCode;
        this.briefDescription = briefDescription;
        this.fullDescription = fullDescription;
        this.category = category;
        if (validate(measureUnit) == true) {
            this.measureUnit = measureUnit;
        } else {
            this.measureUnit = Quantity.measureUnitValues.UN.toString();
        }
        this.hasABillOfMaterials = false;
    }

    public Product(String code, String commercialCode, String briefDescription, String fullDescription, String category, String measureUnit, BillOfMaterials billOfMaterials) {
        this.code = code;
        this.commercialCode = commercialCode;
        this.briefDescription = briefDescription;
        this.fullDescription = fullDescription;
        this.category = category;
        this.productionSheet.add(billOfMaterials);
        if (validate(measureUnit) == true) {
            this.measureUnit = measureUnit;
        } else {
            this.measureUnit = Quantity.measureUnitValues.UN.toString();
        }
        this.hasABillOfMaterials = true;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Product)) {
            return false;
        }

        final Product that = (Product) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && commercialCode.equals(that.commercialCode)
                && briefDescription.equals(that.briefDescription) && fullDescription.equals(that.fullDescription) && category.equals(that.category);
    }

    @Override
    public String identity() {
        return this.code;
    }

    public String getBriefDescription() {
        return this.briefDescription;
    }

    public String getFullDescription() {
        return this.fullDescription;
    }

    public boolean doesProductHaveBOM() {
        return hasABillOfMaterials;
    }

    public void alterBOMPossession() {
        if (hasABillOfMaterials == true) {
            hasABillOfMaterials = false;
        } else {
            hasABillOfMaterials = true;
        }
    }

    public String commercialCode() {
        return String.format(this.commercialCode);
    }

    public String productCategory() {
        return this.category;
    }

    public String measureUnit() {
        return this.measureUnit;
    }

    public void addBOMtoProduct(BillOfMaterials bom) {
        this.alterBOMPossession();
        productionSheet.add(bom);
    }

    public Set<BillOfMaterials> consultBOMS() {
        return productionSheet;
    }

    public static boolean verifyCode(String code, Iterable<Product> products) {
        for (Product p : products) {
            if (p.identity().equalsIgnoreCase(code)) {
                return false;
            }
        }
        return true;
    }
}
