package Sistema;

import Sistema.util.*;

public class Sistema {
    Sucursal acueducto = new Sucursal(NombreSucursal.Acueducto);
    Sucursal madero = new Sucursal(NombreSucursal.Madero);

    public Sucursal getAcueducto() {
        return acueducto;
    }

    public Sucursal getMadero() {
        return madero;
    }

}
