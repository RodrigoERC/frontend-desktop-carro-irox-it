package com.iroxit.front.end.desktop.model;

public class Venta {

    private int idVentas;
    private int cantidadVendida;
    private String fecha;
    private Producto producto;

    public Venta() {
    }

    public Venta(int idVentas, int cantidadVendida, String fecha, Producto producto) {
        this.idVentas = idVentas;
        this.cantidadVendida = cantidadVendida;
        this.fecha = fecha;
        this.producto = producto;
    }

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
