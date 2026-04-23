package App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dao.CantanteDao;
import dao.EventoDao;
import dao.Conexion; 
import vo.CantanteVo;
import vo.EventoVo;

public class AppConex {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salirDelSistema = false;

        while (!salirDelSistema) {
            limpiarPantalla();
            mostrarMenuAcceso();

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                limpiarPantalla(); 

                switch (opcion) {
                    case 1:
                        accesoClientes(scanner);
                        break;
                    case 2:
                        accesoEmpleados(scanner);
                        break;
                    case 3:
                        System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
                        System.out.println("│  [OK] Desconectando de la base de datos...                           │");
                        System.out.println("│  [OK] Apagando el sistema... ¡Hasta pronto!                          │");
                        System.out.println("└──────────────────────────────────────────────────────────────────────┘");
                        salirDelSistema = true;
                        break;
                    default:
                        System.out.println("  [!] ERROR: Opción no válida. Elige 1, 2 o 3.");
                        pausa(scanner);
                }
            } catch (InputMismatchException e) {
                limpiarPantalla();
                System.out.println("  [!] ERROR FATAL: Se esperaba un valor numérico entero.");
                scanner.nextLine();
                pausa(scanner);
            }
        }
        scanner.close();
    }

    private static void mostrarMenuAcceso() {
        System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                                                                      │");
        System.out.println("│   _____ __      __ _____  _   _  _______  ____    _____              │");
        System.out.println("│  |  ___|\\ \\    / /|  ___|| \\ | ||__   __|/ __ \\  / ____|             │");
        System.out.println("│  | |__   \\ \\  / / | |__  |  \\| |   | |  | |  | || (___               │");
        System.out.println("│  |  __|   \\ \\/ /  |  __| | . ` |   | |  | |  | | \\___ \\              │");
        System.out.println("│  | |___    \\  /   | |___ | |\\  |   | |  | |__| | ____) |             │");
        System.out.println("│  |_____|    \\/    |_____||_| \\_|   |_|   \\____/ |_____/              │");
        System.out.println("│                                                                      │");
        System.out.println("│  Los mejores artistas del género                                     │");
        System.out.println("├──────────────────────────────────────────────────────────────────────┤");
        System.out.println("│                                                                      │");
        System.out.println("│   [ 1 ] Acceso Clientes (Comprar Entradas / Registrarse)             │");
        System.out.println("│   [ 2 ] Acceso Empleados (Administración de Eventos)                 │");
        System.out.println("│   [ 3 ] Salir del Sistema                                            │");
        System.out.println("│                                                                      │");
        System.out.println("└──────────────────────────────────────────────────────────────────────┘");
        System.out.print("\n  ➤ Elige cómo deseas entrar: ");
    }

    private static void accesoClientes(Scanner scanner) {
        System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
        System.out.println("│  PORTAL DE CLIENTES                                                  │");
        System.out.println("├──────────────────────────────────────────────────────────────────────┤");
        System.out.println("│  [ 1 ] Ya tengo cuenta (Iniciar sesión con Email)                    │");
        System.out.println("│  [ 2 ] Soy nuevo (Registrarme)                                       │");
        System.out.println("│  [ 3 ] Volver atrás                                                  │");
        System.out.println("└──────────────────────────────────────────────────────────────────────┘");
        System.out.print("  ➤ Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            System.out.print("\n  ➤ Introduce tu Email: ");
            String email = scanner.nextLine();
            
            try {
                Connection conex = Conexion.getConnection();
                String sql = "SELECT id, nombre FROM Cliente WHERE email = ?";
                PreparedStatement pstmt = conex.prepareStatement(sql);
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int idCliente = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    System.out.println("\n  [OK] ¡Bienvenido de nuevo, " + nombre + "!");
                    pausa(scanner);
                    menuCliente(scanner, idCliente, nombre);
                } else {
                    System.out.println("\n  [!] Error: No se encontró ninguna cuenta con ese email.");
                    pausa(scanner);
                }
            } catch (Exception e) {
                System.out.println("  [!] Error de BBDD: " + e.getMessage());
                pausa(scanner);
            }

        } else if (opcion == 2) {
            System.out.println("\n  --- NUEVO REGISTRO ---");
            System.out.print("  ➤ Nombre completo: ");
            String nombre = scanner.nextLine();
            System.out.print("  ➤ Email: ");
            String email = scanner.nextLine();
            System.out.print("  ➤ Teléfono: ");
            String telefono = scanner.nextLine();
            // YA NO SE PIDE LA TARJETA AQUÍ

            try {
                Connection conex = Conexion.getConnection();
                String sql = "INSERT INTO Cliente (nombre, email, telefono) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conex.prepareStatement(sql);
                pstmt.setString(1, nombre);
                pstmt.setString(2, email);
                pstmt.setString(3, telefono);
                pstmt.executeUpdate();

                System.out.println("\n  [OK] ¡Registro completado! Ahora inicia sesión para comprar.");
                pausa(scanner);
            } catch (Exception e) {
                System.out.println("\n  [!] Error al registrar: " + e.getMessage());
                pausa(scanner);
            }
        }
    }

    private static void accesoEmpleados(Scanner scanner) {
        System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
        System.out.println("│  ACCESO DE SEGURIDAD - EMPLEADOS                                     │");
        System.out.println("└──────────────────────────────────────────────────────────────────────┘");
        System.out.print("  ➤ Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("  ➤ Contraseña: ");
        String password = scanner.nextLine();

        try {
            Connection conex = Conexion.getConnection();
            String sql = "SELECT id, nombre, rol FROM Empleado WHERE usuario = ? AND contrasena = ?";
            PreparedStatement pstmt = conex.prepareStatement(sql);
            pstmt.setString(1, usuario);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nombreAdmin = rs.getString("nombre");
                System.out.println("\n  [OK] Acceso concedido a: " + nombreAdmin);
                pausa(scanner);
                menuEmpleado(scanner, nombreAdmin);
            } else {
                System.out.println("\n  [!] ACCESO DENEGADO.");
                pausa(scanner);
            }
        } catch (Exception e) {
            System.out.println("  [!] Error: " + e.getMessage());
            pausa(scanner);
        }
    }

    private static void menuCliente(Scanner scanner, int idCliente, String nombreCliente) {
        boolean salirCliente = false;

        while (!salirCliente) {
            limpiarPantalla();
            System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
            System.out.println("│  PANEL DE CLIENTE: " + String.format("%-49s", nombreCliente) + " │");
            System.out.println("├──────────────────────────────────────────────────────────────────────┤");
            System.out.println("│   [ 1 ] Cartelera de Eventos                                         │");
            System.out.println("│   [ 2 ] Buscar Artistas                                              │");
            System.out.println("│   [ 3 ] Comprar Entrada                                              │");
            System.out.println("│   [ 4 ] Cerrar Sesión                                                │");
            System.out.println("└──────────────────────────────────────────────────────────────────────┘");
            System.out.print("  ➤ Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            limpiarPantalla();

            switch (opcion) {
                case 1:
                    pantallaEventos();
                    pausa(scanner);
                    break;
                case 2:
                    pantallaCantantes(scanner);
                    pausa(scanner);
                    break;
                case 3:
                    System.out.println("  --- COMPRAR ENTRADA ---");
                    System.out.print("  ➤ ID del Evento: ");
                    String idEvento = scanner.nextLine();
                    
                    // AHORA PEDIMOS LA TARJETA AQUÍ
                    System.out.print("  ➤ Introduce tu número de tarjeta para el pago: ");
                    String tarjeta = scanner.nextLine();
                    
                    System.out.println("\n  [>] Validando tarjeta " + tarjeta + "...");
                    System.out.println("  [>] Procesando pago...");
                    System.out.println("  [OK] ¡Entrada generada correctamente!");
                    pausa(scanner);
                    break;
                case 4:
                    salirCliente = true;
                    break;
                default:
                    System.out.println("  [!] Opción no válida.");
                    pausa(scanner);
            }
        }
    }

    private static void menuEmpleado(Scanner scanner, String nombreAdmin) {
        boolean salirEmpleado = false;
        while (!salirEmpleado) {
            limpiarPantalla();
            System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
            System.out.println("│  ADMINISTRACIÓN: " + String.format("%-51s", nombreAdmin) + " │");
            System.out.println("├──────────────────────────────────────────────────────────────────────┤");
            System.out.println("│   [ 1 ] Ver Eventos                                                  │");
            System.out.println("│   [ 2 ] Añadir Evento                                                │");
            System.out.println("│   [ 3 ] Modificar Evento                                             │");
            System.out.println("│   [ 4 ] Eliminar Evento                                              │");
            System.out.println("│   [ 5 ] Cerrar Sesión                                                │");
            System.out.println("└──────────────────────────────────────────────────────────────────────┘");
            System.out.print("  ➤ Opción CRUD: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 5) salirEmpleado = true;
            else pausa(scanner);
        }
    }

    private static void pantallaEventos() {
        try {
            Connection conex = Conexion.getConnection();
            EventoDao eventoDao = new EventoDao();
            List<EventoVo> eventos = eventoDao.obtenerEventos(conex);
            System.out.println(" ID │ NOMBRE DEL EVENTO          │ FECHA      │ LUGAR                  ");
            System.out.println("────┼────────────────────────────┼────────────┼────────────────────────");
            for (EventoVo ev : eventos) {
                System.out.printf(" %-2d │ %-26s │ %-10s │ %-22s \n", 
                                  ev.getId(), ev.getNombre(), ev.getFecha().toString(), ev.getLugar());
            }
        } catch (Exception e) {
            System.out.println("  [!] Error: " + e.getMessage());
        }
    }

    private static void pantallaCantantes(Scanner scanner) {
        try {
            Connection conex = Conexion.getConnection();
            CantanteDao cantanteDao = new CantanteDao();
            List<CantanteVo> cantantes = cantanteDao.obtenerCantantes(conex);
            System.out.println(" ID │ NOMBRE DEL ARTISTA         ");
            System.out.println("────┼────────────────────────────");
            for (CantanteVo canta : cantantes) {
                System.out.printf(" %-2d │ %-26s \n", canta.getId(), canta.getNombre());
            }
        } catch (Exception e) {
            System.out.println("  [!] Error: " + e.getMessage());
        }
    }

    private static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    private static void pausa(Scanner scanner) {
        System.out.print("\n  ➤ Pulsa ENTER para continuar...");
        scanner.nextLine();
    }
}