package inventoryModule.model;

/**
 * Representa un producto en el inventario.
 * Contiene código único, nombre, precio y cantidad en stock.
 */
public class Product {
    private final String code;
    private String name;
    private double price;
    private int quantity;

    public Product(String code, String name, double price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return code.equals(product.code);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - Precio: $%.2f | Stock: %d | Valor Total: $%.2f",
                code, name, price, quantity, getTotalValue());
    }
}
