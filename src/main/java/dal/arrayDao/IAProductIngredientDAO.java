package dal.arrayDao;

import java.util.List;
import dto.interfaces.IProductIngredient;

public interface IAProductIngredientDAO {

    IProductIngredient getProdIng(int prodingId);
    List<IProductIngredient> getProdIngList();
    void createProdIng(IProductIngredient proding);
    void updateProdIng(IProductIngredient proding);
    void deleteProdIng(int prodingId);
}