package Usuarios.util;

import Sistema.Sistema;
import Sistema.util.*;

import java.time.*;
import java.util.*;

public class DatosComun {
    private static Scanner sc = new Scanner(System.in);
    
    public static ArrayList<String> obtenerDatosComun(){
        LocalDate fechaNacimiento = LocalDate.now();
        String nombre = null;
        String apellido = null;
        Tools.printHeader("INGRESE LOS DATOS");

        while(true){
            System.out.println("Ingrese su nombre: ");
            System.out.print(">> ");
            nombre = sc.nextLine();
            System.out.println("Ingrese su primer apellido: ");
            System.out.print(">> ");
            String apellido1 = sc.nextLine();
            System.out.println("Ingrese su segundo apellido: ");
            System.out.print(">> ");
            String apellido2 = sc.nextLine();
            apellido = apellido1 + " " + apellido2;
            Sucursal acueducto = Sistema.getAcueductoStatic();
            Sucursal madero = Sistema.getMaderoStatic();
            boolean flag = true;
            for (ArrayList<Usuario> Acusers : acueducto.usuarios.values()) {
                for (Usuario Acusuario : Acusers) {
                    if((nombre + " " + apellido).equals(Acusuario.getNombreCompleto()) || acueducto.getGerente().getNombreCompleto().equals(nombre + " " + apellido)){
                        System.out.println("El usuario ya se encuentra registrado en la sucursal Acueducto");
                        flag = false;
                        break;
                    }
                }
                if(!flag){
                    break;
                }
            }

            for (ArrayList<Usuario> Madusers : madero.usuarios.values()) {
                for (Usuario Madusuario : Madusers) {
                    if((nombre + " " + apellido).equals(Madusuario.getNombreCompleto()) || madero.getGerente().getNombreCompleto().equals(nombre + " " + apellido)){
                        System.out.println("El usuario ya se encuentra registrado en la sucursal Madero");
                        flag = false;
                        break;
                    }
                }
                if(!flag){
                    break;
                }
            }
            
            if(flag){
                break;
            }
        }
       

        System.out.println("Ingrese su fecha de nacimiento:");
        fechaNacimiento = Tools.askForDate();
        
        System.out.println("Ingrese su ciudad de residencia: ");
        System.out.print(">> ");
        String ciudad = sc.nextLine();
        System.out.println("Ingrese su estado de residencia: ");
        System.out.print(">> ");
        String estado = sc.nextLine();
        System.out.println("Ingrese su genero: (H/M)");
        System.out.print(">> ");
        char gen = sc.nextLine().charAt(0);
        String genero = Character.toString(gen);
        String username = conseguirUsername();
        System.out.println("Ingrese su contrasena: ");
        System.out.print(">> ");
        String password = sc.nextLine();
        ArrayList<String> datosComun = new ArrayList<>();
        datosComun.addAll(Arrays.asList(nombre, apellido, fechaNacimiento.toString(), ciudad, estado, genero, username, password));

        return datosComun;
    }

    public static String conseguirUsername(){
        String username = "";
        
        while (true) {
            boolean flag = false;
            System.out.println("Ingrese su nombre de usuario: ");
            System.out.print(">> ");
            username = sc.nextLine();
            for (ArrayList<Usuario> usuarios : SucursalActual.getInstancia().getSucursalActual().usuarios.values()) {
                for (Usuario usuarioABuscar : usuarios) {
                    if(usuarioABuscar.getNombreUsuario().equals(username)){
                        System.out.println("Nombre de usuario ya registrado en el sistema!");
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    break;
                }
            }
            if(!flag){
                break;
            }
        }
        return username;
    }
}
