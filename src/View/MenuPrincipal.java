package View;

import dao.AsientoDao;
import dao.CantanteDao;
import dao.Conexion;
import dao.EventoDao;
import dao.ZonaDao;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import vo.CantanteVo;
import vo.CarritoVo;

public class MenuPrincipal {

    static Scanner sc = new Scanner(System.in);

    static Connection conexion;
    static CantanteDao cantanteDao = new CantanteDao();
    static EventoDao eventoDao = new EventoDao();
    static ZonaDao zonaDao = new ZonaDao();
    static AsientoDao asientoDao = new AsientoDao();

    static CarritoVo carrito = new CarritoVo();

    public static void main(String[] args) {

        try {

            conexion = Conexion.getConnection();

            int opcion;

            do {

                System.out.println("------MENU PRINCIPAL------");
                System.out.println("1. Ver cantantes");
                System.out.println("2. Ver eventos de cantante");
                System.out.println("3. Ver zonas de evento");
                System.out.println("4. Ver asientos de zona");
                System.out.println("5. Añadir asiento al carrito");
                System.out.println("6. Ver carrito");
                System.out.println("0. Salir");

                System.out.print("Opción: ");
                opcion = sc.nextInt();

                if (opcion == 1) {

                    System.out.println("--- CANTANTES ---");

                    List<CantanteVo> lista = cantanteDao.obtenerCantante(conexion);
                    for (CantanteVo c : lista) {

                        System.out.println(c);

                    }

                }

            } while (opcion != 0);

            conexion.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
