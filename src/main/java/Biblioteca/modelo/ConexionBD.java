package Biblioteca.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Conexion a la base de datos gestion_biblioteca
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
