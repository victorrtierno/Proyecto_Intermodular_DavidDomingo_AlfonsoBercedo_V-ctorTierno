package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EntradaDao {

    public boolean crearEntrada(
            Connection conexion,
            int idVenta,
            int idAsiento,
            double precio) {

        String insert =
        "INSERT INTO Entrada (idVenta, idAsiento, precio) " +
        "VALUES (?, ?, ?)";

        try (PreparedStatement stmt =
                conexion.prepareStatement(insert)) {

            stmt.setInt(1, idVenta);
            stmt.setInt(2, idAsiento);
            stmt.setDouble(3, precio);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
    }

}