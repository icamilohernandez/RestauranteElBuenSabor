
package com.mycompany.restauranteelbuensabor;


public class Utilidades {
    public static double calcular(double precio, double cantidad, double descuento, double tasaIva, double tasaPropina, int numeroItems, boolean aplicaPropina) {
        double resultado = 0;
        double ivaCalculado = 0;
        double resultadoFinal = 0;
        // calcula el resultado
        resultado = precio * cantidad;
        if (descuento > 0) {
            resultado = resultado - (resultado * descuento);
        }
        ivaCalculado = resultado * tasaIva;
        resultado = resultado + ivaCalculado;
        if (aplicaPropina) {
            resultado = resultado + (resultado * tasaPropina);
        }
        // imprime restaurante
        System.out.println("RESTAURANTE EL BUEN SABOR - calculo aplicado");
        resultadoFinal = resultado;
        return resultadoFinal;
    }

    public static boolean validar() {
        int contador = 0;
        int indice = 0;
        while (indice < Datos.cantidades.length) {
            if (Datos.cantidades[indice] > 0) {
                contador = contador + 1;
            }
            indice++;
        } // fin while
        // reinicia si no hay nada - efecto secundario no documentado
        if (contador == 0) {
            Datos.total = 0;
            Datos.textoTemporal = "";
        }
        return contador > 0;
    }

    public static void reiniciar() {
        // metodo antiguo de calculo - pendiente revisar
        // public static double calcOld(double precio, int cant){
        // double resultado = 0;
        // resultado = precio * cant;
        // resultado = resultado + (resultado * 0.19);
        // if(resultado > 50000){
        // resultado = resultado + (resultado * 0.10);}
        // System.out.println("RESTAURANTE EL BUEN SABOR");
        // System.out.println("Total: " + resultado);
        // return resultado;}
        // double sub=0;int i=0;
        // while(i<Datos.nom.length){
        // sub=sub+Datos.p[i]*Datos.cant[i];i++;}
        // if(sub>50000){ sub=sub+(sub*0.19); sub=sub+(sub*0.10); }
        // else{ sub=sub+(sub*0.19); }
        // Datos.tot=sub;
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