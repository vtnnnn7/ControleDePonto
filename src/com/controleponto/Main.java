
package com.controleponto;

import java.util.Scanner;
import com.controleponto.controller.FuncionarioController;
import com.controleponto.controller.RegistroPontoController;
import com.controleponto.reports.Relatorios;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        FuncionarioController funcionarios = new FuncionarioController();
        RegistroPontoController registros = new RegistroPontoController();
        Relatorios relatorios = new Relatorios();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- CONTROLE DE PONTO ---");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Atualizar funcionário");
            System.out.println("4 - Excluir funcionário");
            System.out.println("5 - Registrar ponto");
            System.out.println("6 - Listar pontos");
            System.out.println("7 - Relatório de funcionários");
            System.out.println("8 - Contar registros por tipo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    System.out.print("Cargo: ");
                    String cargo = sc.nextLine();

                    System.out.print("Salário: ");
                    double salario = Double.parseDouble(
                        sc.nextLine().replace(",", ".")
                    );

                    funcionarios.cadastrar(nome, cpf, cargo, salario);
                }

                case 2 -> funcionarios.listar();

                case 3 -> {
                    System.out.print("ID do funcionário: ");
                    int id = Integer.parseInt(sc.nextLine());

                    if (funcionarios.buscarPorId(id)) {
                        System.out.print("Novo nome: ");
                        String nome = sc.nextLine();

                        System.out.print("Novo CPF: ");
                        String cpf = sc.nextLine();

                        System.out.print("Novo cargo: ");
                        String cargo = sc.nextLine();

                        System.out.print("Novo salário: ");
                        double salario = Double.parseDouble(
                            sc.nextLine().replace(",", ".")
                        );

                        funcionarios.atualizar(
                            id, nome, cpf, cargo, salario
                        );
                    }
                }

                case 4 -> {
                    System.out.print("ID do funcionário: ");
                    int id = Integer.parseInt(sc.nextLine());

                    funcionarios.excluir(id);
                }

                case 5 -> {
                    System.out.print("ID do funcionário: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Tipo (Entrada ou Saída): ");
                    String tipo = sc.nextLine();

                    registros.registrar(id, tipo);
                }

                case 6 -> registros.listar();

                case 7 -> relatorios.listarFuncionarios();

                case 8 -> relatorios.contarRegistrosPorTipo();

                case 0 -> System.out.println("Programa encerrado.");

                default -> System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }
}
