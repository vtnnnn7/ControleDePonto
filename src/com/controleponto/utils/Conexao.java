
package com.controleponto.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL =
        "jdbc:mysql://localhost:3306/controle_ponto";

    private static final String USUARIO = "Vtn";

    
    private static final String SENHA = "Vitolas1212@_";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
