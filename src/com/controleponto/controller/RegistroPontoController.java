
package com.controleponto.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.controleponto.model.RegistroPonto;

public class RegistroPontoController {

    private List<RegistroPonto> registros = new ArrayList<>();
    private int proximoId = 1;

    public void registrar(int idFuncionario, String tipo) {
        RegistroPonto registro = new RegistroPonto(
            proximoId,
            idFuncionario,
            LocalDateTime.now(),
            tipo
        );

        registros.add(registro);
        proximoId++;

        System.out.println("Ponto registrado com sucesso!");
    }

    public void listar() {
        if (registros.isEmpty()) {
            System.out.println("Nenhum registro de ponto.");
            return;
        }

        for (RegistroPonto registro : registros) {
            System.out.println(
                "Registro: " + registro.getIdRegistro()
                + " | Funcionário: " + registro.getIdFuncionario()
                + " | Data e hora: " + registro.getDataHora()
                + " | Tipo: " + registro.getTipo()
            );
        }
    }

    public List<RegistroPonto> getRegistros() {
        return registros;
    }
}
