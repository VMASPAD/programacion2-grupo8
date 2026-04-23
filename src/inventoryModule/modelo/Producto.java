package inventoryModule.modelo;

/**
 * Representa un producto en el inventario.
 * Contiene código único, nombre, precio y cantidad en stock.
 */
public class Producto {
    private final String codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.cantidad = cantidad;
    }

    public double getValorTotal() {
        return precio * cantidad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return codigo.equals(producto.codigo);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - Precio: $%.2f | Stock: %d | Valor Total: $%.2f",
                codigo, nombre, precio, cantidad, getValorTotal());
    }
}
