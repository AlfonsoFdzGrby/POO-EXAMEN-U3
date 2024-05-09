package Sistema;

import Sistema.util.*;
import Usuarios.Cliente;
import Usuarios.Inversionista;
import Usuarios.util.*;
import Usuarios.util.Tarjeta.*;

import java.time.LocalDate;
import java.util.*;

public class Menu {

    private static Scanner sc = new Scanner(System.in);
    private static Sistema sistema = new Sistema();
    private static SucursalActual sucursalActual = null;
    private static UsuarioEnSesion usuarioEnSesion = null;

    // ------------------------------------------ MÉTODO PÚBLICO ------------------------------------------

    public static void ejecutar(){
        Sucursal sucursal = null;
        int opc = 1;
        while(opc>0 && opc<3){
            Tools.printHeader("¡BIENVENIDO A POOBANK!");
            System.out.println("Por favor ingrese la sucursal a la que quiere entrar");
            System.out.println("1. Acueducto");
            System.out.println("2. Madero");
            System.out.println("3. Salir del programa");
            System.out.print(">> ");
            opc = sc.nextInt();
            sc.nextLine();
            switch (opc) {
                case 1 -> {
                    sucursal = sistema.getAcueducto();
                    sucursalActual = SucursalActual.getInstancia();
                    sucursalActual.setSucursal(sucursal);
                    iniciarSesion();
                }
                case 2 -> {
                    sucursal = sistema.getMadero();
                    sucursalActual = SucursalActual.getInstancia();
                    sucursalActual.setSucursal(sucursal);
                    iniciarSesion();
                }
            }
        }
    }

    // ------------------------------------------ INICIAR SESIÓN ------------------------------------------

    private static void iniciarSesion(){
        sucursalActual.getSucursalActual().agregarUsuario(Rol.Cliente, new Cliente("Pedro", "Fernandez Carreón",
        LocalDate.of(2000, 2, 25), "Morelia", "Michoacán", true, "PeFeCa", "12345", "Calle xd", NombreSucursal.Acueducto));
        Tools.printHeader("SUCURSAL " + sucursalActual.getSucursalActual().getNombre().toString().toUpperCase());
        System.out.println("Por favor inicie sesión");
        Usuario usuario = Usuario.buscarUsuario();

        int intentos = 3;

        while (true) {
            if(intentos<=0){
                System.out.println("Sus intentos se han agotado");
                Tools.next();
                break;
            }

            System.out.println("Ingrese su contraseña");
            System.out.println("TIENE " + intentos + " INTENTOS");
            System.out.print(">> ");
            String contraseña = sc.nextLine();

            if(contraseña.equals(usuario.getContraseña())){
                System.out.println("Contraseña correcta");
                usuarioEnSesion = UsuarioEnSesion.getInstancia();
                usuarioEnSesion.setUsuario(usuario);
                System.out.println("Se ha iniciado sesión correctamente con el usuario " + usuarioEnSesion.getUsuarioActual().getNombreUsuario());
                Tools.next();

                if(usuarioEnSesion.getUsuarioActual() instanceof Cliente){
                    menuCliente();
                }else if(usuarioEnSesion.getUsuarioActual() instanceof Inversionista){
                    menuInversionista();
                }else{
                    // Le quise poner un switch Lambda pero me puso caprichos ésta madre
                    switch (usuarioEnSesion.getUsuarioActual().getRol()) {
                        case Rol.Gerente:
                            menuGerente();
                            break;

                        case Rol.Ejecutivo:
                            menuEjecutivo();
                            break;

                        default:
                            menuCapturista();
                            break;
                    }
                }
                break;

            }else{
                System.out.println("Contraseña incorrecta");
                intentos--;
            }
        }

    }

    // ----------------------------------------- MENÚS USUARIOS -----------------------------------------

    private static void menuCliente(){
        int opc = 0;
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("CLIENTE");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear tarjeta (débito únicamente)");
            System.out.println("2. Consultar tarjetas");
            // salta el menú para seleccionar su tarjeta
            System.out.println("3. Realizar retiro");
            System.out.println("4. Realizar depósito");
            System.out.println("5. Consultar datos personales");
            System.out.println("6. Cerrar sesión");
            System.out.print(">> ");
            
            opc = Tools.nextInt();

            switch (opc) {
                case 1 -> crearTarjeta(false);
                case 2 -> consultarTarjetas(false);
                case 3 -> realizarRetiro();
                case 5 -> consultarDatos();
                case 6 -> usuarioEnSesion.setUsuario(null);
            }
        }
    }

    private static void menuInversionista(){
        int opc = 0;
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("INVERSIONISTA");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registar ejecutivo");
            System.out.println("2. Modificar ejecutivo");
            System.out.println("3. Eliminar ejecutivo");
            System.out.println("4. Consultar ejecutivo");
            System.out.println("5. Cerrar sesión");
            System.out.print(">> ");
            opc = Tools.nextInt();
            switch (opc) {
                case 5 -> usuarioEnSesion.setUsuario(null);
            }
        }
        
    }

    /* Los capturistas al parecer sólo deben de hacer operaciones sobre los Ejecutivos XDD 
     * Y los ejecutivos son los que deben hacer el CRUD de los clientes, etc.
    */
    private static void menuCapturista(){
        Tools.printHeader("CAPTURISTA");
        System.out.println("Seleccione una opción:");
    }

    private static void menuEjecutivo(){
        int opc = 0;
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("EJECUTIVO");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Modificar Cliente");
            System.out.println("3. Eliminar Cliente");
            // 3 opciones: Información de un sólo cliente, información de todos los clientes, e información propia
            System.out.println("4. Mostrar información");
            System.out.println("5. Cerrar sesión");
            System.out.print(">> ");

            opc = Tools.nextInt();

            switch (opc) {
                //case 1 -> registrarUsuario();
                //case 2 -> modificarUsuario();
                //case 3 -> eliminarUsuario();
                //case 4 -> mostrarInformacion();
                case 5 -> usuarioEnSesion.setUsuario(null);
            }
        }
        
    }

    private static void menuGerente(){
        Tools.printHeader("GERENTE");
        System.out.println("Seleccione una opción:");
    }

    // ---------------------------------------- MENÚS FUNCIONES ----------------------------------------

    /* Algunos métodos reciben un atributo "asksforUser" para que pueda ser utilizado tanto 
     * por capturistas como por el cliente y así no tener que escribir líneas redundantes
     * 
     * True: Llama una función para buscar al usuario
     * False: Trabaja directamente con el usuarioEnSesion
    */

    private static void crearTarjeta(boolean asksforUser){
        Tools.printHeader("CREAR TARJETA");
        Cliente cliente = null;
        if(asksforUser){
            while(true){
                Usuario usuario = Usuario.buscarUsuario();
                if(usuario instanceof Cliente){
                    cliente = (Cliente)usuario;
                    break;
                }else{
                    System.out.println("El usuario especificado no es un cliente");
                    System.out.println("Por favor ingrese un cliente válido");
                }
            }
        }else{
            cliente = (Cliente)usuarioEnSesion.getUsuarioActual();
        }

        if(!(cliente.getTarjetas().get(TipoDeTarjeta.Debito).isEmpty())){
            System.out.println("El cliente ya tiene una tarjeta de débito");
            System.out.println("Por favor actualice su tarjeta a crédito para continuar");
        }else{
            sucursalActual.getSucursalActual().getUsuarios().get(Rol.Cliente).remove(cliente);

            Tarjeta tarjeta = null;

            /* IMPORTANTE: Si el cliente ya tiene una tarjeta de débito se tiene que validar 
                * que no puede crear otra hasta que actualice la cuenta que ya tiene a alguna cuenta
                * de crédito (Simplicity, platino u oro)
            */
            System.out.println("TARJETA DE DÉBITO");
            System.out.println("Ingrese el depósito inicial de la tarjeta:");
            System.out.print(">> $");
            double deposito = Tools.nextDouble();
            tarjeta = new Tarjeta(TipoDeTarjeta.Debito, deposito , sucursalActual.getSucursalActual().getNombre());
            cliente.agregarTarjeta(TipoDeTarjeta.Debito, tarjeta);
            usuarioEnSesion.setUsuario(cliente);
            sucursalActual.getSucursalActual().getUsuarios().get(Rol.Cliente).add(cliente);
            System.out.println("¡Su tarjeta ha sido creada exitosamente!");
        }
        Tools.next();
    }
    

    private static void consultarTarjetas(boolean asksforUser){
        Tools.printHeader("CONSULTAR TARJETAS");
        Cliente cliente = null;
        if(asksforUser){
            while(true){
                Usuario usuario = Usuario.buscarUsuario();
                if(usuario instanceof Cliente){
                    cliente = (Cliente)usuario;
                    break;
                }else{
                    System.out.println("El usuario especificado no es un cliente");
                    System.out.println("Por favor ingrese un cliente válido");
                }
            }
        }else{
            cliente = (Cliente)usuarioEnSesion.getUsuarioActual();
        }

        cliente.mostrarTarjetas();
        Tools.next();
    }

    private static void consultarDatos(){
        Tools.printHeader("CONSULTA DE DATOS PERSONALES");
        System.out.print(usuarioEnSesion.getUsuarioActual().toString());
        Tools.next();
    }

    private static void realizarRetiro(){
        Cliente cliente = (Cliente)usuarioEnSesion.getUsuarioActual();
        Tarjeta tarjeta = null;
        if(cliente.getTarjetas().values().isEmpty()){
            System.out.println("No existen tarjetas registradas aún");
            System.out.println("Por favor registre al menos una tarjeta antes de continuar");
        }else{
            Tools.printHeader("REALIZAR RETIRO");
            System.out.println("Ingrese el tipo de tarjeta del cual quiere retirar");
            System.out.println("1. Débito");
            System.out.println("2. Simplicity");
            System.out.println("3. Platino");
            System.out.println("4. Oro");
            System.out.println("5. Cancelar transacción");
            System.out.print(">> ");
            int opc = Tools.nextInt();

            switch (opc) {
                case 1 -> {
                    Tools.printHeader("RETIRO - DÉBITO");
                    
                    if(cliente.getTarjetas().get(TipoDeTarjeta.Debito).isEmpty()){
                        System.out.println("   * No hay tarjetas de débito registradas");
                    }else{
                        tarjeta = cliente.getDebito();
                        System.out.println("Número de tarjeta: " + tarjeta.getNumTarjeta());
                        System.out.printf("Saldo: $%.2f", + tarjeta.getSaldo());
                        while(true){
                            System.out.print("Ingrese la cantidad a retirar: ");
                            double cantidad = Tools.nextDouble();
                            if(cantidad<0 || cantidad>tarjeta.getSaldo()){
                                tarjeta.realizarRetiro(cantidad);
                                System.out.println("¡El retiro se ha hecho exitosamente!");
                                break;
                            }else{
                                System.out.println("La cantidad especificada no es válida");
                            }
                        }
                        usuarioEnSesion.setUsuario(cliente);
                        Tools.next();
                    }
                }

                case 2 -> {
                    Tools.printHeader("RETIRO - SIMPLICITY");

                    if(cliente.getSimplicity().isEmpty()){
                        System.out.println("   * No hay tarjetas Simplicity registradas");
                    }else{
                        System.out.println("TARJETAS SIMPLICITY");
                        int listId = 0;
                        for (Tarjeta tarjetaAMostrar : cliente.getSimplicity()) {
                            System.out.println("   * " + listId + ": " + tarjetaAMostrar.getNumTarjeta());
                            System.out.printf("      - Saldo: $%.2f", tarjetaAMostrar.getSaldo());
                        }

                        while(true){
                            System.out.println("Ingrese el ID de la tarjeta a usar:");
                            int id = Tools.nextInt();

                            try {
                                tarjeta = cliente.getSimplicity().get(id);
                                break;
                            } catch (Exception e) {
                                System.out.println("ID inválido");
                            }
                        }

                        System.out.print("Ingrese la cantidad a retirar: ");
                        double cantidad = Tools.nextDouble();
                        if(cantidad<0 || cantidad>tarjeta.getSaldo()){
                            tarjeta.realizarRetiro(cantidad);
                            System.out.println("¡El retiro se ha hecho exitosamente!");
                            break;
                        }else{
                            System.out.println("La cantidad especificada no es válida");
                        }

                    }
                    usuarioEnSesion.setUsuario(cliente);
                    Tools.next();
                }

                case 3 -> {
                    Tools.printHeader("RETIRO - PLATINO");

                    if(cliente.getPlatino().isEmpty()){
                        System.out.println("   * No hay tarjetas Platino registradas");
                    }else{
                        System.out.println("TARJETAS PLATINO");

                        int listId = 0;
                        for (Tarjeta tarjetaAMostrar : cliente.getPlatino()) {
                            System.out.println("   * " + listId + ": " + tarjetaAMostrar.getNumTarjeta());
                            System.out.printf("      - Saldo: $%.2f", tarjetaAMostrar.getSaldo());
                        }

                        while(true){
                            System.out.println("Ingrese el ID de la tarjeta a usar:");
                            int id = Tools.nextInt();

                            try {
                                tarjeta = cliente.getPlatino().get(id);
                                break;
                            } catch (Exception e) {
                                System.out.println("ID inválido");
                            }
                        }

                        System.out.print("Ingrese la cantidad a retirar: ");
                        double cantidad = Tools.nextDouble();
                        if(cantidad<0 || cantidad>tarjeta.getSaldo()){
                            tarjeta.realizarRetiro(cantidad);
                            System.out.println("¡El retiro se ha hecho exitosamente!");
                            break;
                        }else{
                            System.out.println("La cantidad especificada no es válida");
                        }
                    }
                    usuarioEnSesion.setUsuario(cliente);
                    Tools.next();
                }

                case 4 -> {
                    Tools.printHeader("RETIRO - SIMPLICITY");
                }
            }
        }

        Tools.next();
    }
    
}
