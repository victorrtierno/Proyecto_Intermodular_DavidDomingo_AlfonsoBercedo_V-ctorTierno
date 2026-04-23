package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vo.ZonaVo;

public class ZonaDao {

    public List<ZonaVo> obtenerZonasPorEvento(
            Connection conexion,
            int idEvento) {

        List<ZonaVo> zonas = new ArrayList<>();

        String consulta =
            "SELECT id, nombre, precioBase " +
            "FROM Zona " +
            "WHERE idEvento = ?";

        try (PreparedStatement stmt =
                conexion.prepareStatement(consulta)) {

            stmt.setInt(1, idEvento);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ZonaVo zona =
                    new ZonaVo(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precioBase")
                    );

                zonas.add(zona);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return zonas;
    }
}