package ecommerce.modelo;

import java.util.ArrayList;
import java.util.Date;

public class TecOrden {
	private int ordId;
	private String ordFcreacion;
	private Integer ordNumConfirmacion;
	private Integer ordPrecioTotal;
	public TecCliente cli;
	public ArrayList<TecProductoTecOrden> tecProductoTecOrden = new ArrayList<TecProductoTecOrden>();

    public TecOrden() {
    }

        
        
    public int getOrdId() {
        return ordId;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    public String getOrdFcreacion() {
        return ordFcreacion;
    }

    public void setOrdFcreacion(String ordFcreacion) {
        this.ordFcreacion = ordFcreacion;
    }

    public Integer getOrdNumConfirmacion() {
        return ordNumConfirmacion;
    }

    public void setOrdNumConfirmacion(Integer ordNumConfirmacion) {
        this.ordNumConfirmacion = ordNumConfirmacion;
    }

    public Integer getOrdPrecioTotal() {
        return ordPrecioTotal;
    }

    public void setOrdPrecioTotal(Integer ordPrecioTotal) {
        this.ordPrecioTotal = ordPrecioTotal;
    }

    public TecCliente getCli() {
        return cli;
    }

    public void setCli(TecCliente cli) {
        this.cli = cli;
    }

    public ArrayList<TecProductoTecOrden> getTecProductoTecOrden() {
        return tecProductoTecOrden;
    }

    public void setTecProductoTecOrden(ArrayList<TecProductoTecOrden> tecProductoTecOrden) {
        this.tecProductoTecOrden = tecProductoTecOrden;
    }
        
        
}