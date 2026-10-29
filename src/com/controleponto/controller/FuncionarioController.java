
package com.controleponto.controller;

import java.util.ArrayList;
import java.util.List;

import com.controleponto.model.Funcionario;

public class FuncionarioController {

    private List<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;

    public void cadastrar(String nome, String cargo, String matricula) {
        Funcionario funcionario = new Funcionario(
            proximoId, nome, cargo, matricula
        );

        funcionarios.add(funcionario);
        proximoId++;

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    public void listar() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        for (Funcionario funcionario : funcionarios) {
            System.out.println(
                "ID: " + funcionario.getIdFuncionario()
                + " | Nome: " + funcionario.getNome()
                + " | Cargo: " + funcionario.getCargo()
                + " | Matrícula: " + funcionario.getMatricula()
            );
        }
    }

    public Funcionario buscarPorId(int id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getIdFuncionario() == id) {
                return funcionario;
            }
        }

        return null;
    }

    public boolean atualizar(int id, String nome, String cargo, String matricula) {
        Funcionario funcionario = buscarPorId(id);

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return false;
        }

        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setMatricula(matricula);

        System.out.println("Funcionário atualizado com sucesso!");
        return true;
    }

    public boolean excluir(int id) {
        Funcionario funcionario = buscarPorId(id);

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return false;
        }

        funcionarios.remove(funcionario);

        System.out.println("Funcionário excluído com sucesso!");
        return true;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}
