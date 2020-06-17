package eapli.base.rawmaterialmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * The information about a Raw Material
 */
@Entity
public class RawMaterial implements AggregateRoot<String> {

    @Id
    private String code;

    private String tecnicalSheet;

    private String description;

    @OneToOne
    private RawMaterialCategory category;

    protected RawMaterial() {
        //for ORM
    }

    public RawMaterial(String code, String tecnicalSheet, String description, RawMaterialCategory category) {
        Preconditions.noneNull(code, tecnicalSheet, description, category);
        this.code = code;
        this.tecnicalSheet = tecnicalSheet;
        this.description = description;
        this.category = category;
    }

    /**
     * This method compares RawMaterial to other objects
     *
     * @param other The object to be compared
     * @return true if equal, false if not
     */
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof RawMaterial)) {
            return false;
        }

        final RawMaterial that = (RawMaterial) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && tecnicalSheet.equals(that.tecnicalSheet)
                && description().equals(that.description()) && category.equals(that.category);
    }

    /**
     * This method gets the identity of the instance
     *
     * @return the code field
     */
    @Override
    public String identity() {
        return this.code;
    }

    public String technicalSheet() {
        return this.tecnicalSheet;
    }

    public String description() {
        return this.description;
    }

    public String categoryId() {
        return this.category.identity();
    }

    public String categoryDescription() {
        return this.category.getDescription();
    }
}
