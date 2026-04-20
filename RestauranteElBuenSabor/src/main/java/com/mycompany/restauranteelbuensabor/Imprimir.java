package com.mycompany.restauranteelbuensabor;

public class Imprimir {

    private static final String SEPARADOR = "========================================";
    private static final String LINEA_SIMPLE = "----------------------------------------";

    public static void mostrarCarta() {
        System.out.println(SEPARADOR);
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR);

        int indice = 0;
        while (indice < Datos.nombresPlatos.length) {
            System.out.printf("%d. %-22s $%,.0f%n", 
                (indice + 1), 
                Datos.nombresPlatos[indice], 
                Datos.preciosPlatos[indice]);
            indice++;
        }
        System.out.println(SEPARADOR);
    }

    public static void mostrarPedido() {
        double subtotalAcumulado = 0;
        int indice = 0;
        
        System.out.println("--- PEDIDO ACTUAL ---");
        while (indice < Datos.nombresPlatos.length) {
            if (Datos.cantidadesPedidas[indice] > 0) {
                double costoPorProducto = Datos.preciosPlatos[indice] * Datos.cantidadesPedidas[indice];
                System.out.printf("%-20s x%-6d $%,.0f%n", 
                    Datos.nombresPlatos[indice], 
                    Datos.cantidadesPedidas[indice], 
                    costoPorProducto);
                
                subtotalAcumulado += costoPorProducto;
            }
            indice++;
        }
        System.out.println(LINEA_SIMPLE);
        System.out.printf("%-27s $%,.0f%n", "Subtotal parcial:", subtotalAcumulado);
    }

    public static void imprimirFacturaCompleta() {
        double totalFinal = Proceso.calcularTotalFactura();

        System.out.println(SEPARADOR);
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println(SEPARADOR);

        System.out.printf("FACTURA No. %03d%n", Datos.numeroFactura);
        System.out.println(LINEA_SIMPLE);

        int indiceItems = 0;
        while (indiceItems < Datos.nombresPlatos.length) {
            if (Datos.cantidadesPedidas[indiceItems] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n", 
                    Datos.nombresPlatos[indiceItems], 
                    Datos.cantidadesPedidas[indiceItems], 
                    (Datos.preciosPlatos[indiceItems] * Datos.cantidadesPedidas[indiceItems]));
            }
            indiceItems++;
        }
        
        System.out.println(LINEA_SIMPLE);

        System.out.printf("%-27s $%,.0f%n", "TOTAL A PAGAR:", totalFinal);
        System.out.println(SEPARADOR);
        System.out.println("Gracias por su visita!");
        System.out.println("El Buen Sabor - Valledupar");
        System.out.println(SEPARADOR);

        Datos.numeroFactura++; 
        Datos.estadoMesa = 0;
        Datos.totalVenta = totalFinal;
    }
}