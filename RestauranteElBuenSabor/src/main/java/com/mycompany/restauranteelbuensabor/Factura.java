package com.mycompany.restauranteelbuensabor;

public class Factura {
    private final Pedido pedido;
    private final int numero;

    public Factura(Pedido pedido, int numero) {
        this.pedido = pedido;
        this.numero = numero;
    }

    public int getNumero() { return numero; }

    public double calcularSubtotal() {
        return pedido.calcularSubtotal();
    }

    public double calcularDescuento() {
        if (pedido.contarItemsDiferentes() > Datos.MIN_ITEMS_DESCUENTO) {
            return calcularSubtotal() - (calcularSubtotal() * Datos.TASA_DESCUENTO);
        }
        return calcularSubtotal();
    }

    public double calcularIVA() {
        return calcularDescuento() * Datos.TASA_IVA;
    }

    public double calcularPropina() {
        if (calcularDescuento() > Datos.UMBRAL_PROPINA) {
            return (calcularDescuento() + calcularIVA()) * Datos.TASA_PROPINA;
        }
        return 0;
    }

    public double calcularTotal() {
        return calcularDescuento() + calcularIVA() + calcularPropina();
    }
}