package Usuarios.util.Tarjeta;

import java.time.LocalDate;

import Usuarios.Cliente;

public class Solicitud {
    private Cliente cliente;
    private LocalDate fecha;
    private TipoDeTarjeta tipoDeTarjeta;
    private double fondos;
    private StatusDeSolicitud status;
    private int idCliente;

    public Solicitud(Cliente cliente, TipoDeTarjeta tipoDeTarjeta, double fondos, int idCliente) {
        this.cliente = cliente;
        this.tipoDeTarjeta = tipoDeTarjeta;
        this.fondos = fondos;
        this.idCliente = idCliente;
        this.status = StatusDeSolicitud.EnProceso;
        this.fecha = LocalDate.now();
    }
    
}