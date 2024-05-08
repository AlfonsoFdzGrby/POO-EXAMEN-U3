package Usuarios.util;

import Sistema.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class DatosComun {
    private static Scanner sc = new Scanner(System.in);
    
    public static ArrayList<String> obtenerDatosComun(){
        LocalDate fechaNacimiento = LocalDate.now();
        boolean flag = true;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Tools.printHeader("INGRESE LOS DATOS");
        System.out.println("Ingrese su nombre: ");
        System.out.print(">> ");
        String nombre = sc.next();
        System.out.println("Ingrese su apellido: ");
        System.out.print(">> ");
        String apellido = sc.next();
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
        String estado = sc.next();
        System.out.println("Ingrese su genero: (H/M)");
        System.out.print(">> ");
        char gen = sc.nextLine().charAt(0);
        String genero = Character.toString(gen);
        String username = conseguirUsername();
        System.out.println("Ingrese su contrasena: ");
        System.out.print(">> ");
        String password = sc.next();
        ArrayList<String> datosComun = new ArrayList<>();
        datosComun.add(nombre);
        datosComun.add(apellido);
        datosComun.add(fechaNacimiento.toString());
        datosComun.add(ciudad);
        datosComun.add(estado);
        datosComun.add(genero);
        datosComun.add(username);
        datosComun.add(password);

        return datosComun;
    }

    private static String conseguirUsername(){
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
