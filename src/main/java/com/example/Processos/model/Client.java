package com.example.Processos.model;

public class Client {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String int_pro;


    public Client() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInt_pro() {
        return int_pro;
    }

    public void setInt_pro(String int_pro) {
        this.int_pro = int_pro;
    }
}
