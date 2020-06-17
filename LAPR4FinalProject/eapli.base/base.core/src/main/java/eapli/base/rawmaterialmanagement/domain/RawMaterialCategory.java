package eapli.base.rawmaterialmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RawMaterialCategory implements AggregateRoot<String> {

    @Id
    private String id;

    private String description;

    protected RawMaterialCategory() {
        //for ORM
    }

    public RawMaterialCategory(String id, String description) {
        Preconditions.noneNull(id, description);
        this.id = id;
        this.description = description;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof RawMaterialCategory)) {
            return false;
        }

        final RawMaterialCategory that = (RawMaterialCategory) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && description.equals(that.description);
    }

    @Override
    public String identity() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public static boolean verifyCode(String code, Iterable<RawMaterialCategory> rmCategories) {
        for (RawMaterialCategory rmc : rmCategories) {
            if (rmc.identity().equalsIgnoreCase(code)) {
                return false;
            }
        }
        return true;
    }
}
