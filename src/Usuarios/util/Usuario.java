package Usuarios.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Sistema.util.NombreSucursal;
import Sistema.util.Sucursal;
import Sistema.util.SucursalActual;

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

    public Usuario(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado, 
    boolean eshombre, String nombreUsuario, String contraseña, NombreSucursal sucursal) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.ciudad = ciudad;
        this.estado = estado;
        this.eshombre = eshombre;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.sucursal = sucursal;
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
            System.out.print("Ingrese su nombre de usuario: ");
            String nombreUsuario = sc.nextLine();

            for (ArrayList<Usuario> usuarios : SucursalActual.getInstancia().getSucursalActual().usuarios.values()) {
                for (Usuario usuarioABuscar : usuarios) {
                    if(usuarioABuscar.getNombreUsuario().equals(nombreUsuario)){
                        usuario = usuarioABuscar;
                    }
                }
            }

            if (usuario.sucursal.equals(SucursalActual.getInstancia().getSucursalActual().getNombre())) {
                System.out.println("¡Usuario encontrado!");
                break;
            } else {
                System.out.println("Usuario no encontrado!");
            }
        }
        return usuario;
    }
}