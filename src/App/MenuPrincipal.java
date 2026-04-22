package App;

import dao.AsientoDao;
import dao.CantanteDao;
import dao.Conexion;
import dao.EventoDao;
import dao.ZonaDao;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import vo.AsientoVo;
import vo.CantanteVo;
import vo.CarritoVo;
import vo.ZonaVo;

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

            System.out.print("Email: ");
            String email = sc.nextLine();

            boolean esEmpleado
                    = email.contains("@empleado");

            if (esEmpleado) {
                menuEmpleado();
            } else {
                menuCliente();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void menuCliente() {

        int op;

        do {

            System.out.println("\n--- CLIENTE ---");
            System.out.println("1. Comprar entradas");
            System.out.println("0. Salir");

            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {

                case 1 ->
                    comprarEntradas();

            }

        } while (op != 0);

    }

    static void menuEmpleado() {

        int op;

        do {

            System.out.println("\n--- EMPLEADO ---");
            System.out.println("1. Comprar entradas");
            System.out.println("2. Ver cantantes");
            System.out.println("0. Salir");

            System.out.print("Opción: ");
            op = sc.nextInt();

            switch (op) {

                case 1 ->
                    comprarEntradas();

                case 2 -> {

                    System.out.println("--- CANTANTES ---");

                    List<CantanteVo> lista
                            = cantanteDao.obtenerCantantes(conexion);

                    for (CantanteVo c : lista) {

                        System.out.println(c);

                    }

                }

            }

        } while (op != 0);

    }

    static void comprarEntradas() {

        //  CANTANTES
        System.out.println("\n--- CANTANTES ---");

        List<CantanteVo> cantantes
                = cantanteDao.obtenerCantantes(conexion);

        for (CantanteVo c : cantantes) {

            System.out.println(c);

        }

        System.out.print("ID cantante: ");
        int idCantante = sc.nextInt();

        //  EVENTOS
        System.out.println("--- EVENTOS ---");

        /*List<EventoVo> eventos= eventoDao.obtenerEventosPorCantante(
                        conexion,
                        idCantante
                );

        for (EventoVo e : eventos) {

            System.out.println(e);

        }*/

        System.out.print("ID evento: ");
        int idEvento = sc.nextInt();

        //  ZONAS
        System.out.println("--- ZONAS ---");

        List<ZonaVo> zonas = zonaDao.obtenerZonasPorEvento(conexion, idEvento);

        for (ZonaVo z : zonas) {

            System.out.println(z);

        }

        System.out.print("ID zona: ");
        int idZona = sc.nextInt();

        //  ASIENTOS
        System.out.println("--- ASIENTOS ---");

        List<AsientoVo> asientos = asientoDao.obtenerAsientosPorZona(conexion, idZona);

        for (AsientoVo a : asientos) {

            System.out.println(a);

        }

        System.out.print("ID asiento: ");
        int idAsiento = sc.nextInt();

        //  AÑADIR AL CARRITO
        for (AsientoVo a : asientos) {

            if (a.getId() == idAsiento) {

                carrito.add(a);

                System.out.println("Añadido al carrito");

            }

        }

        //  MOSTRAR CARRITO
        System.out.println("--- CARRITO ---");

        for (AsientoVo a : carrito.getAsientos()) {

            System.out.println(a);

        }

    }

}
