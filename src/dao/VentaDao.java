package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class VentaDao {

    public int crearVenta(
            Connection conexion,
            int idCliente,
            double total) {

        String insert =
        "INSERT INTO Venta (idCliente, precioTotal) VALUES (?, ?)";

        try (PreparedStatement stmt =
                conexion.prepareStatement(
                        insert,
                        Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, idCliente);
            stmt.setDouble(2, total);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return -1;
    }

}