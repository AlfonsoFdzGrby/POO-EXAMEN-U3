package Usuarios.util;

import java.time.LocalDate;

public abstract class Usuario {
    protected String nombre;
    protected String apellidos;
    protected LocalDate fechaDeNacimiento;
    protected String ciudad;
    protected String estado;
    protected boolean eshombre; //True: Hombre , False: Mujer

    public Usuario(String nombre, String apellidos, LocalDate fechaDeNacimiento, String ciudad, String estado, boolean eshombre) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.ciudad = ciudad;
        this.estado = estado;
        this.eshombre = eshombre;
    }

    
}