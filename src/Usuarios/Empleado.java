package Usuarios;

import java.time.LocalDate;

import Sistema.util.Generators;
import Sistema.util.Sucursal;
import Usuarios.util.*;

public class Empleado extends Usuario {
    // REQUIEREN DE UNA SUCURSAL
    private String rfc;
    private String curp;
    private String direccion;
    private Sucursal sucursal;
    private double salario;
    private Rol rol;
    private LocalDate inicioTrabajo;

    public Empleado(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado,
            boolean eshombre, String direccion, Sucursal sucursal, double salario, Rol rol) {
        super(nombre, apellidos, fechaDeNacimiento, ciudad, estado, eshombre);
        this.rfc = Generators.GenerateRFC(nombre, apellidos, fechaDeNacimiento);
        this.curp = Generators.GenerateCURP(nombre, apellidos, fechaDeNacimiento, eshombre, estado);
        this.direccion = direccion;
        this.sucursal = sucursal;
        this.salario = salario;
        this.rol = rol;
        this.inicioTrabajo = LocalDate.now();
    } 
}
