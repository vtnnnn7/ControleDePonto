package com.controleponto.model;

import java.time.LocalDateTime;

public class RegistroPonto {

	private int id;
	private Funcionario funcionario;
	private LocalDateTime dataHora;
	private String tipo;

	public RegistroPonto() {
	}

	public RegistroPonto(int id, Funcionario funcionario, LocalDateTime dataHora, String tipo) {
		this.id = id;
		this.funcionario = funcionario;
		this.dataHora = dataHora;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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

	@Override
	public String toString() {
		return "RegistroPonto{" + "id=" + id + ", funcionario=" + funcionario + ", dataHora=" + dataHora + ", tipo='"
				+ tipo + '\'' + '}';
	}
}