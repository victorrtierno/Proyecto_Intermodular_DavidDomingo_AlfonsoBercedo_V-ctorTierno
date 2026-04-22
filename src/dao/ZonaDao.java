package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import vo.ZonaVo;

public class ZonaDao {

    public List<ZonaVo>
        obtenerZonasPorEvento(
                Connection conexion,
                int idEvento
        ) {

        List<ZonaVo> lista =
                new ArrayList<>();

        String sql =
            "SELECT id, nombre, precioBase " +
            "FROM Zona " +
            "WHERE idEvento = "
            + idEvento;

        try (

            Statement stmt =
                    conexion.createStatement();

            ResultSet rs =
                    stmt.executeQuery(sql)

        ) {

            while (rs.next()) {

                ZonaVo z =
                        new ZonaVo(

                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precioBase")

                        );

                lista.add(z);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;

    }

}