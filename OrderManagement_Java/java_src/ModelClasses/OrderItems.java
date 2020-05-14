package ModelClasses;

/**
 * connection / relation beetwen Order and its Items
 */
public class OrderItems {
    private int orderId;
    private int productId;
    private int quantity;
    private float price;

    public OrderItems() {
    }

    public OrderItems(int orderId, int productId, int quantity,float price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }



    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
