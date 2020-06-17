package eapli.base.rawmaterialmanagement.domain;

import org.junit.Test;

public class RawMaterialCategoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveId(){
        new RawMaterialCategory(null, "Category ABC");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveDescription(){
        new RawMaterialCategory("PT12467", null);
    }

    @Test
    public void ensureRawMaterialCategoryCreatedWithSuccess(){
        new RawMaterialCategory("PT12467", "Category AVFX");
        assert(true);
    }
}