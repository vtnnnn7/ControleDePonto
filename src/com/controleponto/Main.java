package com.controleponto;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controleponto.controller.FuncionarioController;
import com.controleponto.controller.RegistroPontoController;
import com.controleponto.model.Funcionario;
import com.controleponto.model.RegistroPonto;
import com.controleponto.reports.Relatorios;
import com.controleponto.utils.Conexao;
import com.controleponto.utils.Entrada;

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

	private static final FuncionarioController funcionarios = new FuncionarioController();
	private static final RegistroPontoController pontos = new RegistroPontoController();
	private static final Relatorios relatorios = new Relatorios();
	private static Entrada entrada;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		entrada = new Entrada(sc);

		telaInicial();

		int opcao = -1;

		while (opcao != 0) {

			System.out.println();
			System.out.println("========== MENU PRINCIPAL ==========");
			System.out.println("1 - Consultar registros");
			System.out.println("2 - Inserir registros");
			System.out.println("3 - Remover registros");
			System.out.println("4 - Atualizar registros");
			System.out.println("5 - Relatorios");
			System.out.println("0 - Sair");
			opcao = entrada.lerInt("Escolha uma opcao: ");

			switch (opcao) {
			case 1 -> consultar();
			case 2 -> inserir();
			case 3 -> remover();
			case 4 -> atualizar();
			case 5 -> menuRelatorios();
			case 0 -> System.out.println("Programa encerrado!");
			default -> System.out.println("Opcao invalida!");
			}
		}

		sc.close();
	}

	// ------------------------------------------------------------------
	// Menus auxiliares
	// ------------------------------------------------------------------

	/** Submenu de entidades: 1 = funcionario, 2 = registro de ponto, 0 = voltar. */
	private static int menuEntidade(String titulo) {
		while (true) {
			System.out.println();
			System.out.println("---------- " + titulo + " ----------");
			System.out.println("1 - Funcionarios");
			System.out.println("2 - Registros de ponto");
			System.out.println("0 - Voltar ao menu principal");
			int opcao = entrada.lerInt("Escolha a entidade: ");

			if (opcao >= 0 && opcao <= 2) {
				return opcao;
			}
			System.out.println("Opcao invalida!");
		}
	}

	private static void menuRelatorios() {
		int opcao = -1;

		while (opcao != 0) {
			System.out.println();
			System.out.println("---------- RELATORIOS ----------");
			System.out.println("1 - Relatorio de funcionarios e pontos (JOIN)");
			System.out.println("2 - Contar registros por tipo (GROUP BY)");
			System.out.println("0 - Voltar ao menu principal");
			opcao = entrada.lerInt("Escolha uma opcao: ");

			switch (opcao) {
			case 1 -> relatorios.listarFuncionarios();
			case 2 -> relatorios.contarRegistrosPorTipo();
			case 0 -> {
			}
			default -> System.out.println("Opcao invalida!");
			}
		}
	}

	/** Escolhe o tipo do ponto. Retorna null se o usuário cancelar (apenas quando permitido). */
	private static String escolherTipo(String atual) {
		while (true) {
			System.out.println("Tipo de ponto:");
			System.out.println("1 - ENTRADA");
			System.out.println("2 - SAIDA");

			if (atual == null) {
				int opcao = entrada.lerInt("Escolha o tipo: ");
				if (opcao == 1) {
					return "ENTRADA";
				}
				if (opcao == 2) {
					return "SAIDA";
				}
			} else {
				int opcao = entrada.lerIntOuManter("Escolha o tipo", atual.equals("SAIDA") ? 2 : 1);
				if (opcao == 1) {
					return "ENTRADA";
				}
				if (opcao == 2) {
					return "SAIDA";
				}
			}
			System.out.println("Opcao invalida!");
		}
	}

	// ------------------------------------------------------------------
	// 1 - Consultar
	// ------------------------------------------------------------------

	private static void consultar() {
		int entidade;

		do {
			entidade = menuEntidade("CONSULTAR");
			System.out.println();

			if (entidade == 1) {
				funcionarios.listar();
			} else if (entidade == 2) {
				pontos.listar();
			}
		} while (entidade != 0);
	}

	// ------------------------------------------------------------------
	// 2 - Inserir
	// ------------------------------------------------------------------

	private static void inserir() {
		boolean continuar = true;

		while (continuar) {
			int entidade = menuEntidade("INSERIR");

			if (entidade == 0) {
				return;
			}

			if (entidade == 1) {
				inserirFuncionario();
			} else {
				inserirPonto();
			}

			continuar = entrada.confirmar("Deseja inserir mais algum registro?");
		}
	}

	private static void inserirFuncionario() {
		System.out.println("\n--- Cadastro de funcionario ---");

		Funcionario f = new Funcionario();
		f.setNome(entrada.lerTexto("Nome: "));
		f.setCpf(entrada.lerTexto("CPF: "));
		f.setCargo(entrada.lerTexto("Cargo: "));
		f.setSalario(entrada.lerDouble("Salario: "));

		funcionarios.cadastrar(f);
	}

	private static void inserirPonto() {
		System.out.println("\n--- Registro de ponto ---");

		if (funcionarios.listar() == 0) {
			System.out.println("Cadastre um funcionario antes de registrar ponto.");
			return;
		}

		int idFuncionario = entrada.lerInt("ID do funcionario: ");
		Funcionario f = funcionarios.buscar(idFuncionario);

		if (f == null) {
			System.out.println("Funcionario nao encontrado.");
			return;
		}

		String tipo = escolherTipo(null);

		LocalDateTime dataHora = entrada.lerDataHoraOuAgora("Data/hora (dd/MM/aaaa HH:mm) ou Enter para usar agora: ");

		pontos.registrar(f.getId(), dataHora, tipo);
	}

	// ------------------------------------------------------------------
	// 3 - Remover
	// ------------------------------------------------------------------

	private static void remover() {
		boolean continuar = true;

		while (continuar) {
			int entidade = menuEntidade("REMOVER");

			if (entidade == 0) {
				return;
			}

			if (entidade == 1) {
				removerFuncionario();
			} else {
				removerPonto();
			}

			continuar = entrada.confirmar("Deseja excluir mais algum registro?");
		}
	}

	private static void removerFuncionario() {
		System.out.println("\n--- Remover funcionario ---");

		if (funcionarios.listar() == 0) {
			return;
		}

		int id = entrada.lerInt("ID do funcionario para excluir: ");
		Funcionario f = funcionarios.buscar(id);

		if (f == null) {
			System.out.println("Funcionario nao encontrado.");
			return;
		}

		if (!entrada.confirmar("Tem certeza que deseja excluir " + f.getNome() + "?")) {
			System.out.println("Exclusao cancelada!");
			return;
		}

		int qtdPontos = funcionarios.contarPontos(id);

		if (qtdPontos == 0) {
			if (funcionarios.excluir(id)) {
				System.out.println("Funcionario excluido com sucesso!");
			}
			return;
		}

		// O funcionário é chave estrangeira em registro_ponto.
		System.out.println("Esse funcionario possui " + qtdPontos + " registro(s) de ponto vinculado(s).");

		if (entrada.confirmar("Deseja excluir tambem esses registros de ponto e o funcionario?")) {
			if (funcionarios.excluirComPontos(id)) {
				System.out.println("Funcionario e registros de ponto excluidos com sucesso!");
			}
		} else {
			System.out.println("Exclusao cancelada: o funcionario nao pode ser removido enquanto tiver pontos.");
		}
	}

	private static void removerPonto() {
		System.out.println("\n--- Remover registro de ponto ---");

		if (pontos.listar() == 0) {
			return;
		}

		int id = entrada.lerInt("ID do registro de ponto para excluir: ");
		RegistroPonto r = pontos.buscar(id);

		if (r == null) {
			System.out.println("Registro de ponto nao encontrado.");
			return;
		}

		if (!entrada.confirmar("Tem certeza que deseja excluir o registro " + id + "?")) {
			System.out.println("Exclusao cancelada!");
			return;
		}

		if (pontos.excluir(id)) {
			System.out.println("Registro de ponto excluido com sucesso!");
		}
	}

	// ------------------------------------------------------------------
	// 4 - Atualizar
	// ------------------------------------------------------------------

	private static void atualizar() {
		boolean continuar = true;

		while (continuar) {
			int entidade = menuEntidade("ATUALIZAR");

			if (entidade == 0) {
				return;
			}

			if (entidade == 1) {
				atualizarFuncionario();
			} else {
				atualizarPonto();
			}

			continuar = entrada.confirmar("Deseja atualizar mais algum registro?");
		}
	}

	private static void atualizarFuncionario() {
		System.out.println("\n--- Atualizar funcionario ---");

		if (funcionarios.listar() == 0) {
			return;
		}

		int id = entrada.lerInt("ID do funcionario que deseja atualizar: ");
		Funcionario f = funcionarios.buscar(id);

		if (f == null) {
			System.out.println("Funcionario nao encontrado.");
			return;
		}

		System.out.println("Pressione Enter para manter o valor atual.");
		f.setNome(entrada.lerTextoOuManter("Nome", f.getNome()));
		f.setCpf(entrada.lerTextoOuManter("CPF", f.getCpf()));
		f.setCargo(entrada.lerTextoOuManter("Cargo", f.getCargo()));
		f.setSalario(entrada.lerDoubleOuManter("Salario", f.getSalario()));

		if (funcionarios.atualizar(f)) {
			System.out.println("\nFuncionario atualizado! Dados atuais:");
			funcionarios.exibir(funcionarios.buscar(id));
		}
	}

	private static void atualizarPonto() {
		System.out.println("\n--- Atualizar registro de ponto ---");

		if (pontos.listar() == 0) {
			return;
		}

		int id = entrada.lerInt("ID do registro de ponto que deseja atualizar: ");
		RegistroPonto r = pontos.buscar(id);

		if (r == null) {
			System.out.println("Registro de ponto nao encontrado.");
			return;
		}

		System.out.println("Pressione Enter para manter o valor atual.");

		int idFuncionario = entrada.lerIntOuManter("ID do funcionario", r.getFuncionario().getId());
		Funcionario f = funcionarios.buscar(idFuncionario);

		if (f == null) {
			System.out.println("Funcionario nao encontrado. Atualizacao cancelada.");
			return;
		}

		r.setFuncionario(f);
		r.setDataHora(entrada.lerDataHoraOuManter("Data/hora (dd/MM/aaaa HH:mm)", r.getDataHora()));
		r.setTipo(escolherTipo(r.getTipo()));

		if (pontos.atualizar(r)) {
			System.out.println("\nRegistro de ponto atualizado! Dados atuais:");
			pontos.exibir(pontos.buscar(id));
		}
	}
}
