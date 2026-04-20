package com.mycompany.restauranteelbuensabor;

/**
 * Coordina el proceso de calculo del total de la factura
 * y actualiza el estado global del sistema al finalizar.
 */
public class Proceso {

    public static double calcularSubtotal() {
        return Datos.pedidoActual.calcularSubtotal();
    }

    public static int contarItemsPedido() {
        return Datos.pedidoActual.contarItemsDiferentes();
    }

    public static double aplicarDescuento(double subtotal) {
        if (contarItemsPedido() > Datos.MIN_ITEMS_DESCUENTO) {
            return subtotal - (subtotal * Datos.TASA_DESCUENTO);
        }
        return subtotal;
    }

    public static double calcularIVA(double base) {
        return base * Datos.TASA_IVA;
    }

    public static double calcularPropina(double base) {
        if (base > Datos.UMBRAL_PROPINA) {
            return base * Datos.TASA_PROPINA;
        }
        return 0;
    }

    /**
     * Genera la factura del pedido actual, actualiza el estado de la mesa
     * y persiste el total en Datos para consulta posterior.
     */
    public static double calcularTotal() {
        Factura factura = new Factura(Datos.pedidoActual, Datos.numeroFactura);
        double total = factura.calcularTotal();
        Datos.estadoMesa = 1;
        Datos.total = total;
        return total;
    }
}