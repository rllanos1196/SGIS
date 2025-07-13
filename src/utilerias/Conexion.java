package utilerias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL ="jdbc:mysql://localhost:3306/seguridad?useSSL=true&serverTimezone=America/Lima";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "mysql";
    public static Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USUARIO,
                    PASSWORD);
            System.out.println("Conectado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return con;
    }

   // public static void main(String[] args) {
    //    Connection connection = conectar();
   // }
}
