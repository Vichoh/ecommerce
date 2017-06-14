/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.daopersistencia.impl;

import com.sun.corba.se.impl.oa.toa.TOA;
import ecommerce.daopersistencia.dao.TecProductoDao;
import ecommerce.daopersistencia.factory.MysqlDaoFactory;
import ecommerce.modelo.TecCategoria;
import ecommerce.modelo.TecProducto;
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
public class TecProductoImpl implements TecProductoDao{
    private final Connection conn;
    
    public TecProductoImpl(){
        this.conn = MysqlDaoFactory.createConnection();
    }

    @Override
    public TecProducto buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TecProducto buscar(int idPro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<TecProducto> listar() {
        ArrayList<TecProducto> productos = new ArrayList<>();
        
        ResultSet rs;
        String sql = "Select * FROM tec_producto";
        
        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            if (!rs.next()) {
                System.out.println("No hay productos");
            }else{
                do{
                    TecProducto producto = new TecProducto();
                    producto.setProId(rs.getInt("pro_id"));
                    producto.setProNombre(rs.getString("pro_nombre"));
                    producto.setProDescripcion(rs.getString("pro_descripcion"));
                    producto.setProPrecio(rs.getInt("pro_precio"));
                    producto.setProUltimaActualizacion(rs.getString("pro_ultima_actualizacion"));
                    
                    
                    
                    System.out.println("id cat "+  rs.getInt("cat_id"));
                   
                    TecCategoriaImpl categoriaImpl = new TecCategoriaImpl();
                    TecCategoria categoria = categoriaImpl.buscar(rs.getInt("cat_id"));
                    
                    producto.setCat(categoria);
                    productos.add(producto);
                }while (rs.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productos;
    }

    @Override
    public boolean guardar(TecProducto pro) {
       boolean resultado = false;
       String sql = "INSERT INTO tec_producto(pro_nombre, pro_descripcion, pro_precio, pro_ultima_actualizacion, cat_id) values(?,?,?,?,?)";
    
    try {
        
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setString(1, pro.getProNombre());
        pstm.setString(2, pro.getProDescripcion());
        pstm.setInt(3, pro.getProPrecio());
        pstm.setString(4, pro.getProUltimaActualizacion());
        pstm.setInt(5, pro.getCat().getCatId());
        
        pstm.executeUpdate();
        resultado = true;
        
    }   catch (SQLException ex) {
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return resultado;
    }

    @Override
    public boolean editar(TecProducto pro) {
        boolean result = false;
        String sql = "UPDATE tec_producto SET pro_nombre = ?, pro_descripcion = ?, pro_precio = ?, pro_ultima_actualizacion = ?"
                + ", cat_id = ?  WHERE pro_id = ?";
        Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, "Producto editar {0}", pro);

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, pro.getProNombre());
            pstm.setString(2, pro.getProDescripcion());
            pstm.setInt(3, pro.getProPrecio());
            pstm.setString(4, pro.getProUltimaActualizacion());
            pstm.setInt(5, pro.getCat().getCatId());
             
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, "Edita {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    
    }

    @Override
    public boolean borrar(int id) {
         boolean result = false;
        String sql = "DELETE FROM tec_producto WHERE pro_id = ?";

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, id);
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, "BORRA {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
   
}
