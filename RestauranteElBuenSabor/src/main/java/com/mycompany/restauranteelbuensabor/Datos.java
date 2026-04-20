package com.mycompany.restauranteelbuensabor;

public class Datos {
    public static final double TASA_IVA            = 0.19;
    public static final double TASA_PROPINA        = 0.10;
    public static final double TASA_DESCUENTO      = 0.05;
    public static final double UMBRAL_PROPINA      = 50000;
    public static final int    MIN_ITEMS_DESCUENTO = 3;
    public static final String NOMBRE_RESTAURANTE  = "El Buen Sabor";
    public static final String DIRECCION           = "Calle 15 #8-32, Valledupar";
    public static final String NIT                 = "900.123.456-7";

    public static final Producto[] CARTA = {
        new Producto("Bandeja Paisa",       32000),
        new Producto("Sancocho de Gallina", 28000),
        new Producto("Arepa con Huevo",      8000),
        new Producto("Jugo Natural",         7000),
        new Producto("Gaseosa",              4500),
        new Producto("Cerveza Poker",        6000),
        new Producto("Agua Panela",          3500),
        new Producto("Arroz con Pollo",     25000)
    };

    public static int    numeroMesaActual = 0;
    public static int    estadoMesa       = 0;
    public static double total            = 0;
    public static int    numeroFactura    = 1;
    public static Pedido pedidoActual     = new Pedido();
}