package com.controleponto;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controleponto.controller.FuncionarioController;
import com.controleponto.controller.RegistroPontoController;
import com.controleponto.reports.Relatorios;
import com.controleponto.utils.Conexao;

public class Main {

	public static int contarRegistros(String tabela) {
		String sql = "SELECT COUNT(*) FROM " + tabela;

		try (Connection con = Conexao.conectar();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao contar registros: " + e.getMessage());
		}

		return 0;
	}

	public static void telaInicial() {
		System.out.println("========================================");
		System.out.println("          CONTROLE DE PONTO");
		System.out.println("========================================");
		System.out.println("Disciplina: Banco de Dados");
		System.out.println("Professor: Howard Roatti");
		System.out.println("Semestre: 2026/2");
		System.out.println();

		System.out.println("Integrantes:");
		System.out.println("- Tiago Campos");
		System.out.println("- Victor Bussular");
		System.out.println("- Ingrid Junger");
		System.out.println();

		System.out.println("Quantidade de funcionarios: " + contarRegistros("funcionario"));

		System.out.println("Quantidade de registros de ponto: " + contarRegistros("registro_ponto"));

		System.out.println("========================================");
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		FuncionarioController funcionarioController = new FuncionarioController();

		RegistroPontoController registroController = new RegistroPontoController();

		Relatorios relatorios = new Relatorios();

		telaInicial();

		int opcao = -1;

		while (opcao != 0) {

			System.out.println();
			System.out.println("========== MENU PRINCIPAL ==========");
			System.out.println("1 - Cadastrar funcionario");
			System.out.println("2 - Listar funcionarios");
			System.out.println("3 - Atualizar funcionario");
			System.out.println("4 - Excluir funcionario");
			System.out.println("5 - Registrar ponto");
			System.out.println("6 - Listar pontos");
			System.out.println("7 - Relatorio de funcionarios (JOIN)");
			System.out.println("8 - Contar registros por tipo (GROUP BY)");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opcao: ");

			try {
				opcao = Integer.parseInt(sc.nextLine());

				switch (opcao) {

				case 1 -> {
				    String continuar = "s";

				    while (continuar.equalsIgnoreCase("s")) {

				        System.out.println("\n--- Cadastro de funcionario ---");

				        System.out.print("Nome: ");
				        String nome = sc.nextLine();

				        System.out.print("CPF: ");
				        String cpf = sc.nextLine();

				        System.out.print("Cargo: ");
				        String cargo = sc.nextLine();

				        System.out.print("Salario: ");
				        double salario = Double.parseDouble(
				                sc.nextLine().replace(",", "."));

				        funcionarioController.cadastrar(
				                nome, cpf, cargo, salario);

				        System.out.print("Deseja cadastrar outro funcionario? (s/n): ");
				        continuar = sc.nextLine();
				    }
				}

				case 2 -> funcionarioController.listar();

				case 3 -> {
					System.out.print("ID do funcionario: ");
					int id = Integer.parseInt(sc.nextLine());

					if (funcionarioController.buscarPorId(id)) {

						System.out.print("Novo nome: ");
						String nome = sc.nextLine();

						System.out.print("Novo CPF: ");
						String cpf = sc.nextLine();

						System.out.print("Novo cargo: ");
						String cargo = sc.nextLine();

						System.out.print("Novo salario: ");
						double salario = Double.parseDouble(sc.nextLine().replace(",", "."));

						funcionarioController.atualizar(id, nome, cpf, cargo, salario);

					}
				}

				case 4 -> {
					System.out.print("ID do funcionario para excluir: ");
					int id = Integer.parseInt(sc.nextLine());

					funcionarioController.excluir(id);
				}

				case 5 -> {
				    String continuar = "s";

				    while (continuar.equalsIgnoreCase("s")) {

				        System.out.println("\n--- Registro de ponto ---");

				        System.out.print("ID do funcionario: ");
				        int id = Integer.parseInt(sc.nextLine());

				        System.out.print("Tipo de ponto: ");
				        String tipo = sc.nextLine();

				        registroController.registrar(id, tipo);

				        System.out.print("Deseja registrar outro ponto? (s/n): ");
				        continuar = sc.nextLine();
				    }
				}

				case 6 -> registroController.listar();

				case 7 -> relatorios.listarFuncionarios();

				case 8 -> relatorios.contarRegistrosPorTipo();

				case 0 -> System.out.println("Programa encerrado!");

				default -> System.out.println("Opcao invalida!");
				}

			} catch (NumberFormatException e) {
				System.out.println("Digite um numero valido!");
			}
		}

		sc.close();
	}
}