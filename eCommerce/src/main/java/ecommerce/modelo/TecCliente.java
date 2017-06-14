package ecommerce.modelo;

import java.util.ArrayList;

public class TecCliente {
	private int cliId;
	private String cliNombres;
	private String cliApellidos;
	private String cliTelefono;
	private String cliDireccion;
	private String cliComuna;
	public ArrayList<TecOrden> tecOrden = new ArrayList<TecOrden>();

    public TecCliente() {
    }
        
        

    public int getCliId() {
        return cliId;
    }

    public void setCliId(int cliId) {
        this.cliId = cliId;
    }

    public String getCliNombres() {
        return cliNombres;
    }

    public void setCliNombres(String cliNombres) {
        this.cliNombres = cliNombres;
    }

    public String getCliApellidos() {
        return cliApellidos;
    }

    public void setCliApellidos(String cliApellidos) {
        this.cliApellidos = cliApellidos;
    }

    public String getCliTelefono() {
        return cliTelefono;
    }

    public void setCliTelefono(String cliTelefono) {
        this.cliTelefono = cliTelefono;
    }

    public String getCliDireccion() {
        return cliDireccion;
    }

    public void setCliDireccion(String cliDireccion) {
        this.cliDireccion = cliDireccion;
    }

    public String getCliComuna() {
        return cliComuna;
    }

    public void setCliComuna(String cliComuna) {
        this.cliComuna = cliComuna;
    }

    public ArrayList<TecOrden> getTecOrden() {
        return tecOrden;
    }

    public void setTecOrden(ArrayList<TecOrden> tecOrden) {
        this.tecOrden = tecOrden;
    }
        
        
}