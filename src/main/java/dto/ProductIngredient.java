package dto;

public class ProductIngredient implements dto.interfaces.IProductIngredient {

    private int productIngredientId;
    private int rawMatId;
    private int productId;
    private int amount;


    public ProductIngredient () {}

    public ProductIngredient (int ingredientIdIn) {
        this.productIngredientId = ingredientIdIn;
    }

    @Override
    public int getProductIngredientId() {
        return this.productIngredientId;
    }
    @Override
    public void setProductIngredientId(int ingredientIdIn) {
        this.productIngredientId = ingredientIdIn;
    }

    @Override
    public int getRawMatId() {
        return this.rawMatId;
    }
    @Override
    public void setRawMatId(int rawMatIdIn) {
        this.rawMatId = rawMatIdIn;
    }

    @Override
    public int getProductId() {
        return this.productId;
    }
    @Override
    public void setProductId(int productIdIn) {
        this.productId = productIdIn;
    }

    @Override
    public int getAmount() {
        return amount;
    }
    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void setAll(int ingredientIdIn,
                       int rawMatIdIn,
                       int productIdIn) {
        this.productIngredientId = ingredientIdIn;
        this.rawMatId = rawMatIdIn;
        this.productId = productIdIn;
    }


    @Override
    public boolean hasEqualMapping(ProductIngredient valueObject) {

        if (valueObject.getProductIngredientId() != this.productIngredientId) {
            return(false);
        }
        if (valueObject.getRawMatId() != this.rawMatId) {
            return(false);
        }
        if (valueObject.getProductId() != this.productId) {
            return(false);
        }

        return true;
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass ProductIngredient, mapping to table ProductIngredients\n");
        out.append("Persistent attributes: \n");
        out.append("productIngredientId = " + this.productIngredientId + "\n");
        out.append("rawMatId = " + this.rawMatId + "\n");
        out.append("productId = " + this.productId + "\n");
        return out.toString();
    }

    public Object clone() {
        ProductIngredient cloned = new ProductIngredient();

        cloned.setProductIngredientId(this.productIngredientId);
        cloned.setRawMatId(this.rawMatId);
        cloned.setProductId(this.productId);
        return cloned;
    }

}
