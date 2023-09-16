package com.springboot.facturacionfrasson.model;

import java.util.List;

public class RequestInvoice {

    private int client_id;
    private List<RequestProductDetail> product_list;

    public RequestInvoice(int client_id, List<RequestProductDetail> product_list) {
        this.client_id = client_id;
        this.product_list = product_list;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public List<RequestProductDetail> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(List<RequestProductDetail> product_list) {
        this.product_list = product_list;
    }

    @Override
    public String toString() {
        return "client_id=" + client_id +
                ", product_list=" + product_list;
    }
}
