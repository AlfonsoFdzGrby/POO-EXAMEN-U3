package Sistema.util;

public class SucursalActual {
    private static SucursalActual instancia;
    private Sucursal sucursalActual;

    private SucursalActual(){}

    public static SucursalActual getInstancia(){
        if(instancia==null){
            instancia = new SucursalActual();
        }
        return instancia;
    }

    public Sucursal getSucursalActual(){
        return sucursalActual;
    }

    public void setSucursal(Sucursal sucursalActual){
        this.sucursalActual = sucursalActual;
    }
}
