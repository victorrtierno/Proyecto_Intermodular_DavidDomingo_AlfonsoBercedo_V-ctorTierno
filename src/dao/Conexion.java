package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConnection() throws Exception{
        try {
            String url = "jdbc:mysql://localhost:3306/GestionEventos";
            String user = "root";
            String pwd = "mysql";

            return DriverManager.getConnection(url, user, pwd);
            
        } catch (Exception e) {
            throw (e);
        }
      
        
    }

}
