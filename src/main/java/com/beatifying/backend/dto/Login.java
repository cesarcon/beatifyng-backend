package com.beatifying.backend.dto;

public class Login {
    private String numeroDocumento;
    private String password;

    public Login() {
    }

    public Login(String numeroDocumento, String password) {
        this.numeroDocumento = numeroDocumento;
        this.password = password;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
