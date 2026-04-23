package dao;

import java.sql.Connection;
<<<<<<< HEAD
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
=======
import java.sql.PreparedStatement;
import java.sql.ResultSet;
>>>>>>> david
import java.util.ArrayList;
import java.util.List;
import vo.EventoVo;

public class EventoDao {

<<<<<<< HEAD
    public List<EventoVo> obtenerEventos(Connection conexion) {
        //Define la consulta sobre la tabla evento:
        String consulta = "SELECT id, nombre, fecha, lugar, estado FROM cantante";

        //crea el arraylist que almacenará el resultado de la SELECT
        List<EventoVo> eventos = new ArrayList<>();

        //try con recursos --> se utiliza con clases que implementan autocloseable
        //se trata de una opción que cierra automáticamente los recursos abiertos,
        //cuando se finaliza el bloque
        try (Statement stmt = conexion.createStatement(); ResultSet resultado = stmt.executeQuery(consulta)) {

            //en el try se ha creado la sentencia y se ha ejecutado la query
            //si no se sale por el catch, es que ha ido bien, queda recorrer los resultados
            while (resultado.next()) {
                //next() se va desplazando por el conjunto de resultados que devuelve el 
                //servidor y que se ha almacenado en ResultSet
                //para obtener los datos se utilizan métodos get
                //obtenemos columna a columna
                String id = resultado.getString("id");
                String nombre = resultado.getString("nombre");
                LocalDate fecha = resultado.getDate("fecha").toLocalDate();
                
                String lugar = resultado.getString("lugar");
                String estado = resultado.getString("estado");

                EventoVo eve = new EventoVo(id, nombre, fecha, lugar, estado);
                eventos.add(eve);//se añade el eventi a la lista
            }

        } catch (SQLException e) {
=======
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

>>>>>>> david
            e.printStackTrace();
        }

        return eventos;
    }
<<<<<<< HEAD

    

}
=======
}
>>>>>>> david
