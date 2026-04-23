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
import vo.AsientoVo;

public class AsientoDao {

<<<<<<< HEAD
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
=======
    public List<AsientoVo> obtenerAsientosPorZona(
            Connection conexion,
            int idZona) {

        List<AsientoVo> lista = new ArrayList<>();

        String select =
        "SELECT id, fila, numero, estado " +
        "FROM Asiento " +
        "WHERE idZona = ? AND estado = 'Libre'";

        try (PreparedStatement stmt =
                conexion.prepareStatement(select)) {

            stmt.setInt(1, idZona);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                AsientoVo a =
                new AsientoVo(
                        rs.getInt("id"),
                        rs.getInt("fila"),
                        rs.getInt("numero"),
                        rs.getString("estado")
                );
>>>>>>> david

                lista.add(a);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;
<<<<<<< HEAD

    }

}
=======
    }

    // UPDATE asiento

    public boolean ocuparAsiento(
            Connection conexion,
            int idAsiento) {

        String update =
        "UPDATE Asiento SET estado = 'Ocupado' WHERE id = ?";

        try (PreparedStatement stmt =
                conexion.prepareStatement(update)) {

            stmt.setInt(1, idAsiento);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
    }

}
>>>>>>> david
