package ecommerce.modelo;

import java.util.ArrayList;

public class TecProducto {
	private int proId;
	private String proNombre;
	private String proDescripcion;
	private Integer proPrecio;
	private String proUltimaActualizacion;
	public TecCategoria cat;
	public ArrayList<TecProductoTecOrden> tecProductoTecOrden = new ArrayList<TecProductoTecOrden>();

    public TecProducto() {
    }

        
        
        
    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProNombre() {
        return proNombre;
    }

    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    public String getProDescripcion() {
        return proDescripcion;
    }

    public void setProDescripcion(String proDescripcion) {
        this.proDescripcion = proDescripcion;
    }

    public Integer getProPrecio() {
        return proPrecio;
    }

    public void setProPrecio(Integer proPrecio) {
        this.proPrecio = proPrecio;
    }

    public String getProUltimaActualizacion() {
        return proUltimaActualizacion;
    }

    public void setProUltimaActualizacion(String proUltimaActualizacion) {
        this.proUltimaActualizacion = proUltimaActualizacion;
    }

    public TecCategoria getCat() {
        return cat;
    }

    public void setCat(TecCategoria cat) {
        this.cat = cat;
    }

    public ArrayList<TecProductoTecOrden> getTecProductoTecOrden() {
        return tecProductoTecOrden;
    }

    public void setTecProductoTecOrden(ArrayList<TecProductoTecOrden> tecProductoTecOrden) {
        this.tecProductoTecOrden = tecProductoTecOrden;
    }
        
        
        
}