
package com.controleponto.reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.controleponto.utils.Conexao;

public class Relatorios {

    public void listarFuncionarios() {
        String sql = "SELECT funcionario.nome, registro_ponto.tipo, "
                   + "registro_ponto.data_hora "
                   + "FROM funcionario "
                   + "INNER JOIN registro_ponto "
                   + "ON funcionario.id = registro_ponto.funcionario_id";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Funcionário: " + rs.getString("nome"));
                System.out.println("Tipo: " + rs.getString("tipo"));
                System.out.println("Data: " + rs.getTimestamp("data_hora"));
                System.out.println("--------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void contarRegistrosPorTipo() {
        String sql = "SELECT tipo, COUNT(*) AS total "
                   + "FROM registro_ponto GROUP BY tipo";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getString("tipo") + ": "
                                   + rs.getInt("total"));
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
