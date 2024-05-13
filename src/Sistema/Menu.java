package Sistema;

import Sistema.util.*;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Inversionista;
import Usuarios.util.*;
import Usuarios.util.Tarjeta.*;

import java.util.*;

public class Menu {

    private static Scanner sc = new Scanner(System.in);
    private static Sistema sistema = new Sistema();
    private static SucursalActual sucursalActual = null;
    private static UsuarioEnSesion usuarioEnSesion = null;

    // ------------------------------------------ MÉTODO PÚBLICO ------------------------------------------

    public static void ejecutar(){
        boolean flag = true;
        Sucursal sucursal = null;
        int opc = 1;
        while(flag){
            Tools.printHeader("¡BIENVENIDO A POOBANK!");
            System.out.println("Por favor ingrese la sucursal a la que quiere entrar");
            System.out.println("1. Acueducto");
            System.out.println("2. Madero");
            System.out.println("3. Salir del programa");
            System.out.print(">> ");
            opc = Tools.nextInt();
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
                case 3 -> flag = false;
                default -> System.out.println("\nOpcion incorrecta, intente de nuevo!\n");
            }
        }
    }

    // ------------------------------------------ INICIAR SESIÓN ------------------------------------------

    private static void iniciarSesion(){
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
                    // Ya le pude poner el switch lambda pero no lo he calado jsjs
                    switch (usuarioEnSesion.getUsuarioActual().getRol()) {
                        case Gerente -> menuGerente();
                        case Ejecutivo -> menuEjecutivo();
                        default -> menuCapturista();
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
        Cliente cliente = (Cliente)usuarioEnSesion.getUsuarioActual();
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("CLIENTE");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Consultar tarjetas");
            // salta el menú para seleccionar su tarjeta
            System.out.println("2. Realizar retiro");
            System.out.println("3. Realizar depósito");
            System.out.println("4. Consultar datos personales");
            if (cliente.getDebito().getSaldo() >= 50000 && !cliente.haSolicitado) {
                System.out.println("5. Solicitar actualización de tarjeta");
                System.out.println("6. Cerrar sesión");    
            } else if (cliente.haSolicitado) {
                System.out.println("5. Ver Solicitud de Tarjeta");
                System.out.println("6. Cerrar Sesion");
            } else {
                System.out.println("5. Cerrar Sesion");
            }
            System.out.print(">> ");
            
            opc = Tools.nextInt();

            if (cliente.getDebito().getSaldo() >= 50000 && !cliente.haSolicitado) {
                switch (opc) {
                    case 1 -> consultarTarjetas(false);
                    case 2 -> realizarRetiro();
                    case 3 -> realizarDeposito();
                    case 4 -> consultarDatos();
                    case 5 -> solicitarNuevaTarjeta();
                    case 6 -> usuarioEnSesion.setUsuario(null);
                    default -> System.out.println("\nOpcion incorrecta, intente de nuevo!\n");
                }    
            } else if (cliente.haSolicitado) {
                switch (opc) {
                    case 1 -> consultarTarjetas(false);
                    case 2 -> realizarRetiro();
                    case 3 -> realizarDeposito();
                    case 4 -> consultarDatos();
                    case 5 -> cliente.verSolicitudes();
                    case 6 -> usuarioEnSesion.setUsuario(null);
                    default -> System.out.println("\nOpcion incorrecta, intente de nuevo!\n");
                }
            } else {
                switch (opc) {
                    case 1 -> consultarTarjetas(false);
                    case 2 -> realizarRetiro();
                    case 3 -> realizarDeposito();
                    case 4 -> consultarDatos();
                    case 5 -> usuarioEnSesion.setUsuario(null);
                    default -> System.out.println("\nOpcion incorrecta, intente de nuevo!\n");
                }
            }
        }
    }

    private static void menuInversionista(){
        int opc = 0;
        Inversionista inversionista = (Inversionista)usuarioEnSesion.getUsuarioActual();
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("INVERSIONISTA");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Invertir");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Mostrar informacion");
            System.out.println("4. Mostrar Movimientos");
            System.out.println("5. Cerrar sesión");
            System.out.print(">> ");
            opc = Tools.nextInt();
            switch (opc) {
                case 1 -> Movimientos.RealizarInversiones(inversionista);
                case 2 -> Movimientos.RealizarRetiro(inversionista);
                case 3 -> inversionista.toString();
                case 4 -> inversionista.printMovimientos();
                case 5 -> usuarioEnSesion.setUsuario(null);
                default -> System.out.println("\nOpcion incorrecta, intente de nuevo!\n");
            }
        }
        
    }

    /* Los capturistas al parecer sólo deben de hacer operaciones sobre los Ejecutivos XDD 
     * Y los ejecutivos son los que deben hacer el CRUD de los clientes, etc.
    */

    private static void menuCapturista(){
        int opc = 0;
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("CAPTURISTA");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar Ejecutivo");
            System.out.println("2. Modificar Ejecutivo");
            System.out.println("3. Eliminar Ejecutivo");
            System.out.println("4. Mostrar informacion");
            System.out.println("5. Cerrar Sesion");
            System.out.print(">> ");

            opc = Tools.nextInt();

            switch (opc) {
                case 1 -> Empleado.registrarEjecutivo();
                case 2 -> Empleado.modificarUsuario(Rol.Ejecutivo);
                case 3 -> Empleado.borrarUsuario(Rol.Ejecutivo);
                case 4 -> mostrarInformacion();
                case 5 -> usuarioEnSesion.setUsuario(null);
                default -> System.out.println("\nOpcion incorrecta, intente de nuevo!\n");
            }
        }
        
    }

    private static void menuEjecutivo(){
        int opc = 0;
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("EJECUTIVO");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Modificar Cliente");
            System.out.println("3. Eliminar Cliente");
            System.out.println("4. Manejar solicitudes pendientes");
            // 3 opciones: Información de un sólo cliente, información de todos los clientes, e información propia
            System.out.println("5. Mostrar información");
            System.out.println("6. Cerrar sesión");
            System.out.print(">> ");

            opc = Tools.nextInt();

            switch (opc) {
                case 1 -> Cliente.registrarCliente();
                case 2 -> Usuario.modificarUsuario(Rol.Cliente);
                case 3 -> Usuario.borrarUsuario(Rol.Cliente);
                case 4 -> Empleado.manejarSolicitudes();
                case 5 -> mostrarInformacion();
                case 6 -> usuarioEnSesion.setUsuario(null);
                default -> System.out.println("\nOpcion incorrecta, intente de nuevo!\n");
            }
        }
        
    }

    private static void menuGerente(){
        int opc = 0;
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("GERENTE");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registros");
            System.out.println("2. Modificaciones");
            System.out.println("3. Eliminar del Sistema");
            System.out.println("4. Manejar solicitudes pendientes");
            System.out.println("5. Mostrar información");
            System.out.println("6. Mostrar movimientos Inversionistas");
            System.out.println("7. Cerrar sesión");
            System.out.print(">> ");

            opc = Tools.nextInt();

            switch (opc) {
                case 1 -> gerenteRegistros();
                case 2 -> gerenteModificaciones();
                case 3 -> gerenteEliminaciones();
                case 4 -> Empleado.manejarSolicitudes();
                case 5 -> mostrarInformacion();
                case 6 -> Movimientos.ImprimirOrdenes();
                case 7 -> usuarioEnSesion.setUsuario(null);
                default -> System.out.println("\nOpcion incorrecta, intente de nuevo!\n");
            }
        }
    }

    // ---------------------------------------- SUBMENUS GERENTE ---------------------------------------
    
    private static void gerenteRegistros(){
        boolean flag = true;
        while (flag) {
            Tools.printHeader("REGISTROS");
            System.out.println("Seleccione que desea registrar: ");
            System.out.println("1. Cliente");
            System.out.println("2. Ejecutivo");
            System.out.println("3. Capturista");
            System.out.println("4. Inversionista");
            System.out.println("5. Volver");
            System.out.print(">> ");

            int opc = Tools.nextInt();

            switch (opc) {
                case 1 -> Cliente.registrarCliente();
                case 2 -> Empleado.registrarEjecutivo();
                case 3 -> Empleado.registrarCapturista();
                case 4 -> Inversionista.registrarInversionista();
                case 5 -> flag = false;
                default -> System.out.println("\nOpcion incorrecta, intente de nuevo!\n");
            }
        }
    }

    private static void gerenteModificaciones(){
        boolean flag = true;
        while (flag) {
            Tools.printHeader("MODIFICACIONES");
            System.out.println("Seleccione que desea modificar: ");
            System.out.println("1. Cliente");
            System.out.println("2. Ejecutivo");
            System.out.println("3. Capturista");
            System.out.println("4. Inversionista");
            System.out.println("5. Volver");
            System.out.print(">> ");

            int opc = Tools.nextInt();

            switch (opc) {
                case 1 -> Usuario.modificarUsuario(Rol.Cliente);
                case 2 -> Usuario.modificarUsuario(Rol.Ejecutivo);
                case 3 -> Usuario.modificarUsuario(Rol.Capturista);
                case 4 -> Usuario.modificarUsuario(Rol.Inversionista);
                case 5 -> flag = false;
                default -> System.out.println("\nOpcion incorrecta, intente de nuevo!\n");
            }
        }
    }

    private static void gerenteEliminaciones(){
        boolean flag = true;
        while (flag) {
            Tools.printHeader("ELIMINAR");
            System.out.println("Seleccione que desea elminiar: ");
            System.out.println("1. Cliente");
            System.out.println("2. Ejecutivo");
            System.out.println("3. Capturista");
            System.out.println("4. Inversionista");
            System.out.println("5. Volver");
            System.out.print(">> ");

            int opc = Tools.nextInt();

            switch (opc) {
                case 1 -> Usuario.borrarUsuario(Rol.Cliente);
                case 2 -> Usuario.borrarUsuario(Rol.Ejecutivo);
                case 3 -> Usuario.borrarUsuario(Rol.Capturista);
                case 4 -> Usuario.borrarUsuario(Rol.Inversionista);
                case 5 -> flag = false;
                default -> System.out.println("\nOpcion incorrecta, intente de nuevo!\n");
            }
        }
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

        if(cliente.getDebito()!=null){
            System.out.println("El cliente ya tiene una tarjeta de débito");
            System.out.println("Por favor actualice su tarjeta a crédito antes de continuar");
        }else{
            Tarjeta tarjeta = null;
            System.out.println("TARJETA DE DÉBITO");
            System.out.println("Ingrese el depósito inicial de la tarjeta:");
            System.out.print(">> $");
            double deposito = Tools.nextDouble();
            tarjeta = new Tarjeta(TipoDeTarjeta.Debito, deposito , sucursalActual.getSucursalActual().getNombre());
            cliente.agregarTarjeta(TipoDeTarjeta.Debito, tarjeta);

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

    // ---------------------------------------- REALIZAR RETIRO ----------------------------------------

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
                    tarjeta = cliente.getDebito();
                    Tools.printHeader("RETIRO - DÉBITO");
                    if(tarjeta==null) System.out.println("   * No hay ninguna tarjeta de débito registrada");
                    else retirar(tarjeta);
                }

                case 2 -> {
                    tarjeta = cliente.getSimplicity();
                    Tools.printHeader("RETIRO - SIMPLICITY");
                    if(tarjeta==null) System.out.println("   * No hay ninguna tarjeta Simplicity registrada");
                    else retirar(tarjeta);
                }

                case 3 -> {
                    tarjeta = cliente.getPlatino();
                    Tools.printHeader("RETIRO - PLATINO");
                    if(tarjeta==null) System.out.println("   * No hay ninguna tarjeta Platino registrada");
                    else retirar(tarjeta);
                }

                case 4 -> {
                    tarjeta = cliente.getSimplicity();
                    Tools.printHeader("RETIRO - ORO");
                    if(tarjeta==null) System.out.println("   * No hay ninguna tarjeta Oro registrada");
                    else retirar(tarjeta);
                }

            }
        }
        Tools.next();
    }

    // ---------------------------------------- DEPOSITAR DINERO ----------------------------------------

    private static void realizarDeposito(){
        Cliente cliente = (Cliente)usuarioEnSesion.getUsuarioActual();
        Tarjeta tarjeta = null;
        if(cliente.getTarjetas().values().isEmpty()){
            System.out.println("No existen tarjetas registradas aún");
            System.out.println("Por favor registre al menos una tarjeta antes de continuar");
        }else{
            Tools.printHeader("REALIZAR DEPÓSITO");
            System.out.println("Ingrese el tipo de tarjeta a la que quiere depositar");
            System.out.println("1. Débito");
            System.out.println("2. Simplicity");
            System.out.println("3. Platino");
            System.out.println("4. Oro");
            System.out.println("5. Cancelar transacción");
            System.out.print(">> ");
            int opc = Tools.nextInt();
            switch (opc) {
                case 1 -> {
                    tarjeta = cliente.getDebito();
                    Tools.printHeader("DEPÓSITO - DEBITO");
                    if(tarjeta==null) System.out.println("   * No hay ninguna tarjeta de débito registrada");
                    else depositar(tarjeta);
                }

                case 2 -> {
                    tarjeta = cliente.getSimplicity();
                    Tools.printHeader("DEPÓSITO - SIMPLICITY");
                    if(tarjeta==null) System.out.println("   * No hay ninguna tarjeta Simplicity registrada");
                    else depositar(tarjeta);
                }

                case 3 -> {
                    tarjeta = cliente.getPlatino();
                    Tools.printHeader("DEPÓSITO - PLATINO");
                    if(tarjeta==null) System.out.println("   * No hay ninguna tarjeta Platino registrada");
                    else depositar(tarjeta);  
                }

                case 4 -> {
                    tarjeta = cliente.getOro();
                    Tools.printHeader("DEPÓSITO - ORO");
                    if(tarjeta==null) System.out.println("   * No hay ninguna tarjeta Oro registrada");
                    else depositar(tarjeta);
                }
            }
            Tools.next();
        }
    }

    // --------------------------------- SUBMÉTODOS PARA RETIRAR Y DEPOSITAR ---------------------------------

    private static void retirar(Tarjeta tarjeta){
        System.out.println("TARJETA:");
        System.out.println("   * Número: " + tarjeta.getNumTarjeta());
        System.out.printf("      - Saldo: $%.2f\n", tarjeta.getSaldo());

        while(true){
            if(tarjeta.getSaldo()<=0){
                System.out.println("Cuenta sin fondos");
                System.out.println("Realice un depósito antes de continuar");
                Tools.next();
                break;
            }
            System.out.print("Ingrese la cantidad a retirar: ");
            double cantidad = Tools.nextDouble();
            if(cantidad>0 && cantidad<tarjeta.getSaldo()){
                tarjeta.realizarRetiro(cantidad);
                System.out.println("¡El retiro se ha hecho exitosamente!");
                break;
            }else{
                System.out.println("La cantidad especificada no es válida");
            }
        }
    }

    private static void depositar(Tarjeta tarjeta){
        System.out.println("TARJETA:");
        System.out.println("   * Número: " + tarjeta.getNumTarjeta());
        System.out.printf("      - Saldo: $%.2f\n", + tarjeta.getSaldo());

        while(true){
            System.out.print("Ingrese la cantidad a depositar: ");
            double cantidad = Tools.nextDouble();
            if(cantidad>0){
                tarjeta.realizarDeposito(cantidad);
                System.out.println("¡El depósito se ha hecho exitosamente!");
                break;
            }else{
                System.out.println("La cantidad especificada no es válida");
            }
        }
    }

    // --------------------------------- SOLICITUD DE ACTUALIZACION ---------------------------------
    
    private static void solicitarNuevaTarjeta(){
        Tarjeta tarjeta = null;
        Cliente cliente = (Cliente)usuarioEnSesion.getUsuarioActual();
        Tools.printHeader("SOLICITAR ACTUALIZACIÓN DE TARJETA");
        System.out.println("Ingrese el tipo de tarjeta que desea actualizar");
        System.out.println("1. Débito");
        System.out.println("2. Simplicity");
        System.out.println("3. Platino");
        System.out.println("4. Cancelar operación");
        System.out.print(">> ");
        int opc = Tools.nextInt();
        switch (opc) {
            case 1 -> {
                Tools.printHeader("ACTUALIZAR - DEBITO");
                tarjeta = cliente.getDebito();

                if(tarjeta==null){
                    System.out.println("   * No hay ninguna tarjeta de débito registrada");
                }else if(tarjeta.getSaldo()<50000){
                    System.out.println("   * Se necesita de un saldo mayor o igual a $50,000 para");
                    System.out.println("     continuar con esta operación");
                }else{
                    System.out.println("   * Su tarjeta de débito puede ser actualizada a Simplicity");
                    System.out.println("   * Las solicitudes de actualización deben ser aprobadas por");
                    System.out.println("     un ejecutivo de cuenta o gerente, por lo que el tiempo");
                    System.out.println("     de espera es indefinido");
                    System.out.println("¿Desea solicitar una actualización de tarjeta? (s/n)");
                    System.out.print(">> ");
                    char yOrN = sc.nextLine().charAt(0);

                    if(Tools.AskForYesOrNo(yOrN)){
                        SucursalActual.getInstancia().getSucursalActual().agregarSolicitud(new Solicitud(cliente, TipoDeTarjeta.Simplicity, tarjeta.getSaldo(), cliente.getId()));
                        System.out.println("Su actualización de tarjeta ha sido solicitada");
                        cliente.haSolicitado = true;
                    }else{
                        System.out.println("No se ha realizado ninguna solicitud");
                    }
                }
                Tools.next();
            }

            case 2 -> {
                Tools.printHeader("ACTUALIZAR - SIMPLICITY");
                tarjeta = cliente.getSimplicity();

                if(tarjeta==null){
                    System.out.println("   * No hay ninguna tarjeta Simplicity registrada");
                }else if(tarjeta.getSaldo()<100000){
                    System.out.println("   * Se necesita de un saldo mayor o igual a $100,000 para");
                    System.out.println("     continuar con esta operación");
                }else{
                    System.out.println("   * Su tarjeta de débito puede ser actualizada a Platino");
                    System.out.println("   * Las solicitudes de actualización deben ser aprobadas por");
                    System.out.println("     un ejecutivo de cuenta o gerente, por lo que el tiempo");
                    System.out.println("     de espera es indefinido");
                    System.out.println("¿Desea solicitar una actualización de tarjeta? (s/n)");
                    System.out.print(">> ");
                    char yOrN = sc.nextLine().charAt(0);

                    if(Tools.AskForYesOrNo(yOrN)){
                        SucursalActual.getInstancia().getSucursalActual().agregarSolicitud(new Solicitud(cliente, TipoDeTarjeta.Platino, tarjeta.getSaldo(), cliente.getId()));
                        System.out.println("Su actualización de tarjeta ha sido solicitada");
                        cliente.haSolicitado = true;
                    }else{
                        System.out.println("No se ha realizado ninguna solicitud");
                    }
                }
                Tools.next();
            }

            case 3 -> {
                Tools.printHeader("ACTUALIZAR - PLATINO");
                tarjeta = cliente.getPlatino();

                if(tarjeta==null){
                    System.out.println("   * No hay ninguna tarjeta Platino registrada");
                }else if(tarjeta.getSaldo()<200000){
                    System.out.println("   * Se necesita de un saldo mayor o igual a $200,000 para");
                    System.out.println("     continuar con esta operación");
                }else{
                    System.out.println("   * Su tarjeta de débito puede ser actualizada a Oro");
                    System.out.println("   * Las solicitudes de actualización deben ser aprobadas por");
                    System.out.println("     un ejecutivo de cuenta o gerente, por lo que el tiempo");
                    System.out.println("     de espera es indefinido");
                    System.out.println("¿Desea solicitar una actualización de tarjeta? (s/n)");
                    System.out.print(">> ");
                    char yOrN = sc.nextLine().charAt(0);

                    if(Tools.AskForYesOrNo(yOrN)){
                        SucursalActual.getInstancia().getSucursalActual().agregarSolicitud(new Solicitud(cliente, TipoDeTarjeta.Oro, tarjeta.getSaldo(), cliente.getId()));
                        System.out.println("Su actualización de tarjeta ha sido solicitada");
                        cliente.haSolicitado = true;
                    }else{
                        System.out.println("No se ha realizado ninguna solicitud");
                    }
                }
                Tools.next();
            }
        }
    }

    private static void mostrarInformacion(){
        Tools.printHeader("MOSTRAR INFORMACIÓN");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Mostrar información propia");
        System.out.println("2. Mostrar información general");
        System.out.println("3. Volver al menú anterior");
        System.out.print(">> ");
        int opc = Tools.nextInt();
        switch (opc) {
            case 1 -> {
                Tools.printHeader("MOSTRAR - INFORMACIÓN PROPIA");
                System.out.println(usuarioEnSesion.getUsuarioActual().toString());
                Tools.next();
            }
            case 2 -> {
                Tools.printHeader("MOSTRAR - INFORMACIÓN GENERAL");
                System.out.println("SUCURSAL: " + sucursalActual.getSucursalActual().getNombre().toString().toUpperCase());
                System.out.println("Usuario actual: " + usuarioEnSesion.getUsuarioActual().getNombreCompleto());
                System.out.println("---------------------------------------------------------------");
                sucursalActual.getSucursalActual().imprimirUsuarios();
                System.out.println("---------------------------------------------------------------");
                Tools.next();
            }
        }
    }
}
