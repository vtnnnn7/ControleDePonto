
package com.controleponto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.controleponto.controller.FuncionarioController;
import com.controleponto.controller.RegistroPontoController;
import com.controleponto.model.Funcionario;
import com.controleponto.model.RegistroPonto;
import com.controleponto.reports.Relatorios;

public class Main {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        FuncionarioController funcionarios = new FuncionarioController();
        RegistroPontoController pontos = new RegistroPontoController();
        Relatorios relatorios = new Relatorios();

        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE CONTROLE DE PONTO ===");
            System.out.println("Equipe: [coloque os nomes do grupo]");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Registrar ponto");
            System.out.println("3 - Listar funcionários");
            System.out.println("4 - Atualizar funcionário");
            System.out.println("5 - Excluir funcionário");
            System.out.println("6 - Listar registros de ponto");
            System.out.println("7 - Relatório de registros por tipo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            while (!entrada.hasNextInt()) {
                System.out.print("Digite um número válido: ");
                entrada.nextLine();
            }

            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = entrada.nextLine();

                    System.out.print("Cargo: ");
                    String cargo = entrada.nextLine();

                    System.out.print("Matrícula: ");
                    String matricula = entrada.nextLine();

                    funcionarios.cadastrar(nome, cargo, matricula);
                    break;

                case 2:
                    System.out.print("ID do funcionário: ");
                    int idFuncionario = entrada.nextInt();
                    entrada.nextLine();

                    Funcionario funcionario = funcionarios.buscarPorId(idFuncionario);

                    if (funcionario == null) {
                        System.out.println("Funcionário não encontrado.");
                        break;
                    }

                    System.out.println("1 - ENTRADA");
                    System.out.println("2 - SAIDA_INTERVALO");
                    System.out.println("3 - RETORNO_INTERVALO");
                    System.out.println("4 - SAIDA_FINAL");
                    System.out.print("Tipo do ponto: ");

                    int tipo = entrada.nextInt();
                    entrada.nextLine();

                    String[] tipos = {
                        "ENTRADA", "SAIDA_INTERVALO",
                        "RETORNO_INTERVALO", "SAIDA_FINAL"
                    };

                    if (tipo >= 1 && tipo <= 4) {
                        pontos.registrar(idFuncionario, tipos[tipo - 1]);
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                    break;

                case 3:
                    funcionarios.listar();
                    break;

                case 4:
                    System.out.print("ID do funcionário: ");
                    int idAtualizar = entrada.nextInt();
                    entrada.nextLine();

                    System.out.print("Novo nome: ");
                    String novoNome = entrada.nextLine();

                    System.out.print("Novo cargo: ");
                    String novoCargo = entrada.nextLine();

                    System.out.print("Nova matrícula: ");
                    String novaMatricula = entrada.nextLine();

                    if (funcionarios.atualizar(
                            idAtualizar, novoNome, novoCargo, novaMatricula)) {
                        System.out.println("Funcionário atualizado!");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("ID do funcionário: ");
                    int idExcluir = entrada.nextInt();
                    entrada.nextLine();

                    boolean possuiPonto = false;

                    for (RegistroPonto registro : pontos.getRegistros()) {
                        if (registro.getIdFuncionario() == idExcluir) {
                            possuiPonto = true;
                            break;
                        }
                    }

                    if (possuiPonto) {
                        System.out.println(
                            "Não é possível excluir: existem pontos registrados."
                        );
                    } else if (funcionarios.excluir(idExcluir)) {
                        System.out.println("Funcionário excluído!");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;

                case 6:
                    pontos.listar();
                    break;

                case 7:
                    List<RegistroPonto> registros = pontos.getRegistros();
                    relatorios.contarRegistrosPorTipo(registros);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        entrada.close();
    }
}
