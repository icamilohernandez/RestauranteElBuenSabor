package com.mycompany.restauranteelbuensabor;

public class Utilidades {

    public static boolean validar() {
        int contador = 0;
        int indice = 0;
        while (indice < Datos.cantidades.length) {
            if (Datos.cantidades[indice] > 0) {
                contador = contador + 1;
            }
            indice++;
        }
        return contador > 0;
    }

    public static void reiniciar() {
        int indice = 0;
        while (indice < Datos.cantidades.length) {
            Datos.cantidades[indice] = 0;
            indice++;
        }
        Datos.total = 0;
        Datos.estadoMesa = 0;
        Datos.numeroMesaActual = 0;
        Datos.textoTemporal = "";
    }
}