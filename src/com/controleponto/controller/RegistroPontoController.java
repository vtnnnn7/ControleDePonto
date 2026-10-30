package com.controleponto.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.controleponto.model.Funcionario;
import com.controleponto.model.RegistroPonto;
import com.controleponto.utils.Conexao;
import com.controleponto.utils.Entrada;

public class RegistroPontoController {

	public boolean registrar(int idFuncionario, LocalDateTime dataHora, String tipo) {
		String sql = "INSERT INTO registro_ponto (funcionario_id, data_hora, tipo) VALUES (?, ?, ?)";

		try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, idFuncionario);
			stmt.setTimestamp(2, Timestamp.valueOf(dataHora));
			stmt.setString(3, tipo);

			stmt.executeUpdate();
			System.out.println("Ponto registrado!");
			return true;

		} catch (SQLException e) {
			System.out.println("Erro ao registrar: " + e.getMessage());
		}
		return false;
	}

	/** Lista os registros com o nome do funcionário e devolve quantos foram exibidos. */
	public int listar() {
		String sql = "SELECT rp.id, rp.funcionario_id, f.nome, rp.data_hora, rp.tipo "
				+ "FROM registro_ponto rp INNER JOIN funcionario f ON f.id = rp.funcionario_id "
				+ "ORDER BY rp.id";
		int total = 0;

		try (Connection con = Conexao.conectar();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			System.out.printf("%-5s %-6s %-28s %-17s %s%n", "ID", "FUNC.", "NOME", "DATA/HORA", "TIPO");
			while (rs.next()) {
				System.out.printf("%-5d %-6d %-28s %-17s %s%n", rs.getInt("id"), rs.getInt("funcionario_id"),
						rs.getString("nome"),
						rs.getTimestamp("data_hora").toLocalDateTime().format(Entrada.FORMATO_DATA_HORA),
						rs.getString("tipo"));
				total++;
			}

			if (total == 0) {
				System.out.println("Nenhum registro de ponto cadastrado.");
			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar: " + e.getMessage());
		}
		return total;
	}

	/** Busca um registro pelo id (já com o funcionário associado). Retorna null se não existir. */
	public RegistroPonto buscar(int id) {
		String sql = "SELECT rp.id, rp.data_hora, rp.tipo, f.id AS func_id, f.nome, f.cpf, f.cargo, f.salario "
				+ "FROM registro_ponto rp INNER JOIN funcionario f ON f.id = rp.funcionario_id "
				+ "WHERE rp.id = ?";

		try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Funcionario f = new Funcionario(rs.getInt("func_id"), rs.getString("nome"), rs.getString("cpf"),
							rs.getString("cargo"), rs.getDouble("salario"));

					return new RegistroPonto(rs.getInt("id"), f, rs.getTimestamp("data_hora").toLocalDateTime(),
							rs.getString("tipo"));
				}
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar registro de ponto: " + e.getMessage());
		}
		return null;
	}

	public void exibir(RegistroPonto r) {
		System.out.println("ID do registro: " + r.getId());
		System.out.println("Funcionario: " + r.getFuncionario().getId() + " - " + r.getFuncionario().getNome());
		System.out.println("Data/hora: " + r.getDataHora().format(Entrada.FORMATO_DATA_HORA));
		System.out.println("Tipo: " + r.getTipo());
	}

	public boolean atualizar(RegistroPonto r) {
		String sql = "UPDATE registro_ponto SET funcionario_id = ?, data_hora = ?, tipo = ? WHERE id = ?";

		try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, r.getFuncionario().getId());
			stmt.setTimestamp(2, Timestamp.valueOf(r.getDataHora()));
			stmt.setString(3, r.getTipo());
			stmt.setInt(4, r.getId());

			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar registro de ponto: " + e.getMessage());
		}
		return false;
	}

	public boolean excluir(int id) {
		String sql = "DELETE FROM registro_ponto WHERE id = ?";

		try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao excluir registro de ponto: " + e.getMessage());
		}
		return false;
	}
}
