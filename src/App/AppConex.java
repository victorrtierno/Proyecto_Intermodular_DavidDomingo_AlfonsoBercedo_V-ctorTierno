package App;

import dao.Conexion;
import dao.EventoDao;
import java.sql.Connection;
import java.util.List;
import vo.AlumnoVo;

public class AppConex {

    public static void main(String[] args) {
        //creamos el DAO para ejecutar las operaciones disponibles
        EventoDao a1DAO = new EventoDao();

        try {
            //establecer la conexión
            Connection conex = Conexion.getConnection();
            System.out.println("Conexión realizada con éxito");

            //consultar los alumnos
            List<AlumnoVo> alumnos = a1DAO.obtenerAlumnos(conex);

            for (AlumnoVo alum : alumnos) {
                System.out.println(alum);
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}
