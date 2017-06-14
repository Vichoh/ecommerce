/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.daopersistencia.dao;

import ecommerce.modelo.TecCategoria;
import ecommerce.modelo.TecOrden;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public interface TecordenDao {
    
    
    public TecOrden buscar(String nombre);

    public TecOrden buscar(int idord);

    public ArrayList<TecOrden> listar();

    public boolean guardar(TecOrden ord);

    public boolean editar(TecOrden ord);

    public boolean borrar(int id);
}
