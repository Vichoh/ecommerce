/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.controlador;

import ecommerce.daopersistencia.dao.TecCategoriaDao;
import ecommerce.daopersistencia.factory.DAOFactory;
import ecommerce.daopersistencia.factory.TipoBD;
import ecommerce.modelo.TecCategoria;
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
 * @author dci
 */
public class ControladorEcommerce extends HttpServlet {

        
    public static DAOFactory fabrica;
            
            
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        try {
            switch (userPath) {
            case "/categoria":
                this.listarCategorias(request, response);
                break;
            case "/nuevaCategoria":
                this.mostrarFormNuevaCategoria(request, response);
                break;
            case "/eliminarCategoria":
                this.eliminarCategoria(request, response);
                break;
            case "/editarCategoria":
                this.mostrarFormActualizar(request, response);
                break;
            default:
                throw new AssertionError();
        }
        } catch (SQLException e) {
        }
        
        
    }

    @Override
    public void init() throws ServletException {
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
            case "/guardarCategoria":
                this.guardarCategoria(request, response);
                break;
            case "/actualizarCategoria":
                this.actualizarCategoria(request, response);
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

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException{
        
        TecCategoriaDao categoriaDao = ControladorEcommerce.fabrica.getTecCategoriaDao();
        ArrayList<TecCategoria> listadoCategorias = categoriaDao.listar();
        
        request.setAttribute("listadoCategorias", listadoCategorias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/categoria.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormNuevaCategoria(HttpServletRequest request, HttpServletResponse response)
                        throws SQLException, IOException, ServletException{
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/categoria_form.jsp");
        dispatcher.forward(request, response);
    }
    
    
    
    private void guardarCategoria(HttpServletRequest request, HttpServletResponse response)
                        throws SQLException, IOException, ServletException{
        
        TecCategoria cat = new TecCategoria();        
        cat.setCatNombre(request.getParameter("nombre_categoria"));
        
        TecCategoriaDao categoriaDao  = ControladorEcommerce.fabrica.getTecCategoriaDao();
        categoriaDao.guardar(cat);
        
        response.sendRedirect("index.jsp");
       
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException{
        
        TecCategoriaDao categoriaDao  = ControladorEcommerce.fabrica.getTecCategoriaDao();
        categoriaDao.borrar(Integer.parseInt(request.getParameter("cat_id")));
        
        response.sendRedirect("categoria");
    }
    
    private void mostrarFormActualizar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        
        request.setAttribute("catId", Integer.parseInt(request.getParameter("id")));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/categoria_editar.jsp");
        dispatcher.forward(request, response);
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException{

        TecCategoria cat = new TecCategoria();        
        cat.setCatNombre(request.getParameter("nombre_editado_categoria"));
        cat.setCatId(Integer.parseInt(request.getParameter("id_cat")));
        System.out.println("id "+request.getParameter("id_cat"));
        TecCategoriaDao categoriaDao = ControladorEcommerce.fabrica.getTecCategoriaDao();
        categoriaDao.editar(cat);
        
        response.sendRedirect("categoria");
    }
    
    

}
