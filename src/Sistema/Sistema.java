package Sistema;

import java.util.ArrayList;

import Sistema.util.*;

public class Sistema {
    private static ArrayList<Sucursal> sucursales = new ArrayList<>();
    Sucursal acueducto = new Sucursal(NombreSucursal.Acueducto);
    Sucursal madero = new Sucursal(NombreSucursal.Madero);
    
    public Sucursal getAcueducto() {
        return acueducto;
    }

    public Sucursal getMadero() {
        return madero;
    }

}
