package com.mycompany.restauranteelbuensabor;

/**
 * Encapsula toda la logica de calculo de una factura.
 * La impresion es responsabilidad de Imprimir, no de esta clase.
 */
public class Factura {
    private final Pedido pedido;
    private final int numero;

    public Factura(Pedido pedido, int numero) {
        this.pedido = pedido;
        this.numero = numero;
    }

    public int getNumero() { return numero; }

    /**
     * Retorna la suma bruta del pedido sin descuentos ni impuestos.
     */
    public double calcularSubtotal() {
        return pedido.calcularSubtotal();
    }

    /**
     * Aplica descuento del 5% si el pedido tiene mas de MIN_ITEMS_DESCUENTO productos diferentes.
     * Si no aplica descuento, retorna el subtotal sin modificar.
     */
    public double calcularDescuento() {
        if (pedido.contarItemsDiferentes() > Datos.MIN_ITEMS_DESCUENTO) {
            return calcularSubtotal() - (calcularSubtotal() * Datos.TASA_DESCUENTO);
        }
        return calcularSubtotal();
    }

    /**
     * El IVA se calcula sobre el subtotal ya descontado, segun DIAN 2024.
     */
    public double calcularIVA() {
        return calcularDescuento() * Datos.TASA_IVA;
    }

    /**
     * La propina aplica sobre el total con IVA incluido, no sobre el subtotal.
     * Solo se cobra si el subtotal con descuento supera UMBRAL_PROPINA.
     */
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