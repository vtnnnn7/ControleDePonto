
package com.controleponto.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controleponto.utils.Conexao;

public class FuncionarioController {

    public void cadastrar(String nome, String cpf,
                          String cargo, double salario) {
        String sql = "INSERT INTO funcionario " + "(nome, cpf, cargo, salario) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, cargo);
            stmt.setDouble(4, salario);

            stmt.executeUpdate();
            System.out.println("Funcionário cadastrado!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public void listar() {
        String sql = "SELECT * FROM funcionario";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " - "
                    + rs.getString("nome") + " - "
                    + rs.getString("cpf") + " - "
                    + rs.getString("cargo") + " - R$ "
                    + rs.getDouble("salario")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

    public boolean buscarPorId(int id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome"));
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("Funcionário não encontrado.");
        return false;
    }

    public void atualizar(int id, String nome, String cpf,
                          String cargo, double salario) {
        String sql = "UPDATE funcionario SET nome=?, cpf=?, "
                   + "cargo=?, salario=? WHERE id=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, cargo);
            stmt.setDouble(4, salario);
            stmt.setInt(5, id);

            stmt.executeUpdate();
            System.out.println("Funcionário atualizado!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM funcionario WHERE id=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Funcionário excluído!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }
}
