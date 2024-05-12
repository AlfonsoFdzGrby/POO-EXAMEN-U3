package Usuarios.util.Tarjeta;

import java.time.LocalDate;

import Sistema.util.SucursalActual;
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

    public int getId(){
        return idCliente;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public StatusDeSolicitud getStatus(){
        return status;
    }

    public void rechazarSolicitud(){
        this.status = StatusDeSolicitud.Rechazada;
        System.out.println("\nSE HA RECHAZADO LA SOLICITUD CORRECTAMENTE!\n");
    }

    public void aceptarSolicitud(int index){
        TipoDeTarjeta tipoTarjeta = SucursalActual.getInstancia().getSucursalActual().solicitudesActualizacion.get(index).tipoDeTarjeta;
        SucursalActual.getInstancia().getSucursalActual().solicitudesActualizacion.get(index)
        .cliente.agregarTarjeta(tipoTarjeta, new Tarjeta(tipoTarjeta, (tipoTarjeta == TipoDeTarjeta.Simplicity) ? 60000 :
        (tipoTarjeta == TipoDeTarjeta.Platino) ? 150000 : 400000  , SucursalActual.getInstancia().getSucursalActual().getNombre()));
        System.out.println("\nSE HA ACEPTADO LA SOLICITUD CORRECTAMENTE!\n");
    }

    public String toString(){
        return String.format("Cliente: %s\n * Tipo de tarjeta: %s\n * Status de solicitud: %s\n Id del Cliente: %d\n",this.cliente,this.tipoDeTarjeta.toString(),this.status.toString(),this.idCliente);
    }
    
    public static void printSolicitudes(){
        if (SucursalActual.getInstancia().getSucursalActual().solicitudesActualizacion.isEmpty()) {
            System.out.println("\nNO EXISTEN SOLICITUDES ACTUALMENTE!!\n");
        } else {
            for (Solicitud solicitud : SucursalActual.getInstancia().getSucursalActual().solicitudesActualizacion) {
                System.out.println(solicitud.toString());
            }
        }
    }
    
}