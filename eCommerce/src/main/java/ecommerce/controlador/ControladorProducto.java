/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.controlador;

import ecommerce.daopersistencia.dao.TecCategoriaDao;
import ecommerce.daopersistencia.dao.TecProductoDao;
import ecommerce.daopersistencia.factory.DAOFactory;
import ecommerce.daopersistencia.factory.TipoBD;
import ecommerce.modelo.TecCategoria;
import ecommerce.modelo.TecProducto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adrian
 */
public class ControladorProducto extends HttpServlet{
    
    public static DAOFactory fabricaProducto;
    
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        try {
            switch (userPath) {
            case "/producto":
                this.listarProductos(request, response);
                break;
            case "/producto_form":
                this.mostrarFormNuevoProducto(request, response);
                break;
            case "/eliminarProducto":
                this.eliminarProducto(request, response);
                break;
            case "/formEditarProducto":
                this.mostrarFormEditarProducto(request, response);
                break;
            default:
                throw new AssertionError();
        }
        } catch (SQLException e) {
        }
        
        
    }

    @Override
    public void init() throws ServletException {
        ControladorProducto.fabricaProducto = DAOFactory.getFactory(TipoBD.MYSQL, this.getServletContext());
        ControladorEcommerce.fabrica = DAOFactory.getFactory(TipoBD.MYSQL, this.getServletContext());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        try {
        switch (userPath) {
            case "/guardarProducto":
                this.guardarProducto(request, response);
                break;
            case "/editarProducto":
                this.editarProducto(request, response);
                break;
            default:
                throw new AssertionError();
        }
        } catch (SQLException e) {
        }
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
     throws SQLException, IOException, ServletException{
       
        System.out.println("si entra");
        TecProductoDao productoDao = ControladorProducto.fabricaProducto.getTecProductoDao();
        
        ArrayList<TecProducto> listadoProductos = productoDao.listar();
        
        request.setAttribute("listadoProductos", listadoProductos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/producto.jsp");
        dispatcher.forward(request, response);
        
        
    }

    private void mostrarFormNuevoProducto(HttpServletRequest request, HttpServletResponse response) 
          throws SQLException, IOException, ServletException{
        
        TecCategoriaDao categoriaDao = ControladorEcommerce.fabrica.getTecCategoriaDao();
        System.out.println(categoriaDao.listar().get(0).getCatNombre());
        
        ArrayList<TecCategoria> listadoCategorias = categoriaDao.listar();
        request.setAttribute("listadoCategorias", listadoCategorias);
        RequestDispatcher dispatcher  = request.getRequestDispatcher("/WEB-INF/view/producto_form.jsp");
        dispatcher.forward(request, response);
    }

    private void guardarProducto(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException{
        
        TecProducto producto = new TecProducto();        
        producto.setProNombre(request.getParameter("nombre_producto"));
        producto.setProDescripcion(request.getParameter("descripcion_producto"));
        producto.setProPrecio(Integer.parseInt(request.getParameter("precio_producto")));
        producto.setProUltimaActualizacion(request.getParameter("ultima_actializacion_producto"));
        
        TecCategoria categoria = new TecCategoria();
        categoria.setCatId(Integer.parseInt(request.getParameter("categoria")));
        producto.setCat(categoria);
        
        TecProductoDao tecProductoDao = ControladorProducto.fabricaProducto.getTecProductoDao();
        tecProductoDao.guardar(producto);
        
        response.sendRedirect("index.jsp");
        
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException{
        
        TecProductoDao tecProductoDao = ControladorProducto.fabricaProducto.getTecProductoDao();
        tecProductoDao.borrar(Integer.parseInt(request.getParameter("pro_id")));
        
        response.sendRedirect("producto");
        
        
          }

    private void mostrarFormEditarProducto(HttpServletRequest request, HttpServletResponse response) 
     throws SQLException, IOException, ServletException{
   
        TecCategoriaDao categoriaDao = ControladorEcommerce.fabrica.getTecCategoriaDao();
        System.out.println(categoriaDao.listar().get(0).getCatNombre());
        
        ArrayList<TecCategoria> listadoCategorias = categoriaDao.listar();
        request.setAttribute("id_pro", request.getParameter("id"));
        request.setAttribute("listadoCategorias", listadoCategorias);
        RequestDispatcher dispatcher  = request.getRequestDispatcher("/WEB-INF/view/producto_editar.jsp");
        dispatcher.forward(request, response);
    
    
    }

    private void editarProducto(HttpServletRequest request, HttpServletResponse response)  
            throws SQLException, IOException, ServletException{
    
        
        TecProducto producto = new TecProducto();   
        producto.setProId(Integer.parseInt(request.getParameter("id_pro")));
        producto.setProNombre(request.getParameter("nombre_edi_producto"));
        producto.setProDescripcion(request.getParameter("descripcion_edi_producto"));
        producto.setProPrecio(Integer.parseInt(request.getParameter("precio_edi_producto")));
        producto.setProUltimaActualizacion(request.getParameter("ultima_actializacion_edi_producto"));
        
        TecCategoria categoria = new TecCategoria();
        categoria.setCatId(Integer.parseInt(request.getParameter("edi_categoria")));
        producto.setCat(categoria);
        
        TecProductoDao tecProductoDao = ControladorProducto.fabricaProducto.getTecProductoDao();
        tecProductoDao.editar(producto);
        
        response.sendRedirect("producto");
        
    
    }
    
    
}
