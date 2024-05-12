package Usuarios.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Sistema.util.*;
import Sistema.util.SucursalActual;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Inversionista;

public abstract class Usuario {

    private static Scanner sc = new Scanner(System.in);

    protected String nombre;
    protected String apellidos;
    protected LocalDate fechaDeNacimiento;
    protected String ciudad;
    protected String estado;
    protected boolean eshombre; //True: Hombre , False: Mujer
    protected String nombreUsuario;
    protected String contraseña;
    protected NombreSucursal sucursal;
    protected Rol rol;

    public Usuario(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado, 
    boolean eshombre, String nombreUsuario, String contraseña, Rol rol, NombreSucursal sucursal) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.ciudad = ciudad;
        this.estado = estado;
        this.eshombre = eshombre;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.sucursal = sucursal;
        this.rol = rol;
    }

    @Override
    public String toString() {
        return String.format("   * Nombre del usuario: %s\n   * Fecha de nacimiento: %s\n   * Ciudad y Estado: %s\n   * Género: %s\n   * Nombre de usuario: %s\n", this.nombreUsuario, this.fechaDeNacimiento.toString(), this.ciudad + ", " + this.estado, eshombre ? "Hombre" : "Mujer", this.nombreUsuario);
    }

    public String getNombreCompleto(){
        return nombre + " " + apellidos;
    }

    public String getNombreUsuario(){
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public NombreSucursal getSucursal(){
        return sucursal;
    }

    public static Usuario buscarUsuario(){
        Usuario usuario = null;
        while(true){
            System.out.print("Ingrese el nombre de usuario: ");
            String nombreUsuario = sc.nextLine();

            for (ArrayList<Usuario> usuarios : SucursalActual.getInstancia().getSucursalActual().usuarios.values()) {
                Sucursal sucursal = SucursalActual.getInstancia().getSucursalActual();
                
                for (Usuario usuarioABuscar : usuarios) {
                    if((usuarioABuscar.getNombreUsuario().equals(nombreUsuario) && usuarioABuscar.getSucursal().equals(sucursal.getNombre()))){
                        usuario = usuarioABuscar;
                    }else if(sucursal.getGerente().getNombreUsuario().equals(nombreUsuario)){
                        usuario = sucursal.getGerente();
                    }
                }
            }

            if (usuario!=null) {
                System.out.println("¡Usuario encontrado!");
                break;
            } else {
                System.out.println("Usuario no encontrado");
            }
        }
        return usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setNombreUsuario(){
        String username = DatosComun.conseguirUsername();
        this.nombreUsuario = username;
        System.out.println("SE HA CAMBIADO EL NOMBRRE DE USUARIO CORRECTAMENTE!");
        Tools.next();
    }

    public void setContrasena(){
        System.out.println("Ingrese la nueva contrasena del usuario: ");
        System.out.print(">> ");
        String contrasena = sc.nextLine();
        this.contraseña = contrasena;
        System.out.println("SE HA CAMBIADO LA CONTRASENA CORRECTAMENTE!");
        Tools.next();
    }

    public static void modificarUsuario(Rol rol){
        Tools.printHeader("MODIFICAR USUARIO");

        if (rol.equals(Rol.Inversionista)) {
            System.out.println("Al modificar inversionistas es necesario ingresar la contraseña de la sucursal");
            System.out.println("Ingresela porfavor: ");
            System.out.print(">> ");
            String password = sc.nextLine();
            if (password.equals(SucursalActual.getInstancia().getSucursalActual().getSuperPassword())) {
                System.out.println("Contrasena ingresada correctamente, puede modificar los inversionistas!");
                Tools.next();
            } else {
                System.out.println("Ha ingresado la contrasena incorrecta, cerrando el menu de modificaciones");
                return;
            }
        }

        if(rol.equals(Rol.Cliente)){
            Cliente.printClientes();
        } else if (rol.equals(Rol.Ejecutivo) || rol.equals(Rol.Capturista)){
            Empleado.printEmpleados(rol);
        } else {
            Inversionista.printInversionistas();
        }    

        Usuario usuario = buscarUsuario();
        boolean flag = true;
        
        while (flag) {
            System.out.println("Que desea modificar del usuario: ");
            System.out.println("1. Nombre de usuario");
            System.out.println("2. Contraseña");
            System.out.println("3. Salir");
            System.out.print(">> ");

            int opt = Tools.nextInt();

            switch (opt) {
                case 1 -> usuario.setNombreUsuario();
                case 2 -> usuario.setContrasena();
                case 3 -> flag = false;
                default -> System.out.println("Por favor ingrese una opción válida");
            }    
        }
    }

    public static void borrarUsuario(Rol rol){
        Tools.printHeader("BORRAR USUARIO");

        if (rol.equals(Rol.Inversionista)) {
            System.out.println("Al eliminar inversionistas es necesario ingresar la contrasena de la sucursal");
            System.out.println("Ingresela porfavor: ");
            System.out.print(">> ");
            String password = sc.nextLine();
            if (password.equals(SucursalActual.getInstancia().getSucursalActual().getSuperPassword())) {
                System.out.println("Contrasena ingresada correctamente, puede eliminar los inversionistas");
                Tools.next();
            } else {
                System.out.println("Ha ingresado la contrasena incorrecta, cerrando el menu de eliminacion de inversionistas");
                return;
            }
        }

        if(rol.equals(Rol.Cliente)){
            Cliente.printClientes();
        } else if (rol.equals(Rol.Ejecutivo) || rol.equals(Rol.Capturista)){
            Empleado.printEmpleados(rol);
        } else {
            Inversionista.printInversionistas();
        }

        Usuario usuario = buscarUsuario();

        System.out.println("De verdad desea borrar el usuario: (S/N)");
        System.out.print(">> ");
        char opt = sc.nextLine().charAt(0);

        if (Tools.AskForYesOrNo(opt)) {
            SucursalActual.getInstancia().getSucursalActual().usuarios.get(rol).remove(usuario);
            System.out.println("\nSE HA BORRADO CORRECTAMENTE AL USUARIO\n");
        } else {
            System.out.println("\nNO SE HA BORRADO AL USUARIO\n");
        }
        Tools.next();
    }
}