
package com.controleponto.model;

import java.time.LocalDateTime;

public class RegistroPonto {

    private int idRegistro;
    private int idFuncionario;
    private LocalDateTime dataHora;
    private String tipo;

    public RegistroPonto() {
    }

    public RegistroPonto(int idRegistro, int idFuncionario,
                         LocalDateTime dataHora, String tipo) {
        this.idRegistro = idRegistro;
        this.idFuncionario = idFuncionario;
        this.dataHora = dataHora;
        this.tipo = tipo;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
