package models;

public class Product {

    private int productId;
    private String productName;
    private RawMatAmount[] ingredients;

    public Product() {
    }

    public Product(int productIdIn) {
        this.productId = productIdIn;
    }

    public int getProductId() {
        return this.productId;
    }
    public void setProductId(int productIdIn) {
        this.productId = productIdIn;
    }

    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String productNameIn) {
        this.productName = productNameIn;
    }

    public RawMatAmount[] getIngredients() {
        return ingredients;
    }
    public void setIngredients(RawMatAmount[] ingredients) {
        this.ingredients = ingredients;
    }

    public void setAll(int productIdIn,
                       String productNameIn) {
        this.productId = productIdIn;
        this.productName = productNameIn;
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass Product, mapping to table Product\n");
        out.append("Persistent attributes: \n");
        out.append("productId = " + this.productId + "\n");
        out.append("productName = " + this.productName + "\n");
        return out.toString();
    }

    public static class RawMatAmount {
        private int prodIngId;
        private int rawMatId;
        private String name;
        private double amount;
        private double tolerance;

        public int getProdIngId() {
            return prodIngId;
        }
        public void setProdIngId(int prodIngId) {
            this.prodIngId = prodIngId;
        }
        public int getRawMatId() {
            return rawMatId;
        }
        public void setRawMatId(int rawMatId) {
            this.rawMatId = rawMatId;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public double getAmount() {
            return amount;
        }
        public void setAmount(double amount) {
            this.amount = amount;
        }
        public double getTolerance() {
            return tolerance;
        }
        public void setTolerance(double tolerance) {
            this.tolerance = tolerance;
        }
    }
}