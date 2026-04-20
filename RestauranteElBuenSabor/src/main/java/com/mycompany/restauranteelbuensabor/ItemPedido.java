package com.mycompany.restauranteelbuensabor;

/**
 * Representa un producto seleccionado por el cliente con su cantidad.
 * Es la unidad minima de un pedido.
 */
public class ItemPedido {
    private final Producto producto;
    private int cantidad;

    public ItemPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    public void agregarCantidad(int cantidadExtra) {
        this.cantidad = this.cantidad + cantidadExtra;
    }

    /**
     * Calcula el valor total de este item multiplicando precio por cantidad.
     */
    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}