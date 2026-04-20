package com.mycompany.restauranteelbuensabor;

public class Utilidades {

    public static boolean validar() {
        int productosEncontrados = 0;
        int indice = 0;
        
        while (indice < Datos.cantidadesPedidas.length) {
            if (Datos.cantidadesPedidas[indice] > 0) {
                productosEncontrados++;
            }
            indice++;
        }
        
        if (productosEncontrados == 0) {
            Datos.totalVenta = 0;
            Datos.registroTemporal = "";
        }
        
        return productosEncontrados > 0;
    }

    public static void reiniciar() {
        int indice = 0;
        
        while (indice < Datos.cantidadesPedidas.length) {
            Datos.cantidadesPedidas[indice] = 0;
            indice++;
        }
        
        Datos.totalVenta = 0;
        Datos.estadoMesa = 0;
        Datos.numeroMesaActual = 0;
        Datos.registroTemporal = "";
    }

    public static double calcularDesglose(double precioUnitario, double cantidad, 
                                          double porcentajeDescuento, double porcentajeIVA, 
                                          double porcentajePropina, boolean aplicarPropina) {
        
        double montoCalculado = precioUnitario * cantidad;

        if (porcentajeDescuento > 0) {
            montoCalculado -= (montoCalculado * porcentajeDescuento);
        }

        montoCalculado += (montoCalculado * porcentajeIVA);

        if (aplicarPropina) {
            montoCalculado += (montoCalculado * porcentajePropina);
        }
        
        return montoCalculado; 
    }
}