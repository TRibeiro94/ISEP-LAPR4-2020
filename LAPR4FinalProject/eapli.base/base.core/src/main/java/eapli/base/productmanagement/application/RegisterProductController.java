package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.BillOfMaterials;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;

/**
 * This class registers a Product in the database
 */
public class RegisterProductController {

    //adicionar autenticacao
    private final ProductRepository repository = PersistenceContext.repositories().productRepository();


    /**
     * This method registers a new Product in the database
     *
     * @param code             The code of the Product
     * @param commercialCode   The commercialCode of the Product
     * @param briefDescription The briefDescription of the Product
     * @param fullDescription  The fullDescription of the Product
     * @param cat              The product category
     * @param rawMaterial      The raw material in the product
     * @param quantity
     * @param measureUnitProd
     * @param measureUnitRaw
     * @return the instance saved in the database
     */
    public Product registerProduct(String code, String commercialCode, String briefDescription, String fullDescription, String cat, RawMaterial rawMaterial, Integer quantity, String measureUnitProd, String measureUnitRaw) {
        final Product newProduct = new Product(code, commercialCode, briefDescription, fullDescription, cat, measureUnitProd, new BillOfMaterials(rawMaterial, quantity, measureUnitRaw));
        return this.repository.save(newProduct);
    }

    public Product registerProductWithoutBOM(String code, String commercialCode, String briefDescription, String fullDescription, String cat, String measureUnit) {
        final Product newProduct = new Product(code, commercialCode, briefDescription, fullDescription, cat, measureUnit);
        return this.repository.save(newProduct);
    }

    public boolean checkAvailableCode(String code) {
        boolean codeAvailability = Product.verifyCode(code, this.getProducts());

        if (codeAvailability == false) {
            return false;
        }
        return true;
    }

    public Iterable<Product> getProducts() {
        return this.repository.findAll();
    }
}
