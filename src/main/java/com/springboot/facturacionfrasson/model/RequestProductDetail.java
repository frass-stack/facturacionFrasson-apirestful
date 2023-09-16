package com.springboot.facturacionfrasson.model;

public class RequestProductDetail {
    private int productoId;
    private int quantity;

    public RequestProductDetail(int productoId, int quantity) {
        this.productoId = productoId;
        this.quantity = quantity;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "productoId=" + productoId +
                ", quantity=" + quantity;
    }
}
