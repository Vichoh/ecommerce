/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.controlador;

import ecommerce.daopersistencia.dao.TecClienteDao;
import ecommerce.daopersistencia.dao.TecordenDao;
import ecommerce.daopersistencia.factory.DAOFactory;
import ecommerce.daopersistencia.factory.TipoBD;
import ecommerce.modelo.TecCliente;
import ecommerce.modelo.TecOrden;
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
public class ControladorOrden extends HttpServlet{
    
    
    public static DAOFactory fabricaOrden;
            
            
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
            case "/orden":
                this.listarOrdens(request, response);
                break;
            case "/orden_form":
                this.mostrarFormOrden(request, response);
                break;
            case "/eliminarOrden":
                this.eliminarOrden(request, response);
                break;
            case "/editarOrden":
                this.mostrarFormEditarOrden(request, response);
                break;
            default:
                throw new AssertionError();
        }
        } catch (SQLException e) {
        }
        
        
    }

    @Override
    public void init() throws ServletException {
        ControladorOrden.fabricaOrden = DAOFactory.getFactory(TipoBD.MYSQL, this.getServletContext());
        ControladorCliente.fabricaCliente = DAOFactory.getFactory(TipoBD.MYSQL, this.getServletContext());
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
            case "/guardarOrden":
                this.guardarOrden(request, response);
                break;
             case "/actualizarOrden":
                this.actualizarOrden(request, response);
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

    private void listarOrdens(HttpServletRequest request, HttpServletResponse response)
     throws SQLException, IOException, ServletException{
   
        TecordenDao tecordenDao = ControladorOrden.fabricaOrden.getTecOrdenDao();
        ArrayList<TecOrden> listadoOrden = tecordenDao.listar();
        
        request.setAttribute("listadoOrdens", listadoOrden);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/orden.jsp");
        dispatcher.forward(request, response);
    
    }

    private void mostrarFormOrden(HttpServletRequest request, HttpServletResponse response) 
    throws SQLException, IOException, ServletException{
      
        TecClienteDao clienteDao =  ControladorCliente.fabricaCliente.getTecClienteDao();
         ArrayList<TecCliente> clientes = new ArrayList<>();
         
         
        for (int i = 0; i < clienteDao.listar().size(); i++) {
            clientes.add(clienteDao.listar().get(i));
        }
        
        //ArrayList<TecCliente> clientes = clienteDao.listar();
        //System.out.println(clientes.get(0).getCliNombres());
        
        
        
        //request.setAttribute("nombre_cliente", clientes.get(0).getCliNombres());
        request.setAttribute("clientes", clientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/orden_form.jsp");
        dispatcher.forward(request, response);
    
    }

    private void eliminarOrden(HttpServletRequest request, HttpServletResponse response) 
    throws SQLException, IOException, ServletException{
   
        
        TecordenDao tecordenDao = ControladorOrden.fabricaOrden.getTecOrdenDao();
        tecordenDao.borrar(Integer.parseInt(request.getParameter("ord_id")));
        
        response.sendRedirect("orden");
    }

    private void guardarOrden(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException{
        
        
        TecOrden orden = new TecOrden();
        
        orden.setOrdFcreacion(request.getParameter("fecha_creacion"));
        orden.setOrdNumConfirmacion(Integer.parseInt(request.getParameter("numero_confirmacion")));
        orden.setOrdPrecioTotal(Integer.parseInt(request.getParameter("precio_total")));
        
        TecCliente cliente = new TecCliente();
        cliente.setCliId(Integer.parseInt(request.getParameter("clientes")));
        orden.setCli(cliente);
        
        
        TecordenDao dao = ControladorOrden.fabricaOrden.getTecOrdenDao();
        dao.guardar(orden);
        
        response.sendRedirect("orden");
        
    }

    private void mostrarFormEditarOrden(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException{
        
         TecClienteDao clienteDao =  ControladorCliente.fabricaCliente.getTecClienteDao();
         ArrayList<TecCliente> clientes = new ArrayList<>();
         clientes = clienteDao.listar();
          request.setAttribute("clientes", clientes);
  
        request.setAttribute("ordId", request.getParameter("id"));
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/orden_editar.jsp");
        dispatcher.forward(request, response);
    
    }

    private void actualizarOrden(HttpServletRequest request, HttpServletResponse response) 
    throws SQLException, IOException, ServletException
    {
   
        TecOrden orden = new TecOrden();
        
        orden.setOrdId(Integer.parseInt(request.getParameter("id_ord")));
        orden.setOrdFcreacion(request.getParameter("fecha_ed_creacion"));
        orden.setOrdNumConfirmacion(Integer.parseInt(request.getParameter("numero_ed_confirmacion")));
        orden.setOrdPrecioTotal(Integer.parseInt(request.getParameter("precio_ed_total")));
        
        TecCliente cliente = new TecCliente();
        cliente.setCliId(Integer.parseInt(request.getParameter("ed_clientes")));
        orden.setCli(cliente);
    
        TecordenDao dao = ControladorOrden.fabricaOrden.getTecOrdenDao();
        dao.editar(orden);
        
        response.sendRedirect("orden");
    }
}
