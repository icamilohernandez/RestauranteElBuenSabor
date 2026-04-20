package com.mycompany.restauranteelbuensabor;

/**
 * Responsable de toda la salida en consola del sistema.
 * Esta clase solo imprime, no calcula ni modifica el estado del sistema.
 */
public class Imprimir {

    private static final String SEPARADOR_DOBLE  = "========================================";
    private static final String SEPARADOR_SIMPLE = "----------------------------------------";

    public static void mostrarCarta() {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    " + Datos.NOMBRE_RESTAURANTE);
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_DOBLE);
        int indice = 0;
        while (indice < Datos.CARTA.length) {
            System.out.printf("%d. %-22s $%,.0f%n", (indice + 1), Datos.CARTA[indice].getNombre(), Datos.CARTA[indice].getPrecio());
            indice++;
        }
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void mostrarPedido() {
        System.out.println("--- PEDIDO ACTUAL ---");
        int indice = 0;
        while (indice < Datos.pedidoActual.getItems().size()) {
            ItemPedido item = Datos.pedidoActual.getItems().get(indice);
            System.out.printf("%-20s x%-6d $%,.0f%n", item.getProducto().getNombre(), item.getCantidad(), item.calcularSubtotal());
            indice++;
        }
        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", Datos.pedidoActual.calcularSubtotal());
    }

    /**
     * Imprime el encabezado oficial del restaurante con nombre, direccion y NIT.
     * Se reutiliza en todos los formatos de factura para garantizar consistencia.
     */
    private static void imprimirEncabezado() {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    " + Datos.NOMBRE_RESTAURANTE);
        System.out.println("    " + Datos.DIRECCION);
        System.out.println("    NIT: " + Datos.NIT);
        System.out.println(SEPARADOR_DOBLE);
    }

    /**
     * Imprime el bloque de totales compartido entre factura completa y resumen.
     * Omite la linea de propina si esta es cero para no confundir al cliente.
     */
    private static void imprimirLineasTotales(Factura factura) {
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", factura.calcularDescuento());
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", factura.calcularIVA());
        if (factura.calcularPropina() > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", factura.calcularPropina());
        }
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", factura.calcularTotal());
        System.out.println(SEPARADOR_DOBLE);
    }

    /**
     * Imprime la factura completa con el detalle de cada item del pedido.
     * Ademas actualiza el contador de facturas y libera el estado de la mesa.
     */
    public static void imprimirFacturaCompleta() {
        Factura factura = new Factura(Datos.pedidoActual, Datos.numeroFactura);

        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", factura.getNumero());
        System.out.println(SEPARADOR_SIMPLE);
        int indice = 0;
        while (indice < Datos.pedidoActual.getItems().size()) {
            ItemPedido item = Datos.pedidoActual.getItems().get(indice);
            System.out.printf("%-20s x%-6d $%,.0f%n", item.getProducto().getNombre(), item.getCantidad(), item.calcularSubtotal());
            indice++;
        }
        imprimirLineasTotales(factura);
        System.out.println("Gracias por su visita!");
        System.out.println(Datos.NOMBRE_RESTAURANTE + " - Valledupar");
        System.out.println(SEPARADOR_DOBLE);
        // El numero de factura incrementa despues de imprimir para que el numero mostrado coincida con el registrado
        Datos.numeroFactura = Datos.numeroFactura + 1;
        Datos.estadoMesa = 0;
        Datos.total = factura.calcularTotal();
    }

    /**
     * Imprime una version resumida de la factura sin el detalle de items.
     * Util para el cliente que solo necesita ver el total a pagar.
     */
    public static void imprimirFacturaResumen() {
        Factura factura = new Factura(Datos.pedidoActual, Datos.numeroFactura);

        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", factura.getNumero());
        System.out.println(SEPARADOR_SIMPLE);
        imprimirLineasTotales(factura);
    }
}