package com.mycompany.restauranteelbuensabor;

public class Proceso {

    public static double calcularSubtotal() {
        double subtotal = 0;
        int indice = 0;
        while (indice < Datos.nombres.length) {
            if (Datos.cantidades[indice] > 0) {
                subtotal = subtotal + Datos.precios[indice] * Datos.cantidades[indice];
            }
            indice++;
        }
        return subtotal;
    }

    public static int contarItemsPedido() {
        int contador = 0;
        int indice = 0;
        while (indice < Datos.cantidades.length) {
            if (Datos.cantidades[indice] > 0) {
                contador = contador + 1;
            }
            indice++;
        }
        return contador;
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

    public static double calcularTotal() {
        double subtotal              = calcularSubtotal();
        double subtotalConDescuento  = aplicarDescuento(subtotal);
        double iva                   = calcularIVA(subtotalConDescuento);
        double totalConIva           = subtotalConDescuento + iva;
        double propina               = calcularPropina(subtotalConDescuento);
        double total                 = totalConIva + propina;
        Datos.estadoMesa = 1;
        Datos.total = total;
        return total;
    }

    public static double procesar(double precio, double cantidad, double descuento, double tasaIva, double tasaPropina, int numeroItems, boolean aplicaPropina) {
        double resultado = precio * cantidad;
        if (descuento > 0) {
            resultado = resultado - (resultado * descuento);
        }
        double iva = resultado * tasaIva;
        resultado = resultado + iva;
        if (aplicaPropina) {
            double propina = resultado * tasaPropina;
            resultado = resultado + propina;
        }
        if (numeroItems > Datos.MIN_ITEMS_DESCUENTO) {
            resultado = resultado - (resultado * 0.01);
        }
        return resultado;
    }
}