package eapli.base.rawmaterialmanagement.domain;

import junit.framework.TestCase;
import org.junit.Test;

public class RawMaterialTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveCategory() {
        new RawMaterial("PT50", "Exemplo1", "ExemploDescricao1", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveCode() {
        new RawMaterial(null, "Exemplo2", "ExemploDescricao2", new RawMaterialCategory("CAT14A", "Categoria1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveTechnicalSheet() {
        new RawMaterial("PT500", null, "ExemploDescricao2", new RawMaterialCategory("CAT14A", "Categoria1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveDescription() {
        new RawMaterial("PT500", "Exemplo346", null, new RawMaterialCategory("CAT14A", "Categoria1"));
    }

    @Test
    public void ensureRawMaterialIsCreatedWithSuccess() {
        new RawMaterial("PT246", "Exemplo2", "ExemploDescricao2", new RawMaterialCategory("CAT14A", "Categoria1"));
    }
}