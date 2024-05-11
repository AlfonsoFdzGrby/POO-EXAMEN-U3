package Usuarios.util;

import Sistema.util.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class DatosComun {
    private static Scanner sc = new Scanner(System.in);
    
    public static ArrayList<String> obtenerDatosComun(){
        LocalDate fechaNacimiento = LocalDate.now();
        boolean flag = true;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Tools.printHeader("INGRESE LOS DATOS");
        System.out.println("Ingrese su nombre: ");
        System.out.print(">> ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese su primer apellido: ");
        System.out.print(">> ");
        String apellido1 = sc.nextLine();
        System.out.println("Ingrese su segundo apellido: ");
        System.out.print(">> ");
        String apellido2 = sc.nextLine();
        String apellido = apellido1 + " " + apellido2;

        while (flag) {
            System.out.println("Ingrese su fecha de nacimiento: (dd-MM-yyyy)");
            System.out.print(">> ");
            try{
                fechaNacimiento = LocalDate.parse(sc.next(), format);
                flag = false;
            } catch (DateTimeParseException e) {
                System.out.println("Ingreso incorrectamente la fecha");
            }
        }

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
        boolean flag = true;
        String username = "";
        while (flag) {
            System.out.println("Ingrese su nombre de usuario: ");
            System.out.print(">> ");
            username = sc.next();
            for (ArrayList<Usuario> usuarios : SucursalActual.getInstancia().getSucursalActual().usuarios.values()) {
                for (Usuario usuarioABuscar : usuarios) {
                    if(usuarioABuscar.getNombreUsuario().equals(username)){
                        System.out.println("Nombre de usuario ya registrado en el sistema!");
                    }
                }
            }
        }
        return username;
    }
}
