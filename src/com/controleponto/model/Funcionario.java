package com.controleponto.model;

public class Funcionario {

    private int idFuncionario;
    private String nome;
    private String cargo;
    private String matricula;

    public Funcionario() {
    }

    public Funcionario(int idFuncionario, String nome, String cargo, String matricula) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cargo = cargo;
        this.matricula = matricula;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}