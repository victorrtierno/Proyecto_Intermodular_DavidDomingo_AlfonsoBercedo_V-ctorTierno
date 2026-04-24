package App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
                        System.out.println("│   Desconectando de la base de datos...                               │");
                        System.out.println("│   Apagando el sistema... ¡Hasta pronto!                              │");
                        System.out.println("└──────────────────────────────────────────────────────────────────────┘");
                        salirDelSistema = true;
                        break;
                    default:
                        System.out.println("   ERROR: Opción no válida. Elige 1, 2 o 3.");
                        pausa(scanner);
                }
            } catch (InputMismatchException e) {
                limpiarPantalla();
                System.out.println("   ERROR FATAL: Se esperaba un valor numérico entero.");
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
        System.out.print("\n  => Elige cómo deseas entrar: ");
    }

    private static void accesoClientes(Scanner scanner) {
        System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
        System.out.println("│  PORTAL DE CLIENTES                                                  │");
        System.out.println("├──────────────────────────────────────────────────────────────────────┤");
        System.out.println("│  [ 1 ] Ya tengo cuenta (Iniciar sesión con Email)                    │");
        System.out.println("│  [ 2 ] Soy nuevo (Registrarme)                                       │");
        System.out.println("│  [ 3 ] Volver atrás                                                  │");
        System.out.println("└──────────────────────────────────────────────────────────────────────┘");
        System.out.print("  => Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            System.out.print("\n  => Introduce tu Email: ");
            String email = scanner.nextLine();
            System.out.print("  => Introduce tu Contraseña: ");
            String contrasena = scanner.nextLine();
        
            try {
                Connection conex = Conexion.getConnection();
                String consulta = "SELECT id, nombre FROM Cliente WHERE email = ? AND contrasena = ?";
                
                try (PreparedStatement pstmt = conex.prepareStatement(consulta)) {
                    pstmt.setString(1, email);
                    pstmt.setString(2, contrasena); 
                    
                    try (ResultSet resultado = pstmt.executeQuery()) {
                        if (resultado.next()) {
                            int idCliente = resultado.getInt("id");
                            String nombre = resultado.getString("nombre");
                            System.out.println("\n   ¡Bienvenido de nuevo, " + nombre + "!");
                            pausa(scanner);
                            menuCliente(scanner, idCliente, nombre);
                        } else {
                            System.out.println("\n   Error: Email o contraseña incorrectos.");
                            pausa(scanner);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("   Error de BBDD: " + e.getMessage());
                pausa(scanner);
            }

        } else if (opcion == 2) {
            System.out.println("\n  --- NUEVO REGISTRO ---");
            System.out.print("  > Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("  > Primer Apellido: ");
            String prApellido = scanner.nextLine();
            System.out.print("  > Segundo Apellido: ");
            String sgApellido = scanner.nextLine();
            System.out.print("  > Email: ");
            String email = scanner.nextLine();
            if (!email.contains("@") && email.endsWith(".com")) {
                System.out.println("Email inválido. Escríbalo correctamente");
                accesoClientes(scanner);
            }
            System.out.print("  > Contraseña: ");
            String contrasena = scanner.nextLine();
            System.out.print("  > Teléfono: ");
            String telefono = scanner.nextLine();
            
            try {
                Connection conex = Conexion.getConnection();
                String consulta = "INSERT INTO Cliente (nombre, prAppellido, sgApellido, email, contrasena, telefono) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conex.prepareStatement(consulta);
                pstmt.setString(1, nombre);
                pstmt.setString(2, prApellido);
                pstmt.setString(3, sgApellido);
                pstmt.setString(4, email);
                pstmt.setString(5, contrasena);
                pstmt.setString(6, telefono);
                pstmt.executeUpdate();

                System.out.println("\n   ¡Registro completado! Ahora inicia sesión para comprar.");
                pausa(scanner);
            } catch (Exception e) {
                System.out.println("\n   Error al registrar: " + e.getMessage());
                pausa(scanner);
            }
        }
    }

    private static void accesoEmpleados(Scanner scanner) {
        System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
        System.out.println("│  ACCESO DE SEGURIDAD - EMPLEADOS                                     │");
        System.out.println("└──────────────────────────────────────────────────────────────────────┘");
        System.out.print("  > Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("  > Contraseña: ");
        String password = scanner.nextLine();

        try {
            Connection conex = Conexion.getConnection();
            String consulta = "SELECT id, nombre, rol FROM Empleado WHERE usuario = ? AND contrasena = ?";
            PreparedStatement pstmt = conex.prepareStatement(consulta);
            pstmt.setString(1, usuario);
            pstmt.setString(2, password);
            ResultSet resultado = pstmt.executeQuery();

            if (resultado.next()) {
                String nombreAdmin = resultado.getString("nombre");
                System.out.println("\n   Acceso concedido a: " + nombreAdmin);
                pausa(scanner);
                menuEmpleado(scanner, nombreAdmin);
            } else {
                System.out.println("\n   ACCESO DENEGADO.");
                pausa(scanner);
            }
        } catch (Exception e) {
            System.out.println("   Error: " + e.getMessage());
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
            System.out.println("│   [ 1 ] Cartelera de Eventos y Compra de Entradas                    │");
            System.out.println("│   [ 2 ] Buscar Artistas                                              │");
            System.out.println("│   [ 3 ] Mis Entradas Compradas                                       │");
            System.out.println("│   [ 4 ] Cerrar Sesión                                                │");
            System.out.println("└──────────────────────────────────────────────────────────────────────┘");
            System.out.print("  > Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            limpiarPantalla();

            switch (opcion) {
                case 1:
                    pantallaEventos(scanner, idCliente);
                    break;
                case 2:
                    pantallaCantantes(scanner);
                    pausa(scanner);
                    break;
                case 3:
                    pantallaMisEntradas(scanner, idCliente);
                    break;
                case 4:
                    salirCliente = true;
                    break;
                default:
                    System.out.println("   Opción no válida.");
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
            System.out.print("  > Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch(opcion) {
                case 1:
                    pantallaEventos(scanner, null);
                    break;
                case 5:
                    salirEmpleado = true;
                    break;
                default:
                    pausa(scanner);
            }
        }
    }

    private static void pantallaEventos(Scanner scanner, Integer idCliente) {
        boolean salirEventos = false;

        while (!salirEventos) {
            limpiarPantalla();
            try {
                Connection conex = Conexion.getConnection();
                EventoDao eventoDao = new EventoDao();
                List<EventoVo> eventos = eventoDao.obtenerEventos(conex);
                
                System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
                System.out.println("│  CARTELERA ACTUAL DE EVENTOS            INTRODUCE 0 PARA SALIR       │");
                System.out.println("└──────────────────────────────────────────────────────────────────────┘\n");
                System.out.println(" ID │ NOMBRE DEL EVENTO          │ FECHA      │ LUGAR                  ");
                System.out.println("────┼────────────────────────────┼────────────┼────────────────────────");
                for (EventoVo ev : eventos) {
                    System.out.printf(" %-2d │ %-26s │ %-10s │ %-22s \n", 
                                      ev.getId(), ev.getNombre(), ev.getFecha().toString(), ev.getLugar());
                }

                System.out.print("\n  > Introduce el ID del evento para más detalles: ");
                int seleccionId = scanner.nextInt();
                scanner.nextLine();

                if (seleccionId == 0) {
                    salirEventos = true;
                } else {
                    EventoVo eventoElegido = null;
                    for (EventoVo ev : eventos) {
                        if (ev.getId() == seleccionId) {
                            eventoElegido = ev;
                            break;
                        }
                    }

                    if (eventoElegido != null) {
                        boolean salirDetalles = false;
                        while(!salirDetalles) {
                            limpiarPantalla();
                            System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
                            System.out.println("│  DETALLES DEL EVENTO                                                 │");
                            System.out.println("├──────────────────────────────────────────────────────────────────────┤");
                            System.out.println("│  Nombre: " + eventoElegido.getNombre());
                            System.out.println("│  Lugar:  " + eventoElegido.getLugar());
                            System.out.println("│  Fecha:  " + eventoElegido.getFecha());
                            System.out.println("└──────────────────────────────────────────────────────────────────────┘");
                            
                            if (idCliente != null) {
                                System.out.println("\n  [ 1 ] Comprar Entrada(s)");
                            }
                            System.out.println("  [ 2 ] Volver a la cartelera");
                            System.out.print("\n  > Opción: ");
                            
                            int opcDetalle = scanner.nextInt();
                            scanner.nextLine();

                       
                            if (opcDetalle == 1 && idCliente != null) {
                                System.out.println("\n  --- PROCESANDO COMPRA ---");
                                System.out.print("  > Introduce tu número de tarjeta para el pago: ");
                                String tarjeta = scanner.nextLine();
                                System.out.println("\n   Validando tarjeta " + tarjeta + "...");
                                
                                Connection conxTransaccion = null;
                                try {
                                    conxTransaccion = Conexion.getConnection();
                                    conxTransaccion.setAutoCommit(false); // INICIO DE LA TRANSACCIÓN

                                    // PASO 1: Buscar un asiento libre para ese evento a través de la Zona
                                    String sqlBusqueda = "SELECT a.id, z.precioBase FROM Asiento a " +
                                                         "JOIN Zona z ON a.idZona = z.id " +
                                                         "WHERE z.idEvento = ? AND a.estado = 'Libre' LIMIT 1";
                                    
                                    int idAsiento = -1;
                                    double precio = 0.0;

                                    try (PreparedStatement pstBusqueda = conxTransaccion.prepareStatement(sqlBusqueda)) {
                                        pstBusqueda.setInt(1, eventoElegido.getId());
                                        try (ResultSet rs = pstBusqueda.executeQuery()) {
                                            if (rs.next()) {
                                                idAsiento = rs.getInt("id");
                                                precio = rs.getDouble("precioBase");
                                            }
                                        }
                                    }

                                    if (idAsiento != -1) {
                                        // PASO 2: Marcar el asiento como Ocupado
                                        String sqlUpdateAsiento = "UPDATE Asiento SET estado = 'Ocupado' WHERE id = ?";
                                        try (PreparedStatement pstUpdate = conxTransaccion.prepareStatement(sqlUpdateAsiento)) {
                                            pstUpdate.setInt(1, idAsiento);
                                            pstUpdate.executeUpdate();
                                        }

                                        // PASO 3: Crear la Venta y recuperar la ID autogenerada de la Venta
                                        String sqlInsertVenta = "INSERT INTO Venta (idCliente, fecha, precioTotal) VALUES (?, NOW(), ?)";
                                        int idVenta = -1;
                                        // Le pasamos Statement.RETURN_GENERATED_KEYS para que MySQL nos devuelva el ID de la venta
                                        try (PreparedStatement pstVenta = conxTransaccion.prepareStatement(sqlInsertVenta, Statement.RETURN_GENERATED_KEYS)) {
                                            pstVenta.setInt(1, idCliente);
                                            pstVenta.setDouble(2, precio);
                                            pstVenta.executeUpdate();

                                            try (ResultSet rsKeys = pstVenta.getGeneratedKeys()) {
                                                if (rsKeys.next()) {
                                                    idVenta = rsKeys.getInt(1);
                                                }
                                            }
                                        }

                                        // Crear la Entrada vinculando el Asiento y la Venta
                                        String sqlInsertEntrada = "INSERT INTO Entrada (idVenta, idAsiento, precio) VALUES (?, ?, ?)";
                                        try (PreparedStatement pstEntrada = conxTransaccion.prepareStatement(sqlInsertEntrada)) {
                                            pstEntrada.setInt(1, idVenta);
                                            pstEntrada.setInt(2, idAsiento);
                                            pstEntrada.setDouble(3, precio);
                                            pstEntrada.executeUpdate();
                                        }

                                        conxTransaccion.commit(); // CONFIRMAMOS LA TRANSACCIÓN SI TODO HA IDO BIEN
                                        
                                        System.out.println("   Procesando pago de " + precio + "€ para " + eventoElegido.getNombre() + "...");
                                        System.out.println("   ¡Compra realizada! Tu asiento es el nº " + idAsiento + ".");
                                        
                                    } else {
                                        System.out.println("   Lo sentimos mucho, ¡Aforo completo! No quedan asientos libres.");
                                    }

                                } catch (Exception exCompra) {
                                    System.out.println("   Error al procesar la compra: " + exCompra.getMessage());
                                    if (conxTransaccion != null) {
                                        try { 
                                            conxTransaccion.rollback(); // DESHACEMOS LOS CAMBIOS SI ALGO FALLA
                                        } catch(Exception rbEx) { }
                                    }
                                } finally {
                                    if (conxTransaccion != null) {
                                        try { 
                                            conxTransaccion.setAutoCommit(true); // Establecemos el valor inicial
                                        } catch(Exception acEx) { }
                                    }
                                }
                                
                                pausa(scanner);
                                salirDetalles = true; 
                                
                            } else if (opcDetalle == 2) {
                                salirDetalles = true;
                            } else {
                                System.out.println("   Opción inválida.");
                                pausa(scanner);
                            }
                        }
                    } else {
                        System.out.println("   No existe ningún evento con ese ID.");
                        pausa(scanner);
                    }
                }
            } catch (Exception e) {
                System.out.println("   Error de BBDD: " + e.getMessage());
                pausa(scanner);
                salirEventos = true;
            }
        }
    }

    private static void pantallaMisEntradas(Scanner scanner, int idCliente) {
        System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
        System.out.println("│  MIS ENTRADAS COMPRADAS                                              │");
        System.out.println("└──────────────────────────────────────────────────────────────────────┘\n");
        
        try {
            Connection conex = Conexion.getConnection();
            String sql = "SELECT ev.nombre AS nombre_evento, ev.fecha AS fecha_evento, v.fecha AS fecha_compra " +
                         "FROM Venta v " +
                         "JOIN Entrada en ON v.id = en.idVenta " +
                         "JOIN Asiento a ON en.idAsiento = a.id " +
                         "JOIN Zona z ON a.idZona = z.id " +
                         "JOIN Evento ev ON z.idEvento = ev.id " +
                         "WHERE v.idCliente = ?";

            try (PreparedStatement pstmt = conex.prepareStatement(sql)) {
                pstmt.setInt(1, idCliente);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    System.out.println(" EVENTO                     │ FECHA EVENTO │ FECHA DE COMPRA ");
                    System.out.println("────────────────────────────┼──────────────┼─────────────────");
                    
                    boolean tieneEntradas = false;
                    while (rs.next()) {
                        tieneEntradas = true;
                        System.out.printf(" %-26s │ %-12s │ %-15s \n",
                                rs.getString("nombre_evento"),
                                rs.getString("fecha_evento"),
                                rs.getString("fecha_compra"));
                    }
                    
                    if (!tieneEntradas) {
                        System.out.println("  (Todavía no tienes entradas registradas en la base de datos)");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("   Error al consultar el historial: " + e.getMessage());
        }
        pausa(scanner);
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
            System.out.println("   Error: " + e.getMessage());
        }
    }

    private static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    private static void pausa(Scanner scanner) {
        System.out.print("\n  > Pulsa ENTER para continuar...");
        scanner.nextLine();
    }
}