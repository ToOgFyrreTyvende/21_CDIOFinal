package dal.Array;

import java.util.List;
import dto.interfaces.IProduct;

public interface IAProductDAO {

    IProduct getProd(int prodId);
    List<IProduct> getProdList();
    void createProd(IProduct prod);
    void updateProd(IProduct prod);
    void deleteProd(int prodId);
}

