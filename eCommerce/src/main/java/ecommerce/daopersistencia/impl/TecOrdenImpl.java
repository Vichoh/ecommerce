/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.daopersistencia.impl;

import ecommerce.daopersistencia.dao.TecordenDao;
import ecommerce.daopersistencia.factory.MysqlDaoFactory;
import ecommerce.modelo.TecCliente;
import ecommerce.modelo.TecOrden;
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
public class TecOrdenImpl implements TecordenDao{

    private final Connection conn;
    
    public TecOrdenImpl (){
        this.conn = MysqlDaoFactory.createConnection();
    }
    
    
    @Override
    public TecOrden buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TecOrden buscar(int idord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<TecOrden> listar() {
    
    ArrayList<TecOrden> ordens = new ArrayList<>();

        ResultSet rs;
        String sql = "SELECT * FROM tec_Orden";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                System.out.println("NO HAY DATOS de orden");

            } else {
                do {
                    TecOrden orden = new TecOrden();
                    orden.setOrdId(rs.getInt("ord_id"));
                    orden.setOrdFcreacion(rs.getString("ord_fcreacion"));
                    orden.setOrdNumConfirmacion(rs.getInt("ord_num_confirmacion"));
                    orden.setOrdPrecioTotal(rs.getInt("ord_precio_total"));
                    
                    TecClienteImpl  clienteImpl = new  TecClienteImpl();
                    
                    TecCliente cliente = clienteImpl.buscar(rs.getInt("cli_id"));
                    
                    orden.setCli(cliente);
                    ordens.add(orden);

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ordens;
    
    }

    @Override
    public boolean guardar(TecOrden ord) {
   
        boolean resultado = false;
        String sql = "INSERT INTO tec_orden (ord_fcreacion, ord_num_confirmacion, ord_precio_total, cli_id) values(?,?,?,?)";
        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, ord.getOrdFcreacion());
            pstm.setInt(2, ord.getOrdNumConfirmacion());
            pstm.setInt(3, ord.getOrdPrecioTotal());
            
            pstm.setInt(4, ord.getCli().getCliId());
       
            pstm.executeUpdate();
            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    
    }

    @Override
    public boolean editar(TecOrden ord) {
   
        boolean result = false;
        String sql = "UPDATE tec_orden SET ord_fcreacion = ?,ord_num_confirmacion = ?, ord_precio_total= ?,"
                + " cli_id = ? WHERE ord_id = ?";
        
        
        Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, "Orden editar {0}", ord);

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, ord.getOrdFcreacion());
            pstm.setInt(2, ord.getOrdNumConfirmacion());
            pstm.setInt(3, ord.getOrdPrecioTotal());
            pstm.setInt(4, ord.getCli().getCliId());
            

            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, "Edita {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
        
    }

    @Override
    public boolean borrar(int id) {
       
           boolean result = false;
        String sql = "DELETE FROM tec_orden WHERE ord_id = ?";

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, id  );
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, "BORRA {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    
    }
    
}
