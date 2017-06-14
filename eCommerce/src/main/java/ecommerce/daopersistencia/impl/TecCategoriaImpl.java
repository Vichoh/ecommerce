package ecommerce.daopersistencia.impl;

import ecommerce.daopersistencia.dao.TecCategoriaDao;
import ecommerce.daopersistencia.factory.MysqlDaoFactory;
import ecommerce.modelo.TecCategoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TecCategoriaImpl implements TecCategoriaDao {

    private final Connection conn;

    public TecCategoriaImpl() {
        this.conn = MysqlDaoFactory.createConnection();
    }

    @Override
    public boolean guardar(TecCategoria cat) {

        boolean resultado = false;
        String sql = "INSERT INTO tec_categoria(cat_nombre) values(?)";
        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, cat.getCatNombre());
            pstm.executeUpdate();
            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public TecCategoria buscar(String nombre) {

        TecCategoria cat = null;
        ResultSet rs;
        String sql = "SELECT * FROM tec_categoria WHERE cat_nombre = ?";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, nombre);
            rs = pstm.executeQuery();

            if (rs.next()) {
                cat = map(rs);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }

    @Override
    public ArrayList<TecCategoria> listar() {

        ArrayList<TecCategoria> categorias = new ArrayList<>();

        ResultSet rs;
        String sql = "SELECT * FROM tec_categoria";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                System.out.println("NO HAY DATOS");

            } else {
                do {
                    TecCategoria cat = new TecCategoria();
                    cat.setCatId(rs.getInt("cat_id"));
                    cat.setCatNombre(rs.getString("cat_nombre"));
                    categorias.add(cat);

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;

    }

    @Override
    public boolean borrar(int idCat) {

        boolean result = false;
        String sql = "DELETE FROM tec_categoria WHERE cat_id = ?";

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, idCat);
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, "BORRA {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public TecCategoria buscar(int idCat) {

        TecCategoria cat = null;
        ResultSet rs;
        String sql = "SELECT * FROM tec_categoria WHERE cat_id = ?";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, idCat);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, "NO HAY DATOS");

            } else {
                do {
                    cat = new TecCategoria();
                    cat.setCatId(rs.getInt("cat_id"));
                    cat.setCatNombre(rs.getString("cat_nombre"));

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;

    }

    @Override
    public boolean editar(TecCategoria cat) {

        boolean result = false;
        String sql = "UPDATE tec_categoria SET cat_nombre = ? WHERE cat_id = ?";
        Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, "Categoria editar {0}", cat);

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, cat.getCatNombre());
            pstm.setInt(2, cat.getCatId());

            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, "Edita {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;

    }

    /**
     * Map the current row of the given ResultSet to an User.
     *
     * @param resultSet The ResultSet of which the current row is to be mapped
     * to an User.
     * @return The mapped User from the current row of the given ResultSet.
     * @throws SQLException If something fails at database level.
     */
    private static TecCategoria map(ResultSet resultSet) throws SQLException {
        TecCategoria cat = new TecCategoria();
        cat.setCatId(resultSet.getInt("cat_id"));
        cat.setCatNombre(resultSet.getString("cat_nombre"));
        return cat;
    }

}
