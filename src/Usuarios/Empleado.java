package Usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Sistema.util.Tools;
import Sistema.util.Generators;
import Sistema.util.NombreSucursal;
import Sistema.util.SucursalActual;
import Usuarios.util.*;
import Usuarios.util.Tarjeta.Solicitud;
import Usuarios.util.Tarjeta.StatusDeSolicitud;

public class Empleado extends Usuario {
    // REQUIEREN DE UNA SUCURSAL
    private static Scanner sc = new Scanner(System.in);
    private String rfc;
    private String curp;
    private String direccion;
    private double salario;
    private LocalDate inicioTrabajo;

    public Empleado(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado,
            boolean eshombre, String nombreUsuario, String contraseña, String direccion, NombreSucursal sucursal, double salario, Rol rol) {
        super(nombre, apellidos, fechaDeNacimiento, ciudad, estado, eshombre, nombreUsuario, contraseña, rol, sucursal);
        this.rfc = Generators.GenerateRFC(nombre, apellidos, fechaDeNacimiento);
        this.curp = Generators.GenerateCURP(nombre, apellidos, fechaDeNacimiento, eshombre, estado);
        this.direccion = direccion;
        this.salario = salario;
        this.inicioTrabajo = LocalDate.now();
    } 

    @Override
    public String toString() {
        return String.format("%s   * RFC: %s\n   * CURP: %s\n   * Dirección: %s\n   * Salario: %f\n   * Inicio de trabajo: %s\n   * Rol: %s", super.toString() , this.rfc, this.curp, this.direccion, this.salario, this.inicioTrabajo.toString(), this.rol == Rol.Capturista ? "Capturista" : this.rol == Rol.Ejecutivo ? "Ejecutivo" : "Gerente");
    }

    public static void registrarEjecutivo(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ArrayList<String> datosComun = DatosComun.obtenerDatosComun();
        String nombre = datosComun.get(0);
        String apellido = datosComun.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComun.get(2), format);
        String ciudad = datosComun.get(4);
        String estado = datosComun.get(5);
        boolean eshombre = datosComun.get(6).equalsIgnoreCase("hombre");
        String nombreUsuario = datosComun.get(7);
        String contrasena = datosComun.get(8);
        System.out.println("Ingrese su direccion: ");
        System.out.print(">> ");
        String direccion = sc.nextLine();
        System.out.println("Ingrese el salario del empleado: ");
        System.out.print(">> ");
        double salario = Tools.nextDouble();
        Rol rol = Rol.Ejecutivo;
        NombreSucursal sucursal = SucursalActual.getInstancia().getSucursalActual().getNombre();

        Empleado empleado = new Empleado(nombre, apellido, fechaNacimiento, ciudad, estado, eshombre, nombreUsuario, contrasena, direccion, sucursal, salario, rol);
        SucursalActual.getInstancia().getSucursalActual().agregarUsuario(rol, empleado);

        System.out.println("\n== EJECUTIVO REGISTRADO EXITOSAMENTE! ==\n");
    }

    public static void registrarCapturista(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ArrayList<String> datosComun = DatosComun.obtenerDatosComun();
        String nombre = datosComun.get(0);
        String apellido = datosComun.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComun.get(2), format);
        String ciudad = datosComun.get(4);
        String estado = datosComun.get(5);
        boolean eshombre = datosComun.get(6).equalsIgnoreCase("hombre");
        String nombreUsuario = datosComun.get(7);
        String contrasena = datosComun.get(8);
        System.out.println("Ingrese su direccion: ");
        System.out.print(">> ");
        String direccion = sc.nextLine();
        System.out.println("Ingrese el salario del empleado: ");
        System.out.print(">> ");
        double salario = Tools.nextDouble();
        Rol rol = Rol.Capturista;
        NombreSucursal sucursal = SucursalActual.getInstancia().getSucursalActual().getNombre();

        Empleado empleado = new Empleado(nombre, apellido, fechaNacimiento, ciudad, estado, eshombre, nombreUsuario, contrasena, direccion, sucursal, salario, rol);
        SucursalActual.getInstancia().getSucursalActual().agregarUsuario(rol, empleado);

        System.out.println("\n== EJECUTIVO REGISTRADO EXITOSAMENTE! ==\n");
    }

    public static void manejarSolicitudes(){
        Solicitud solicitud = null;
        int index = 0;
        Tools.printHeader("HA SELECCIONADO MANEJAR LAS SOLICITUDES");
        //printSolicitudes();
        while (true) {
            System.out.println("Ingrese la solicitud que desea manejar: (Ingrese el id del cliente)");
            System.out.print(">> ");
            int id = Tools.nextInt();
            index = Tools.encontrarSolicitud(id);

            if (index == -1) {
                System.out.println("ID NO ENCONTRADO, INGRESE UNO CORRECTO (-2 SI EL CODIGO FALLA)");
            } else if (index == -2){
                return;
            } else {
                System.out.println("Solicitud seleccionada correctamente...");
                Tools.next();
                break;
            }
        }
        solicitud = SucursalActual.getInstancia().getSucursalActual().solicitudesActualizacion.get(index);
        if (solicitud.getStatus().equals(StatusDeSolicitud.EnProceso)) {
            while (true) {
                System.out.println("Desea rechazar o aceptar la solicitud: ");
                System.out.print(">> ");
                String decision = sc.nextLine();

                if (decision.equalsIgnoreCase("aceptar")) {
                    solicitud.aceptarSolicitud(index);
                    Tools.next();
                    break;
                } else if (decision.equalsIgnoreCase("rechazar")) {
                    solicitud.rechazarSolicitud();
                    Tools.next();
                    break;
                } else {
                    System.out.println("Seleccione rechazar o aceptar por favor!\n");
                }
            }
        } else {
            System.out.println("\nEsta solicitud ya ha sido manejada!\n");
            Tools.next();
        }
    }
}
