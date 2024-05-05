package Usuarios;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Usuarios.util.*;
import Usuarios.util.Tarjeta.*;
import Sistema.util.*;

// 1 débito y 3 crédito como límite

public class Cliente extends Usuario {
    private static Scanner sc = new Scanner(System.in);
    private String rfc;
    private String curp;
    private String direccion;
    private LocalDate fechaRegistro;
    private HashMap<TipoDeTarjeta, ArrayList<Tarjeta>> tarjetas = new HashMap<>();

    public Cliente(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado,
            boolean eshombre, String nombreUsuario, String contraseña, String direccion, NombreSucursal sucursal) {
        super(nombre, apellidos, fechaDeNacimiento, ciudad, estado, eshombre, nombreUsuario, contraseña, sucursal);
        this.direccion = direccion;
        this.fechaRegistro = LocalDate.now();
        this.sucursal = sucursal;
        this.curp = Generators.GenerateCURP(nombre, apellidos, fechaRegistro, eshombre, estado);
        tarjetas.put(TipoDeTarjeta.Simplicity, new ArrayList<Tarjeta>());
        tarjetas.put(TipoDeTarjeta.Platino, new ArrayList<Tarjeta>());
        tarjetas.put(TipoDeTarjeta.Oro, new ArrayList<Tarjeta>());
    }

    public void agregarTarjeta(TipoDeTarjeta tipo, Tarjeta tarjeta){
        this.tarjetas.get(tipo).add(tarjeta);
    }

    @Override
    public String toString() {
        return String.format("%s   * RFC: %s\n   * CURP: %s\n   * Dirección: %s\n   * Fecha de registro: %s\n", super.toString() , this.rfc, this.curp, this.direccion, this.fechaRegistro.toString());
    }

    public static void registrarCliente(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ArrayList<String> datosComun = DatosComun.obtenerDatosComun();
        String nombre = datosComun.get(0);
        String apellido = datosComun.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComun.get(2), format);
        String ciudad = datosComun.get(4);
        String estado = datosComun.get(5);
        boolean eshombre = datosComun.get(6).equalsIgnoreCase("H");
        String nombreUsuario = datosComun.get(7);
        String contrasena = datosComun.get(8);
        System.out.println("Ingrese su direccion: ");
        System.out.print(">> ");
        String direccion = sc.nextLine();
        NombreSucursal sucursal = SucursalActual.getInstancia().getSucursalActual().getNombre();

        Cliente cliente = new Cliente(nombre, apellido, fechaNacimiento, ciudad, estado, eshombre, nombreUsuario, contrasena, direccion, sucursal);
        SucursalActual.getInstancia().getSucursalActual().agregarUsuario(Rol.Cliente, cliente);

        System.out.println("\n== CLIENTE REGISTRADO EXITOSAMENTE! ==\n");
    }

    public static void imprimirClientes(){
        //TODO
    }

}
