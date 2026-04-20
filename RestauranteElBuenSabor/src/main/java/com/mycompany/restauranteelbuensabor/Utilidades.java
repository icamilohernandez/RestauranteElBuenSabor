package com.mycompany.restauranteelbuensabor;

/**
 * Metodos de apoyo para validar el estado del pedido y reiniciar la mesa.
 */
public class Utilidades {

    /**
     * Verifica si el pedido actual tiene al menos un producto agregado.
     * Usar antes de generar factura o mostrar el pedido.
     */
    public static boolean validar() {
        return Datos.pedidoActual.tieneProductos();
    }

    /**
     * Reinicia completamente el estado de la mesa actual.
     * Llama a este metodo al iniciar la atencion de un nuevo cliente.
     */
    public static void reiniciar() {
        Datos.pedidoActual.limpiar();
        Datos.total = 0;
        Datos.estadoMesa = 0;
        Datos.numeroMesaActual = 0;
    }
}