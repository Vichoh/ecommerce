/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.daopersistencia.impl;

import ecommerce.daopersistencia.dao.TecClienteDao;
import ecommerce.daopersistencia.factory.MysqlDaoFactory;
import ecommerce.modelo.TecCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
public class TecClienteImpl implements TecClienteDao{

    private final Connection conn;
    
    public TecClienteImpl() {
        this.conn = MysqlDaoFactory.createConnection();
    }
    
    @Override
    public TecCliente buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TecCliente buscar(int idCli) {
        TecCliente cli = null;
        ResultSet rs;
        String sql = "SELECT * FROM tec_cliente WHERE cli_id = ?";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, idCli);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, "NO HAY DATOS");

            } else {
                do {
                    cli = new TecCliente();
                    cli.setCliId(rs.getInt("cli_id"));
                    cli.setCliNombres(rs.getString("cli_nombres"));

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cli;
    }

    @Override
    public ArrayList<TecCliente> listar() {
       
        ArrayList<TecCliente> clientes = new ArrayList<>();
        
        ResultSet rs;
        String sql = "SELECT * FROM tec_cliente";
        
        PreparedStatement pstm;
        try {
            pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            if (!rs.next()) {
                System.out.println("No hay clientes");
            }else{
                do{
                    TecCliente cliente = new TecCliente();
                    cliente.setCliId(rs.getInt("cli_id"));
                    cliente.setCliNombres(rs.getString("cli_nombres"));
                    cliente.setCliApellidos(rs.getString("cli_apellidos"));
                    cliente.setCliTelefono(rs.getString("cli_telefono"));
                    cliente.setCliDireccion(rs.getString("cli_direccion"));
                    cliente.setCliComuna(rs.getString("cli_comuna"));
                    
                    clientes.add(cliente);
                }while (rs.next());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  clientes;
        
    }

    @Override
    public boolean guardar(TecCliente cli) {
       boolean resultado = false;
        String sql = "INSERT INTO tec_cliente(cli_nombres, cli_apellidos, cli_telefono, cli_direccion, cli_comuna) values(?,?,?,?,?)";
        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, cli.getCliNombres());
            pstm.setString(2, cli.getCliApellidos());
            pstm.setString(3, cli.getCliTelefono());
            pstm.setString(4, cli.getCliDireccion());
            pstm.setString(5, cli.getCliComuna());
            
            pstm.executeUpdate();
            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(TecClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public boolean editar(TecCliente cli) {
        
         boolean result = false;
        String sql = "UPDATE tec_cliente SET cli_nombres = ?, cli_apellidos = ?, cli_telefono = ?"
                + ", cli_direccion = ?, cli_comuna = ?  WHERE cli_id = ?";
        
        Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, "Cliente editar {0}", cli);

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, cli.getCliNombres());
            pstm.setString(2, cli.getCliApellidos());
            pstm.setString(3, cli.getCliTelefono());
            pstm.setString(4, cli.getCliDireccion());
            pstm.setString(5, cli.getCliComuna());
            pstm.setInt(6, cli.getCliId());

            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, "Edita {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
        
        
        }

    @Override
    public boolean borrar(int id) {
       
        boolean result = false;
        String sql = "DELETE FROM tec_cliente WHERE cli_id = ?";

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, id);
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, "BORRA {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;

    }
    
}
