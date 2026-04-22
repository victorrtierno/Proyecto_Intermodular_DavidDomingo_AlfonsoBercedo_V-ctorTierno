package App;

import dao.Conexion;
import dao.CantanteDao;
import java.sql.Connection;
import java.util.List;
import vo.CantanteVo;

public class AppConex {

    public static void main(String[] args) {
        //creamos el DAO para ejecutar las operaciones disponibles
        CantanteDao a1DAO = new CantanteDao();

        try {
            //establecer la conexión
            Connection conex = Conexion.getConnection();
            System.out.println("Conexión realizada con éxito");

            //consultar los alumnos
            List<CantanteVo> cantantes = a1DAO.obtenerCantantes(conex);

            for (CantanteVo canta : cantantes) {
                System.out.println(canta);
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}
