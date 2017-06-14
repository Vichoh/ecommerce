package ecommerce.modelo;

import java.util.ArrayList;

public class TecCategoria {
	private int catId;
	private String catNombre;
	public ArrayList<TecProducto> tecProducto = new ArrayList<TecProducto>();

        public TecCategoria() {
        }

        
	public int getCatId() {
		return this.catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatNombre() {
		return this.catNombre;
	}

	public void setCatNombre(String catNombre) {
		this.catNombre = catNombre;
	}
}