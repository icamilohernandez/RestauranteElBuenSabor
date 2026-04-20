package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {
    public static void main(String[] args) {
        Scanner lectorTeclado = new Scanner(System.in);
        int opcionSeleccionada = 0;
        boolean sistemaActivo = true;

        System.out.println("========================================");
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println("========================================");

        while (sistemaActivo) {
            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");
            
            opcionSeleccionada = lectorTeclado.nextInt();
            
            if (opcionSeleccionada == 1) {

                Imprimir.mostrarCarta(); 
                System.out.println();

            } else if (opcionSeleccionada == 2) {
                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print("Numero de producto (1-" + Datos.nombresPlatos.length + "): ");
                int numeroProductoSeleccionado = lectorTeclado.nextInt();
                
                System.out.print("Cantidad: ");
                int cantidadIngresada = lectorTeclado.nextInt();

                if (numeroProductoSeleccionado > 0 && numeroProductoSeleccionado <= Datos.nombresPlatos.length) {
                    if (cantidadIngresada > 0) {
                        if (Datos.estadoMesa == 0) {
                            System.out.print("Ingrese numero de mesa: ");
                            Datos.numeroMesaActual = lectorTeclado.nextInt();
                            Datos.estadoMesa = 1;
                        }
                        
                        Datos.cantidadesPedidas[numeroProductoSeleccionado - 1] += cantidadIngresada;
                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + Datos.nombresPlatos[numeroProductoSeleccionado - 1] + " x" + cantidadIngresada);
                        
                    } else {
                        if (cantidadIngresada == 0) {
                            System.out.println("La cantidad no puede ser cero.");
                        } else {
                            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
                        }
                    }
                } else {
                    if (numeroProductoSeleccionado <= 0) {
                        System.out.println("El numero debe ser mayor a cero.");
                    } else {
                        System.out.println("Producto no existe. La carta tiene " + Datos.nombresPlatos.length + " productos.");
                    }
                }
                System.out.println();

            } else if (opcionSeleccionada == 3) {
                System.out.println();
                if (Utilidades.validar()) {
                    Imprimir.mostrarPedido();
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                }
                System.out.println();

            } else if (opcionSeleccionada == 4) {
                System.out.println();
                if (Utilidades.validar()) {

                    Proceso.calcularTotalFactura();
                    Imprimir.imprimirFacturaCompleta();
                    System.out.println();
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                }

            } else if (opcionSeleccionada == 5) {
                System.out.println();
                Utilidades.reiniciar();
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();

            } else if (opcionSeleccionada == 0) {
                sistemaActivo = false;
                System.out.println("Hasta luego!");

            } else {
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
            }
        }
        lectorTeclado.close();
    }
}