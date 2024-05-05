package Usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Sistema.util.Designer;
import Sistema.util.Generators;
import Sistema.util.NombreSucursal;
import Sistema.util.SucursalActual;
import Usuarios.util.*;

public class Empleado extends Usuario {
    // REQUIEREN DE UNA SUCURSAL
    private static Scanner sc = new Scanner(System.in);
    private String rfc;
    private String curp;
    private String direccion;
    private double salario;
    private Rol rol;
    private LocalDate inicioTrabajo;

    public Empleado(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado,
            boolean eshombre, String nombreUsuario, String contraseña, String direccion, NombreSucursal sucursal, double salario, Rol rol) {
        super(nombre, apellidos, fechaDeNacimiento, ciudad, estado, eshombre, nombreUsuario, contraseña, sucursal);
        this.rfc = Generators.GenerateRFC(nombre, apellidos, fechaDeNacimiento);
        this.curp = Generators.GenerateCURP(nombre, apellidos, fechaDeNacimiento, eshombre, estado);
        this.direccion = direccion;
        this.salario = salario;
        this.rol = rol;
        this.inicioTrabajo = LocalDate.now();
    } 

    @Override
    public String toString() {
        return String.format("%s   * RFC: %s\n   * CURP: %s\n   * Dirección: %s\n   * Salario: %f\n   * Inicio de trabajo: %s\n   * Rol: %s", super.toString() , this.rfc, this.curp, this.direccion, this.salario, this.inicioTrabajo.toString(), this.rol == Rol.Capturista ? "Capturista" : this.rol == Rol.Ejecutivo ? "Ejecutivo" : "Gerente");
    }

    public static void registrarEmpleado(){
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
        double salario = Designer.nextDouble();
        int opc = 1;
        Rol rol = Rol.Capturista;

        while(opc>0 && opc<3){
            System.out.println("Ingrese el rol del trabajador: ");
            System.out.println("1. Ejecutivo");
            System.out.println("2. Capturista");
            System.out.print(">> ");
            opc = Designer.nextInt();
            switch (opc) {
                case 1 -> rol = Rol.Ejecutivo;
                default -> rol = Rol.Capturista;
            }
        }

        NombreSucursal sucursal = SucursalActual.getInstancia().getSucursalActual().getNombre();

        Empleado empleado = new Empleado(nombre, apellido, fechaNacimiento, ciudad, estado, eshombre, nombreUsuario, contrasena, direccion, sucursal, salario, rol);
        SucursalActual.getInstancia().getSucursalActual().agregarUsuario(rol, empleado);

        System.out.println("\n== CLIENTE REGISTRADO EXITOSAMENTE! ==\n");
    }
}
