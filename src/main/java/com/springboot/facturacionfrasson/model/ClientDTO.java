package com.springboot.facturacionfrasson.model;

public class ClientDTO {
    private int client_id;

    private String name;

    private String lastname;

    private String docnumber;

    public ClientDTO(int client_id, String name, String lastname, String docnumber) {
        this.client_id = client_id;
        this.name = name;
        this.lastname = lastname;
        this.docnumber = docnumber;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDocnumber() {
        return docnumber;
    }

    public void setDocnumber(String docnumber) {
        this.docnumber = docnumber;
    }

    @Override
    public String toString() {
        return "client_id=" + client_id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", docnumber='" + docnumber;
    }
}
