/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.daopersistencia.dao;


import ecommerce.modelo.TecProducto;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public interface TecProductoDao {
    
    public TecProducto buscar(String nombre);

    public TecProducto buscar(int idPro);

    public ArrayList<TecProducto> listar();

    public boolean guardar(TecProducto pro);

    public boolean editar(TecProducto pro);

    public boolean borrar(int id);
}
