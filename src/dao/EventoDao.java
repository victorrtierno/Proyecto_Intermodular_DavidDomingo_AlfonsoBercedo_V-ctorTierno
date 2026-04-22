package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import vo.EventoVo;

public class EventoDao {

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
            e.printStackTrace();
        }

        return eventos;
    }

    

}
