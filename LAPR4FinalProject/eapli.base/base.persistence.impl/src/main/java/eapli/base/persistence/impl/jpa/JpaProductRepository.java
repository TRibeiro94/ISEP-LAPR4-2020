package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class JpaProductRepository extends BasepaRepositoryBase<Product, String, String> implements ProductRepository {

    JpaProductRepository(String identityFieldName) {
        super(identityFieldName, "code");
    }

    @Override
    public Iterable<Product> allProductsWithoutBOM() {
        return match("e.hasABillOfMaterials=false");
    }
    
    @Override
    public Product productById(String productCode){
        final TypedQuery<Product> query = createQuery("SELECT m FROM Product m WHERE m.code = :pCode", Product.class);
        query.setParameter("pCode", productCode);
        try{
            return query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
}
