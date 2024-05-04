package Sistema.util;

import java.util.HashMap;
import Usuarios.util.Tarjeta.Tarjeta;
import Usuarios.util.Tarjeta.TipoDeTarjeta;

public class Sucursal {
    NombreSucursal nombre;
    HashMap<TipoDeTarjeta, Tarjeta> tarjetas = new HashMap<>();

    public Sucursal(NombreSucursal nombre /*, HashMap<TipoDeTarjeta, Tarjeta> tarjetas*/) {
        this.nombre = nombre;
        //this.tarjetas = tarjetas;
    }

    public NombreSucursal getNombre() {
        return nombre;
    }
}
