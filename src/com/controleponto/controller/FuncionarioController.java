package com.controleponto.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.controleponto.model.Funcionario;
import com.controleponto.utils.Conexao;

public class FuncionarioController {

	public boolean cadastrar(Funcionario f) {
		String sql = "INSERT INTO funcionario (nome, cpf, cargo, salario) VALUES (?, ?, ?, ?)";

		try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, f.getNome());
			stmt.setString(2, f.getCpf());
			stmt.setString(3, f.getCargo());
			stmt.setDouble(4, f.getSalario());

			stmt.executeUpdate();
			System.out.println("Funcionario cadastrado!");
			return true;

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Ja existe um funcionario cadastrado com esse CPF.");
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar: " + e.getMessage());
		}
		return false;
	}

	/** Lista todos os funcionários e devolve quantos foram exibidos. */
	public int listar() {
		String sql = "SELECT id, nome, cpf, cargo, salario FROM funcionario ORDER BY id";
		int total = 0;

		try (Connection con = Conexao.conectar();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			System.out.printf("%-5s %-28s %-15s %-20s %s%n", "ID", "NOME", "CPF", "CARGO", "SALARIO");
			while (rs.next()) {
				System.out.printf("%-5d %-28s %-15s %-20s R$ %.2f%n", rs.getInt("id"), rs.getString("nome"),
						rs.getString("cpf"), rs.getString("cargo"), rs.getDouble("salario"));
				total++;
			}

			if (total == 0) {
				System.out.println("Nenhum funcionario cadastrado.");
			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar: " + e.getMessage());
		}
		return total;
	}

	/** Busca um funcionário pelo id. Retorna null se não existir (verificação de existência). */
	public Funcionario buscar(int id) {
		String sql = "SELECT id, nome, cpf, cargo, salario FROM funcionario WHERE id = ?";

		try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"),
							rs.getString("cargo"), rs.getDouble("salario"));
				}
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar funcionario: " + e.getMessage());
		}
		return null;
	}

	public void exibir(Funcionario f) {
		System.out.println("ID: " + f.getId());
		System.out.println("Nome: " + f.getNome());
		System.out.println("CPF: " + f.getCpf());
		System.out.println("Cargo: " + f.getCargo());
		System.out.printf("Salario: R$ %.2f%n", f.getSalario());
	}

	public boolean atualizar(Funcionario f) {
		String sql = "UPDATE funcionario SET nome = ?, cpf = ?, cargo = ?, salario = ? WHERE id = ?";

		try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, f.getNome());
			stmt.setString(2, f.getCpf());
			stmt.setString(3, f.getCargo());
			stmt.setDouble(4, f.getSalario());
			stmt.setInt(5, f.getId());

			return stmt.executeUpdate() > 0;

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Ja existe outro funcionario cadastrado com esse CPF.");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar: " + e.getMessage());
		}
		return false;
	}

	/** Quantidade de registros de ponto vinculados ao funcionário (filhos na FK). */
	public int contarPontos(int id) {
		String sql = "SELECT COUNT(*) FROM registro_ponto WHERE funcionario_id = ?";

		try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}

		} catch (SQLException e) {
			System.out.println("Erro ao verificar registros de ponto: " + e.getMessage());
		}
		return 0;
	}

	/** Exclui o funcionário. Só funciona se ele não tiver registros de ponto (FK RESTRICT). */
	public boolean excluir(int id) {
		String sql = "DELETE FROM funcionario WHERE id = ?";

		try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao excluir funcionario: " + e.getMessage());
		}
		return false;
	}

	/** Exclui os registros de ponto do funcionário e depois o próprio funcionário, em uma transação. */
	public boolean excluirComPontos(int id) {
		String sqlPontos = "DELETE FROM registro_ponto WHERE funcionario_id = ?";
		String sqlFuncionario = "DELETE FROM funcionario WHERE id = ?";

		try (Connection con = Conexao.conectar()) {
			con.setAutoCommit(false);

			try (PreparedStatement pontos = con.prepareStatement(sqlPontos);
					PreparedStatement func = con.prepareStatement(sqlFuncionario)) {

				pontos.setInt(1, id);
				pontos.executeUpdate();

				func.setInt(1, id);
				int linhas = func.executeUpdate();

				con.commit();
				return linhas > 0;

			} catch (SQLException e) {
				con.rollback();
				throw e;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao excluir funcionario e seus pontos: " + e.getMessage());
		}
		return false;
	}
}
