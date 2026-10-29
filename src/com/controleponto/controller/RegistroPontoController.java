
package com.controleponto.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.controleponto.utils.Conexao;

public class RegistroPontoController {

    public void registrar(int idFuncionario, String tipo) {
        String sql = "INSERT INTO registro_ponto "
                   + "(funcionario_id, data_hora, tipo) VALUES (?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);
            stmt.setObject(2, LocalDateTime.now());
            stmt.setString(3, tipo);

            stmt.executeUpdate();
            System.out.println("Ponto registrado!");

        } catch (SQLException e) {
            System.out.println("Erro ao registrar: " + e.getMessage());
        }
    }

    public void listar() {
        String sql = "SELECT * FROM registro_ponto";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                    "Registro: " + rs.getInt("id")
                    + " | Funcionário: " + rs.getInt("funcionario_id")
                    + " | Data: " + rs.getTimestamp("data_hora")
                    + " | Tipo: " + rs.getString("tipo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }
}
