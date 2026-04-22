package App;

import dao.AsientoDao;
import dao.CantanteDao;
import dao.Conexion;
import dao.EventoDao;
import dao.ZonaDao;
import java.sql.Connection;
import java.util.Scanner;
import vo.CarritoVo;

public class MenuPrincipal {

    static Scanner sc = new Scanner(System.in);

    int randomNum = (int) (Math.random() * 1000);

    static Connection conexion;
    static CantanteDao cantanteDao = new CantanteDao();
    static EventoDao eventoDao = new EventoDao();
    static ZonaDao zonaDao = new ZonaDao();
    static AsientoDao asientoDao = new AsientoDao();

    static CarritoVo carrito = new CarritoVo();

    public static void main(String[] args) {

        try {

            conexion = Conexion.getConnection();

            String email;

            do {

                System.out.println("------MENU PRINCIPAL------");
                System.out.println("introduce tu correo para verificar tu identidad");
                //si el correo contiene @empleado, accedes como empleado
                /*System.out.println("2. Ver eventos de cantante");
                System.out.println("3. Ver zonas de evento");
                System.out.println("4. Ver asientos de zona");
                System.out.println("5. Añadir asiento al carrito");
                System.out.println("6. Ver carrito");
                System.out.println("0. Salir");*/

                System.out.print("Opción: ");
                email = sc.nextLine();

                

                if (email.contains("empleado")) {
                    System.out.println("Contiene 'empleado'");
                    //le damos una acreditacion para tener acceso a la creacio de cantantes/eventos
                    int randomNum = (int) (Math.random() * 1000);
                    System.out.println("Esta es tu acreditación: "+randomNum);

                }

                System.out.println("");
                System.out.println("introduce tu correo para verificar tu identidad");

                /*if (opcion == 1) {

                    System.out.println("--- CANTANTES ---");

                    List<CantanteVo> lista = cantanteDao.obtenerCantante(conexion);
                    for (CantanteVo c : lista) {

                        System.out.println(c);

                    }

                }*/
            } while (!email.equalsIgnoreCase("salir"));

            conexion.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
