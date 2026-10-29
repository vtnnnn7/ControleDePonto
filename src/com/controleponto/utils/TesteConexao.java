package com.controleponto.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {

    public static void main(String[] args) {
        try (Connection conexao = Conexao.conectar()) {
            System.out.println("Conexão com MySQL realizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com MySQL:");
            e.printStackTrace();
        }
    }
}