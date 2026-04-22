package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vo.AsientoVo;

public class AsientoDao {

    public List<AsientoVo> obtenerAsientosPorZona(Connection conexion, int idZona) {
        
        List<AsientoVo> lista
                = new ArrayList<>();

        String sql
                = "SELECT id, fila, numero, estado "
                + "FROM Asiento "
                + "WHERE idZona = "
                + idZona;

        try (
                Statement stmt
                = conexion.createStatement(); ResultSet rs
                = stmt.executeQuery(sql)) {

            while (rs.next()) {

                AsientoVo a
                        = new AsientoVo(
                                rs.getInt("id"),
                                rs.getInt("fila"),
                                rs.getInt("numero"),
                                rs.getString("estado")
                        );

                lista.add(a);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;

    }

}
