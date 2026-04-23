package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vo.EventoVo;

public class EventoDao {

    public List<EventoVo> obtenerEventosPorCantante(
            Connection conexion,
            int idCantante) {

        List<EventoVo> eventos = new ArrayList<>();

        String consulta =
        "SELECT e.id, e.nombre, e.fecha, e.lugar, e.estado " +
        "FROM Evento e " +
        "JOIN Evento_Cantante ec ON e.id = ec.idEvento " +
        "WHERE ec.idCantante = ?";

        try (PreparedStatement stmt =
                conexion.prepareStatement(consulta)) {

            stmt.setInt(1, idCantante);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                EventoVo ev =
                new EventoVo(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nombre"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getString("lugar"),
                        rs.getString("estado")
                );

                eventos.add(ev);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return eventos;
    }
}