package dao;

import java.sql.Connection;
<<<<<<< HEAD
import java.sql.ResultSet;
import java.sql.Statement;
=======
import java.sql.PreparedStatement;
import java.sql.ResultSet;
>>>>>>> david
import java.util.ArrayList;
import java.util.List;
import vo.ZonaVo;

public class ZonaDao {

<<<<<<< HEAD
    public List<ZonaVo>obtenerZonasPorEvento(Connection conexion,int idEvento) {

        List<ZonaVo> lista =
                new ArrayList<>();

        String sql ="SELECT id, nombre, precioBase " +"FROM Zona " +"WHERE idEvento = "+ idEvento;

        try (

            Statement stmt =conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                ZonaVo z =
                        new ZonaVo(

                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precioBase")

                        );

                lista.add(z);

=======
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
>>>>>>> david
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

<<<<<<< HEAD
        return lista;

    }

=======
        return zonas;
    }
>>>>>>> david
}