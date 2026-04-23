package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vo.AsientoVo;

public class AsientoDao {

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

                lista.add(a);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return lista;
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