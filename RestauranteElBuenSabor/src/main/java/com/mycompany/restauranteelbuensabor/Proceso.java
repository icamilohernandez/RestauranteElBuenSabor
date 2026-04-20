
package com.mycompany.restauranteelbuensabor;


public class Proceso {
    public static double hacerTodo() {
        double subtotal = 0;
        double iva = 0;
        double total = 0;
        double subtotalConDescuento = 0;
        int contador = 0;
        int indice = 0;
        while (indice < Datos.nombres.length) {
            if (Datos.cantidades[indice] > 0) {
                // multiplica precio por cantidad
                subtotal = subtotal + Datos.precios[indice] * Datos.cantidades[indice];
                contador = contador + 1;
            }
            indice++;
        } // fin while
        if (contador > 3) {
            if (subtotal > 0) {
                subtotalConDescuento = subtotal - (subtotal * 0.05);
                if (subtotalConDescuento > 50000) {
                    iva = subtotalConDescuento * 0.19;
                    // suma iva al subtotal con descuento
                    total = subtotalConDescuento + iva;
                    total = total + (total * 0.10);
                } else {
                    // suma iva al subtotal
                    iva = subtotalConDescuento * 0.19;
                    total = subtotalConDescuento + iva;
                }
            } // fin if subtotal>0
            // version anterior - no borrar
            // sub = sub * 1.19;
            // if(sub > 40000) sub = sub + (sub*0.10);
            // return sub;
        } else {
            if (subtotal > 50000) {
                iva = subtotal * 0.19;
                // suma iva al subtotal
                total = subtotal + iva;
                total = total + (total * 0.10);
            } else {
                iva = subtotal * 0.19;
                total = subtotal + iva;
            }
        } // fin if-else contador
        Datos.estadoMesa = 1;
        Datos.total = total;
        return total;
    }

    public static double procesar(double precio, double cantidad, double descuento, double tasaIva, double tasaPropina, int numeroItems, boolean aplicaPropina) {
        double resultado = 0;
        double iva = 0;
        double propina = 0;
        double ivaCalculado = 0;
        // calcula subtotal con cantidad
        resultado = precio * cantidad;
        if (descuento > 0) {
            // aplica descuento
            resultado = resultado - (resultado * descuento);
        }
        // calcula iva
        iva = resultado * tasaIva;
        ivaCalculado = iva;
        resultado = resultado + ivaCalculado;
        if (aplicaPropina) {
            // aplica propina si corresponde
            propina = resultado * tasaPropina;
            resultado = resultado + propina;
        }
        if (numeroItems > 3) {
            resultado = resultado - (resultado * 0.01);
        }
        return resultado;
    }
}