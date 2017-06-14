package ecommerce.daopersistencia.factory;


import ecommerce.daopersistencia.dao.TecCategoriaDao;
import ecommerce.daopersistencia.dao.TecClienteDao;
import ecommerce.daopersistencia.dao.TecProductoDao;
import ecommerce.daopersistencia.dao.TecordenDao;
import ecommerce.daopersistencia.impl.TecCategoriaImpl;
import ecommerce.daopersistencia.impl.TecClienteImpl;
import ecommerce.daopersistencia.impl.TecOrdenImpl;
import ecommerce.daopersistencia.impl.TecProductoImpl;
import javax.servlet.ServletContext;

public abstract class DAOFactory {

    public static DAOFactory getFactory(TipoBD bd, ServletContext context) {
        
        switch (bd) {

            case DERBY:
                return new DerbyDaoFactory();
            case MYSQL:
                return new MysqlDaoFactory(context);
            case ORACLE:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException();
        }

    }

    public TecCategoriaDao getTecCategoriaDao() { 
        return new TecCategoriaImpl(); }
    
    public TecProductoDao getTecProductoDao() { 
        return new TecProductoImpl(); }
    
    public TecClienteDao getTecClienteDao() { 
        return new TecClienteImpl(); }
    
    public TecordenDao getTecOrdenDao() { 
        return new TecOrdenImpl(); }
//
//    public ProductoDao getProductoDao() { return new ProductoImpl(); }
}
