/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.BillOfMaterials;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialRepository;

import java.util.List;

/**
 * @author Tiago Ribeiro
 */
public class BOMController {

    private final ProductRepository repository = PersistenceContext.repositories().productRepository();
    private final RawMaterialRepository rawMaterialRepository = PersistenceContext.repositories().rawMaterials();

    /**
     * Specifies a bill of materials for a product
     *
     * @param prod product
     * @param bom  bill of materials
     * @return the product with a BOM defined
     */
    public Product specifyBOMItem(Product prod, BillOfMaterials bom) {
        prod.addBOMtoProduct(bom);
        return this.repository.save(prod);
    }

    /**
     * updates bill of materials of an item
     *
     * @param prod product
     * @param bom  bill of materials
     */
    public void updateBOM(Product prod, BillOfMaterials bom) {
        List<Product> list = (List<Product>) repository.findAll();
        for (Product product : list) {
            if (prod.equals(product)) {
                repository.delete(product);
                prod.addBOMtoProduct(bom);
                repository.save(prod);
            }
        }
    }

    public Iterable<Product> getProducts() {
        return this.repository.findAll();
    }

    public Iterable<RawMaterial> getRawMaterials() {
        return this.rawMaterialRepository.findAll();
    }

    public Product productByIdentity(String code) {
        for (Product prod : this.repository.findAll()) {
            if (prod.identity().equalsIgnoreCase(code)) {
                return prod;
            }
        }
        return null;
    }

    public RawMaterial rawMaterialByIdentity(String code) {
        for (RawMaterial rm : this.rawMaterialRepository.findAll()) {
            if (rm.identity().equalsIgnoreCase(code)) {
                return rm;
            }
        }
        return null;
    }
}
