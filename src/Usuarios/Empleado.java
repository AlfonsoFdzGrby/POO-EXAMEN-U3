package Usuarios;

import java.time.LocalDate;

import Sistema.util.Generators;
import Sistema.util.NombreSucursal;
import Usuarios.util.*;

public class Empleado extends Usuario {
    // REQUIEREN DE UNA SUCURSAL
    private String rfc;
    private String curp;
    private String direccion;
    private NombreSucursal sucursal;
    private double salario;
    private Rol rol;
    private LocalDate inicioTrabajo;

    public Empleado(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado,
            boolean eshombre, String nombreUsuario, String contraseña, String direccion, NombreSucursal sucursal, double salario, Rol rol) {
        super(nombre, apellidos, fechaDeNacimiento, ciudad, estado, eshombre, nombreUsuario, contraseña, sucursal);
        this.rfc = Generators.GenerateRFC(nombre, apellidos, fechaDeNacimiento);
        this.curp = Generators.GenerateCURP(nombre, apellidos, fechaDeNacimiento, eshombre, estado);
        this.direccion = direccion;
        this.sucursal = sucursal;
        this.salario = salario;
        this.rol = rol;
        this.inicioTrabajo = LocalDate.now();
    } 
}
