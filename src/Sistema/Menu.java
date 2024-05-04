package Sistema;

import Sistema.util.*;
import Usuarios.Cliente;
import Usuarios.util.Rol;
import Usuarios.util.Usuario;
import Usuarios.util.UsuarioEnSesion;

import java.time.LocalDate;
import java.util.*;

public class Menu {

    private static Scanner sc = new Scanner(System.in);
    private static Sistema sistema = new Sistema();
    private static SucursalActual sucursalActual = null;
    private static UsuarioEnSesion usuarioEnSesion = null;

    public static void ejecutarMenu(){
        Sucursal sucursal = null;
        int opc = 1;
        while(opc>0 && opc<3){
            Designer.printHeader("¡BIENVENIDO A POOBANK!");
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
                    menuSucursal();
                }
                case 2 -> {
                    sucursal = sistema.getMadero();
                    sucursalActual = SucursalActual.getInstancia();
                    sucursalActual.setSucursal(sucursal);
                    menuSucursal();
                }
            }
        }
    }

    private static void menuSucursal(){
        sucursalActual.getSucursalActual().agregarUsuario(Rol.Cliente, new Cliente("Pedro", "Fernandez Carreón", LocalDate.of(2000, 2, 25), "Morelia", "Michoacán", true, "PeFeCa", "12345", "Calle xd", NombreSucursal.Acueducto));
        Designer.printHeader("SUCURSAL " + sucursalActual.getSucursalActual().getNombre().toString().toUpperCase());
        System.out.println("Por favor inicie sesión");
        Usuario usuario = Usuario.buscarUsuario();

        int intentos = 3;

        while (true) {
            if(intentos<=0){
                System.out.println("Sus intentos se han agotado");
                Designer.next();
                break;
            }

            System.out.println("Ingrese su contraseña");
            System.out.println("TIENE " + intentos + " INTENTOS");
            System.out.println(">> ");
            String contraseña = sc.nextLine();

            if(contraseña.equals(usuario.getContraseña())){
                System.out.println("Contraseña correcta");
                usuarioEnSesion = UsuarioEnSesion.getInstancia();
                usuarioEnSesion.setUsuario(usuario);
                System.out.println("Se ha iniciado sesión correctamente con el usuario " + usuarioEnSesion.getUsuarioActual().getNombreUsuario());
                Designer.next();
                break;
            }else{
                System.out.println("Contraseña incorrecta");
                intentos--;
            }
        }

    }
}
