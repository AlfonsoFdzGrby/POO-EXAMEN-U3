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
    private static int nextID = 0;
    private int id;
    private String rfc;
    private String curp;
    private String direccion;
    private LocalDate fechaRegistro;
    private HashMap<TipoDeTarjeta, Tarjeta> tarjetas = new HashMap<>();
    private boolean tieneCredito = false;
    public boolean haSolicitado = false;

    public Cliente(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado,
            boolean eshombre, String nombreUsuario, String contraseña, String direccion, NombreSucursal sucursal) {
        super(nombre, apellidos, fechaDeNacimiento, ciudad, estado, eshombre, nombreUsuario, contraseña, Rol.Cliente, sucursal);
        this.direccion = direccion;
        this.fechaRegistro = LocalDate.now();
        this.sucursal = sucursal;
        this.curp = Generators.GenerateCURP(nombre, apellidos, fechaRegistro, eshombre, estado);
        this.rfc = Generators.GenerateRFC(nombre, apellidos, fechaDeNacimiento);
        this.id = nextID;
        nextID++;
        tarjetas.put(TipoDeTarjeta.Simplicity, null);
        tarjetas.put(TipoDeTarjeta.Platino, null);
        tarjetas.put(TipoDeTarjeta.Oro, null);
        tarjetas.put(TipoDeTarjeta.Debito, null);

        //Ingresa una tarjeta inicializada en ceros
        tarjetas.put(TipoDeTarjeta.Debito, new Tarjeta(TipoDeTarjeta.Debito, 0, sucursal));
    }

    public void agregarTarjeta(TipoDeTarjeta tipo, Tarjeta tarjeta){
        tarjetas.put(tipo, tarjeta);
    }

    @Override
    public String toString() {
        return String.format("%s   * RFC: %s\n   * CURP: %s\n   * Dirección: %s\n   * Fecha de registro: %s\n", super.toString() , this.rfc, this.curp, this.direccion, this.fechaRegistro.toString());
    }

    public static void registrarCliente(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<String> datosComun = DatosComun.obtenerDatosComun();
        String nombre = datosComun.get(0);
        String apellido = datosComun.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComun.get(2), format);
        String ciudad = datosComun.get(3);
        String estado = datosComun.get(4);
        boolean eshombre = datosComun.get(5).equalsIgnoreCase("H");
        String nombreUsuario = datosComun.get(6);
        String contrasena = datosComun.get(7);
        System.out.println("Ingrese su direccion: ");
        System.out.print(">> ");
        String direccion = sc.nextLine();
        NombreSucursal sucursal = SucursalActual.getInstancia().getSucursalActual().getNombre();

        Cliente cliente = new Cliente(nombre, apellido, fechaNacimiento, ciudad, estado, eshombre, nombreUsuario, contrasena, direccion, sucursal);
        SucursalActual.getInstancia().getSucursalActual().agregarUsuario(Rol.Cliente, cliente);

        System.out.println("\n== EL CLIENTE FUE REGISTRADO EXITOSAMENTE! ==\n");
        Tools.next();
    }

    public int getId() {
        return id;
    }

    public static void printClientes(){
        Tools.printHeader("CLIENTES REGISTRADOS EN LA SUCURSAL");
        for (Usuario cliente : SucursalActual.getInstancia().getSucursalActual().usuarios.get(Rol.Cliente)) {
            System.out.println(cliente.toString());
        }
    }

    public void verSolicitudes(){
        for (Solicitud solicitud : SucursalActual.getInstancia().getSucursalActual().solicitudesActualizacion) {
            if (solicitud.getCliente().nombreUsuario == this.nombreUsuario) {
                System.out.print(solicitud.toString());
            }
        }
    }

    // ---------------------------------------- TARJETAS: GETTERS ----------------------------------------------

    public Tarjeta getDebito(){
        return this.tarjetas.get(TipoDeTarjeta.Debito);
    }

    public Tarjeta getSimplicity(){
        return this.tarjetas.get(TipoDeTarjeta.Simplicity);
    }

    public Tarjeta getPlatino(){
        return this.tarjetas.get(TipoDeTarjeta.Platino);
    }

    public Tarjeta getOro(){
        return this.tarjetas.get(TipoDeTarjeta.Oro);
    }

    public HashMap<TipoDeTarjeta, Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public boolean getTieneCredito(){
        return this.tieneCredito;
    }

    // ---------------------------------------- TARJETAS: SETTERS ----------------------------------------------

    public void setTarjeta(TipoDeTarjeta tipo, Tarjeta tarjeta){
        this.tarjetas.put(tipo, tarjeta);
    }

    public void setTieneCredito(boolean flag){
        this.tieneCredito = flag;
    }

    // ---------------------------------------- MUESTREO DE TARJETAS ----------------------------------------------

    public void mostrarTarjetas(){
        System.out.println("TARJETAS DE " + getNombreCompleto().toUpperCase());
        
        System.out.println("DÉBITO");
        if(tarjetas.get(TipoDeTarjeta.Debito)==null){
            System.out.println("   * No hay una tarjeta de débito registrada");
        }else{
            System.out.println("   * " + tarjetas.get(TipoDeTarjeta.Debito).toString());
        }
        
        System.out.println("CRÉDITO");

        if(tarjetas.get(TipoDeTarjeta.Simplicity)==null){
            System.out.println("   * No hay una tarjeta Simplicity registrada");
        }else{
            System.out.println("   * " + tarjetas.get(TipoDeTarjeta.Simplicity).toString());
        }

        if(tarjetas.get(TipoDeTarjeta.Platino)==null){
            System.out.println("   * No hay una tarjeta Platino registrada");
        }else{
            System.out.println("   * " + tarjetas.get(TipoDeTarjeta.Platino).toString());
        }

        if(tarjetas.get(TipoDeTarjeta.Oro)==null){
            System.out.println("   * No hay una tarjeta Oro registrada");
        }else{
            System.out.println("   * " + tarjetas.get(TipoDeTarjeta.Oro).toString());
        }
    }

}
