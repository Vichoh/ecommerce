/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.daopersistencia.dao;


import ecommerce.modelo.TecCliente;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public interface TecClienteDao {
    
    
    public TecCliente buscar(String nombre);

    public TecCliente buscar(int idCli);

    public ArrayList<TecCliente> listar();

    public boolean guardar(TecCliente cli);

    public boolean editar(TecCliente cli);

    public boolean borrar(int id);
}
