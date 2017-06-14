/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce.controlador;

import ecommerce.daopersistencia.dao.TecClienteDao;
import ecommerce.daopersistencia.factory.DAOFactory;
import ecommerce.daopersistencia.factory.TipoBD;
import ecommerce.modelo.TecCliente;
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
public class ControladorCliente extends HttpServlet{
    
    public static DAOFactory fabricaCliente;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        try {
            switch (userPath) {
            case "/cliente":
                this.listarCliente(request, response);
                break;
            case "/cliente_form":
                this.mostrarFormNuevoCliente(request,response);
                break;
            case "/eliminarCliente":
                this.eliminarCliente(request,response);
                break;
            case "/goFormEditar":
                this.mostrarFormEditar(request,response);
                break;
            default:
                throw new AssertionError();
        }
        } catch (SQLException e) {
        }
        
        
    }

    @Override
    public void init() throws ServletException {
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
            case "/guardarCliente":
                this.guardarcliente(request, response);
                break;
            case "/actualizarCliente":
                this.actualizarCliente(request, response);
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

    private void listarCliente(HttpServletRequest request, HttpServletResponse response) 
     throws SQLException, IOException, ServletException{
        
        TecClienteDao clienteDao = ControladorCliente.fabricaCliente.getTecClienteDao();
        ArrayList<TecCliente> listadoClientes = clienteDao.listar();
        
        request.setAttribute("listaClientes", listadoClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cliente.jsp");
        dispatcher.forward(request, response);
        
    }

    private void mostrarFormNuevoCliente(HttpServletRequest request, HttpServletResponse response) 
    throws SQLException, IOException, ServletException{
      
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cliente_form.jsp");
        dispatcher.forward(request, response);
        
    }

    private void guardarcliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
       
        TecCliente cliente = new TecCliente();
        cliente.setCliNombres(request.getParameter("nombres_cliente"));
        cliente.setCliApellidos(request.getParameter("apellidos_cliente"));
        cliente.setCliTelefono(request.getParameter("telefono_cliente"));
        cliente.setCliDireccion(request.getParameter("direccion_cliente"));
        cliente.setCliComuna(request.getParameter("comuna_cliente"));
        
        TecClienteDao clienteDao = ControladorCliente.fabricaCliente.getTecClienteDao();
        clienteDao.guardar(cliente);
    
        response.sendRedirect("cliente");
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException
    {
    
        TecClienteDao clienteDao = ControladorCliente.fabricaCliente.getTecClienteDao();
        clienteDao.borrar(Integer.parseInt(request.getParameter("cli_id")));
        
        response.sendRedirect("cliente");
    
    }

    private void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response) 
    throws SQLException, IOException, ServletException{
    
        System.out.println(request.getParameter("id"));
        request.setAttribute("id_cliente", Integer.parseInt(request.getParameter("id")));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cliente_editar.jsp");
        dispatcher.forward(request, response);
    
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException{
        
        TecCliente cliente = new TecCliente();
        cliente.setCliId(Integer.parseInt(request.getParameter("id_cat")));
        cliente.setCliNombres(request.getParameter("nombres_act_cliente"));
        cliente.setCliApellidos(request.getParameter("apellidos_act_cliente"));
        cliente.setCliTelefono(request.getParameter("telefono_act_cliente"));
        cliente.setCliDireccion(request.getParameter("direccion_act_cliente"));
        cliente.setCliComuna(request.getParameter("comuna_act_cliente"));
        
        TecClienteDao clienteDao  = ControladorCliente.fabricaCliente.getTecClienteDao();
        clienteDao.editar(cliente);
        
        response.sendRedirect("cliente");
    }

}

