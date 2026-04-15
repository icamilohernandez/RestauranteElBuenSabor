/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author alfre
 */
public class Imprimir {

    public static void mostrarCarta() {
        System.out.println("========================================");
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println("========================================");

        int indice = 0;
        while (indice < Datos.nombresPlatos.length) {
            System.out.printf("%d. %-22s $%,.0f%n", 
                (indice + 1), 
                Datos.nombresPlatos[indice], 
                Datos.preciosPlatos[indice]);
            indice++;
        }
        System.out.println("========================================");
    }

    public static void mostrarPedido() {
        double subtotal = 0;
        int indice = 0;
        
        System.out.println("--- PEDIDO ACTUAL ---");
        while (indice < Datos.nombresPlatos.length) {
            if (Datos.cantidadesPedidas[indice] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n", 
                    Datos.nombresPlatos[indice], 
                    Datos.cantidadesPedidas[indice], 
                    (Datos.preciosPlatos[indice] * Datos.cantidadesPedidas[indice]));
                
                subtotal = subtotal + Datos.preciosPlatos[indice] * Datos.cantidadesPedidas[indice];
            }
            indice++;
        }
        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    public static void imprimirFacturaCompleta() {
        double subtotalBase = 0;
        double montoIva = 0; 
        double totalFactura = 0;
        double montoPropina = 0;
        int contadorProductosDiferentes = 0;
        double subtotalConDescuento = 0; 
        
        int indice = 0;
        while (indice < Datos.nombresPlatos.length) {
            if (Datos.cantidadesPedidas[indice] > 0) {
                subtotalBase = subtotalBase + Datos.preciosPlatos[indice] * Datos.cantidadesPedidas[indice];
                contadorProductosDiferentes = contadorProductosDiferentes + 1;
            }
            indice++;
        } // fin while
        if (contadorProductosDiferentes > 3) {
            subtotalConDescuento = subtotalBase - (subtotalBase * 0.05);
        } else {
            subtotalConDescuento = subtotalBase;
        }
        
        if (subtotalConDescuento > 50000) {
            montoIva = subtotalConDescuento * 0.19;
            totalFactura = subtotalConDescuento + montoIva;
            montoPropina = totalFactura * 0.10;
            totalFactura = totalFactura + montoPropina;
        } else {
            montoIva = subtotalConDescuento * 0.19;
            totalFactura = subtotalConDescuento + montoIva;
            montoPropina = 0;
        } // fin if-else

        String separadorVisual = "========================================";
        System.out.println(separadorVisual);
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println(separadorVisual);

        System.out.printf("FACTURA No. %03d%n", Datos.numeroFactura);
        System.out.println("----------------------------------------");
        // imprime cada item del pedido

        int indiceItems = 0; // Antes: j
        while (indiceItems < Datos.nombresPlatos.length) {
            if (Datos.cantidadesPedidas[indiceItems] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n", 
                    Datos.nombresPlatos[indiceItems], 
                    Datos.cantidadesPedidas[indiceItems], 
                    (Datos.preciosPlatos[indiceItems] * Datos.cantidadesPedidas[indiceItems]));
            }
            indiceItems++;
        } // fin while
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", montoIva);
        
        if (montoPropina > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", montoPropina); 
        } // fin if prop
        
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", totalFactura);
        System.out.println(separadorVisual);
        System.out.println("Gracias por su visita!");
        System.out.println("El Buen Sabor - Valledupar");
        System.out.println(separadorVisual);

        // actualiza estado e incrementa factura - tres responsabilidades en un metodo
        Datos.numeroFactura = Datos.numeroFactura + 1; 
        Datos.estadoMesa = 0;
        Datos.totalVenta = totalFactura;
    }

    public static void imprimirFacturaResumen() {
        double subtotalBase = 0;
        double montoIva = 0;
        double totalFactura = 0;
        double montoPropina = 0;
        int contadorProductosDiferentes = 0;
        double subtotalConDescuento = 0;
        // calcula subtotal otra vez igual que en imprimirFacturaCompleta
        int indice = 0;
        while (indice < Datos.nombresPlatos.length) {
            if (Datos.cantidadesPedidas[indice] > 0) {
                subtotalBase = subtotalBase + Datos.preciosPlatos[indice] * Datos.cantidadesPedidas[indice];
                contadorProductosDiferentes = contadorProductosDiferentes + 1;
            }
            indice++;
        } // fin while
        if (contadorProductosDiferentes > 3) {
            subtotalConDescuento = subtotalBase - (subtotalBase * 0.05);
        } else {
            subtotalConDescuento = subtotalBase;
        }
        
        if (subtotalConDescuento > 50000) {
            montoIva = subtotalConDescuento * 0.19;
            totalFactura = subtotalConDescuento + montoIva;
            montoPropina = totalFactura * 0.10;
            totalFactura = totalFactura + montoPropina;
        } else {
            montoIva = subtotalConDescuento * 0.19;
            totalFactura = subtotalConDescuento + montoIva;
            montoPropina = 0;
        } // fin if-else
        String separadorVisual = "========================================";
        System.out.println(separadorVisual);
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println(separadorVisual);
        
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", Datos.numeroFactura);
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", montoIva);
        
        if (montoPropina > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", montoPropina);
        }
        
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", totalFactura);
        System.out.println(separadorVisual);
    }
}
