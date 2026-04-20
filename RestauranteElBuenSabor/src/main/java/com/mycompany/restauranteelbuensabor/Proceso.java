package com.mycompany.restauranteelbuensabor;

public class Proceso {

    private static final double PORCENTAJE_IVA = 0.19;
    private static final double PORCENTAJE_PROPINA = 0.10;
    private static final double PORCENTAJE_DESCUENTO = 0.05;
    private static final double LIMITE_PROPINA_IVA = 50000.0;

    public static double calcularTotalFactura() {
        double subtotalBase = 0;
        double montoIVA = 0; 
        double totalVentaFinal = 0;
        double subtotalConDescuento = 0;
        int contadorItemsDiferentes = 0;
        int indice = 0;

        // 1. Calcular subtotal y contar productos diferentes
        while (indice < Datos.nombresPlatos.length) {
            if (Datos.cantidadesPedidas[indice] > 0) {
                subtotalBase += Datos.preciosPlatos[indice] * Datos.cantidadesPedidas[indice];
                contadorItemsDiferentes++;
            }
            indice++;
        }

        if (contadorItemsDiferentes > 3) {
            subtotalConDescuento = subtotalBase - (subtotalBase * PORCENTAJE_DESCUENTO);
        } else {
            subtotalConDescuento = subtotalBase;
        }

        montoIVA = subtotalConDescuento * PORCENTAJE_IVA;
        totalVentaFinal = subtotalConDescuento + montoIVA;

        if (subtotalConDescuento > LIMITE_PROPINA_IVA) {
            totalVentaFinal += (totalVentaFinal * PORCENTAJE_PROPINA);
        }

        Datos.estadoMesa = 1; 
        Datos.totalVenta = totalVentaFinal;
        
        return totalVentaFinal;
    }

    public static double calcularDesgloseManual(double precioUnitario, double cantidad, 
                                               double porcentajeDescuento, double porcentajeIVA, 
                                               double porcentajePropina, int totalItems, 
                                               boolean aplicaPropina) { 
        
        double resultadoCalculo = precioUnitario * cantidad;

        if (porcentajeDescuento > 0) {
            resultadoCalculo -= (resultadoCalculo * porcentajeDescuento);
        }

        resultadoCalculo += (resultadoCalculo * porcentajeIVA);

        if (aplicaPropina) {
            resultadoCalculo += (resultadoCalculo * porcentajePropina);
        }
        
        if (totalItems > 3) {
            resultadoCalculo -= (resultadoCalculo * 0.01);
        }

        return resultadoCalculo;
    }
}