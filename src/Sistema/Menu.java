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
        int opc = 1;
        while(opc<3 && opc>0){
            Tools.printHeader("CLIENTE");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear tarjeta (débito únicamente)");
            System.out.println("2. Consultar tarjetas");
            System.out.println("3. ");
            System.out.print(">> ");
            
            opc = Tools.nextInt();

            switch (opc) {
                case 1 -> crearTarjeta(false);
                case 2 -> consultarTarjetas(false);
            }
        }
        
    }

    private static void menuInversionista(){
        Tools.printHeader("INVERSIONISTA");
        System.out.println("Seleccione una opción:");
    }

    private static void menuCapturista(){
        Tools.printHeader("CAPTURISTA");
        System.out.println("Seleccione una opción:");
    }

    private static void menuEjecutivo(){
        Tools.printHeader("EJECUTIVO");
        System.out.println("Seleccione una opción:");
    }

    private static void menuGerente(){
        Tools.printHeader("GERENTE");
        System.out.println("Seleccione una opción:");
    }

    // ---------------------------------------- MENÚS FUNCIONES ----------------------------------------

    private static void crearTarjeta(boolean asksforUser){
        Tools.printHeader("CREAR TARJETA");
        Cliente cliente = null;
        if(asksforUser){
            while(true){
                Usuario usuario = Usuario.buscarUsuario();
                if(usuario instanceof Cliente){
                    cliente = (Cliente)usuario;
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
    
}
