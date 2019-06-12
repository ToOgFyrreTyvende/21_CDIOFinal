package functionality.interfaces;

import dto.interfaces.IProduct;

import java.util.List;

public interface IProductFunctionality {
    void createProduct(IProduct prod) throws Exception;

    IProduct getProduct(int prodId) throws Exception;

    List<IProduct> getAllProducts() throws Exception;

    void updateProduct(IProduct product) throws Exception;

    void deleteProduct(IProduct product) throws Exception;
}
