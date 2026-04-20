package com.mycompany.restauranteelbuensabor;

/**
 * Representa un producto disponible en la carta del restaurante.
 * El precio esta expresado en pesos colombianos sin decimales.
 */
public class Producto {
    private final String nombre;
    private final double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
}