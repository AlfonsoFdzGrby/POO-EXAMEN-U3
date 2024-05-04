package Usuarios.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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

    public Usuario(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado, boolean eshombre, String nombreUsuario, String contraseña) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.ciudad = ciudad;
        this.estado = estado;
        this.eshombre = eshombre;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
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

    public static Usuario buscarUsuario(){
        Usuario usuario = null;
        while(true){
            System.out.print("Ingrese su nombre de usuario: ");
            String nombreUsuario = sc.nextLine();

            for (ArrayList<Usuario> usuarios : SucursalActual.) {
                for (Usuario usuarioABuscar : usuarios) {
                    if(usuarioABuscar.getNombreUsuario().equals(nombreUsuario)){
                        usuario = usuarioABuscar;
                    }
                }
            }

            if(usuario!=null){
                System.out.println("¡Usuario encontrado!");
                break;
            }else{
                System.out.println("Usuario no encontrado");
            }
        }
        return usuario;
    }
}