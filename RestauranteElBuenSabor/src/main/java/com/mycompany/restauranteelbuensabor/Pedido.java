package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el pedido activo de una mesa.
 * Agregar el mismo producto dos veces acumula la cantidad en lugar de crear un item duplicado.
 */
public class Pedido {
    private final List<ItemPedido> items = new ArrayList<>();

    public void agregarItem(Producto producto, int cantidad) {
        int indice = 0;
        while (indice < items.size()) {
            if (items.get(indice).getProducto().getNombre().equals(producto.getNombre())) {
                // Si el producto ya existe en el pedido, se acumula la cantidad
                items.get(indice).agregarCantidad(cantidad);
                return;
            }
            indice++;
        }
        items.add(new ItemPedido(producto, cantidad));
    }

    /**
     * Calcula la suma de todos los items sin aplicar descuentos ni impuestos.
     */
    public double calcularSubtotal() {
        double subtotal = 0;
        int indice = 0;
        while (indice < items.size()) {
            subtotal = subtotal + items.get(indice).calcularSubtotal();
            indice++;
        }
        return subtotal;
    }

    public int contarItemsDiferentes() {
        return items.size();
    }

    public boolean tieneProductos() {
        return items.size() > 0;
    }

    public void limpiar() {
        items.clear();
    }

    public List<ItemPedido> getItems() { return items; }
}