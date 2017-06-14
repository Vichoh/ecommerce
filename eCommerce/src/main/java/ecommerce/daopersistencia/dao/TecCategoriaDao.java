package ecommerce.daopersistencia.dao;

import ecommerce.modelo.TecCategoria;
import java.util.ArrayList;

public interface TecCategoriaDao {

    public TecCategoria buscar(String nombre);

    public TecCategoria buscar(int idCat);

    public ArrayList<TecCategoria> listar();

    public boolean guardar(TecCategoria cat);

    public boolean editar(TecCategoria cat);

    public boolean borrar(int id);
}
