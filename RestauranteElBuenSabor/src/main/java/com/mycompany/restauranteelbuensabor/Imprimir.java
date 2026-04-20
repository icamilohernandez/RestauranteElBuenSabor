package com.mycompany.restauranteelbuensabor;

public class Imprimir {

    private static final String SEPARADOR_DOBLE  = "========================================";
    private static final String SEPARADOR_SIMPLE = "----------------------------------------";

    public static void mostrarCarta() {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    " + Datos.NOMBRE_RESTAURANTE);
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_DOBLE);
        int indice = 0;
        while (indice < Datos.nombres.length) {
            System.out.printf("%d. %-22s $%,.0f%n", (indice + 1), Datos.nombres[indice], Datos.precios[indice]);
            indice++;
        }
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void mostrarPedido() {
        double subtotal = 0;
        int indice = 0;
        System.out.println("--- PEDIDO ACTUAL ---");
        while (indice < Datos.nombres.length) {
            if (Datos.cantidades[indice] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n", Datos.nombres[indice], Datos.cantidades[indice], (Datos.precios[indice] * Datos.cantidades[indice]));
                subtotal = subtotal + Datos.precios[indice] * Datos.cantidades[indice];
            }
            indice++;
        }
        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    private static void imprimirEncabezado() {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    " + Datos.NOMBRE_RESTAURANTE);
        System.out.println("    " + Datos.DIRECCION);
        System.out.println("    NIT: " + Datos.NIT);
        System.out.println(SEPARADOR_DOBLE);
    }

    private static double[] calcularTotalesFactura() {
        double subtotal = 0;
        int contador = 0;
        int indice = 0;
        while (indice < Datos.nombres.length) {
            if (Datos.cantidades[indice] > 0) {
                subtotal = subtotal + Datos.precios[indice] * Datos.cantidades[indice];
                contador = contador + 1;
            }
            indice++;
        }
        double subtotalConDescuento = 0;
        if (contador > Datos.MIN_ITEMS_DESCUENTO) {
            subtotalConDescuento = subtotal - (subtotal * Datos.TASA_DESCUENTO);
        } else {
            subtotalConDescuento = subtotal;
        }
        double iva = subtotalConDescuento * Datos.TASA_IVA;
        double total = subtotalConDescuento + iva;
        double propina = 0;
        if (subtotalConDescuento > Datos.UMBRAL_PROPINA) {
            propina = total * Datos.TASA_PROPINA;
            total = total + propina;
        }
        return new double[]{subtotalConDescuento, iva, propina, total};
    }

    private static void imprimirLineasTotales(double subtotalConDescuento, double iva, double propina, double total) {
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", iva);
        if (propina > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", propina);
        }
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", total);
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void imprimirFacturaCompleta() {
        double[] totales = calcularTotalesFactura();
        double subtotalConDescuento = totales[0];
        double iva                  = totales[1];
        double propina              = totales[2];
        double total                = totales[3];

        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", Datos.numeroFactura);
        System.out.println(SEPARADOR_SIMPLE);
        int indicePedido = 0;
        while (indicePedido < Datos.nombres.length) {
            if (Datos.cantidades[indicePedido] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n", Datos.nombres[indicePedido], Datos.cantidades[indicePedido], (Datos.precios[indicePedido] * Datos.cantidades[indicePedido]));
            }
            indicePedido++;
        }
        imprimirLineasTotales(subtotalConDescuento, iva, propina, total);
        System.out.println("Gracias por su visita!");
        System.out.println(Datos.NOMBRE_RESTAURANTE + " - Valledupar");
        System.out.println(SEPARADOR_DOBLE);
        Datos.numeroFactura = Datos.numeroFactura + 1;
        Datos.estadoMesa = 0;
        Datos.total = total;
    }

    public static void imprimirFacturaResumen() {
        double[] totales = calcularTotalesFactura();
        double subtotalConDescuento = totales[0];
        double iva                  = totales[1];
        double propina              = totales[2];
        double total                = totales[3];

        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", Datos.numeroFactura);
        System.out.println(SEPARADOR_SIMPLE);
        imprimirLineasTotales(subtotalConDescuento, iva, propina, total);
    }
}