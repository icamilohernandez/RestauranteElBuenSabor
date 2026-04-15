/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author alfre
 */
public class Proceso {
    public static double calcularTotalFactura() {
        double subtotalBase = 0;
        double montoIVA = 0; 
        double totalVentaFinal = 0;
        double subtotalConDescuento = 0;
        int contadorItemsDiferentes = 0;
        int indice = 0;

        while (indice < Datos.nombresPlatos.length) {
            if (Datos.cantidadesPedidas[indice] > 0) {
                
                subtotalBase = subtotalBase + Datos.preciosPlatos[indice] * Datos.cantidadesPedidas[indice];
                contadorItemsDiferentes = contadorItemsDiferentes + 1;
            }
            indice++;
        }

        if (contadorItemsDiferentes > 3) {
            if (subtotalBase > 0) {
                subtotalConDescuento = subtotalBase - (subtotalBase * 0.05);
                if (subtotalConDescuento > 50000) {
                    montoIVA = subtotalConDescuento * 0.19;
                    totalVentaFinal = subtotalConDescuento + montoIVA;
                    totalVentaFinal = totalVentaFinal + (totalVentaFinal * 0.10);
                } else {
                    montoIVA = subtotalConDescuento * 0.19;
                    totalVentaFinal = subtotalConDescuento + montoIVA;
                }
            }
        } else {
            if (subtotalBase > 50000) {
                montoIVA = subtotalBase * 0.19;
                totalVentaFinal = subtotalBase + montoIVA;
                totalVentaFinal = totalVentaFinal + (totalVentaFinal * 0.10);
            } else {
                montoIVA = subtotalBase * 0.19;
                totalVentaFinal = subtotalBase + montoIVA;
            }
        } // fin if-else cont
        Datos.estadoMesa = 1; 
        Datos.totalVenta = totalVentaFinal;
        
        return totalVentaFinal;
    }

    public static double calcularDesgloseManual(double precioUnitario, double cantidad, 
                                               double porcentajeDescuento, double porcentajeIVA, 
                                               double porcentajePropina, int totalItems, 
                                               boolean aplicaPropina) { 
        
        double resultadoCalculo = 0;
        double montoIVA = 0;
        double montoPropina = 0;
        double valorTemporalCalculo = 0;

        resultadoCalculo = precioUnitario * cantidad;

        if (porcentajeDescuento > 0) {
            resultadoCalculo = resultadoCalculo - (resultadoCalculo * porcentajeDescuento);
        }

        montoIVA = resultadoCalculo * porcentajeIVA;
        valorTemporalCalculo = montoIVA;
        resultadoCalculo = resultadoCalculo + valorTemporalCalculo;

        if (aplicaPropina) {
            montoPropina = resultadoCalculo * porcentajePropina;
            resultadoCalculo = resultadoCalculo + montoPropina;
        }
        
        if (totalItems > 3) {
            resultadoCalculo = resultadoCalculo - (resultadoCalculo * 0.01);
        }

        return resultadoCalculo;
    }
}
