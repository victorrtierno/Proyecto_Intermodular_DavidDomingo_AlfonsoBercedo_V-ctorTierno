package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.ClienteVo;

public class ClienteDao {

    public int obtenerIdClientePorEmail(
            Connection conexion,
            String email) {

        String consulta =
        "SELECT id FROM Cliente WHERE email = ?";

        try (PreparedStatement stmt =
                conexion.prepareStatement(consulta)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return rs.getInt("id");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return -1;
    }
}