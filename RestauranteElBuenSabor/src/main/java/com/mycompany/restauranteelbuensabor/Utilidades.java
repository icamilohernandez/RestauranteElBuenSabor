package com.mycompany.restauranteelbuensabor;

public class Utilidades {

    public static boolean validar() {
        return Datos.pedidoActual.tieneProductos();
    }

    public static void reiniciar() {
        Datos.pedidoActual.limpiar();
        Datos.total = 0;
        Datos.estadoMesa = 0;
        Datos.numeroMesaActual = 0;
    }
}