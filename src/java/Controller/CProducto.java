package Controller;

import Bean.Producto;
import Model.MProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet(name = "CProducto", urlPatterns = {"/ServletProducto"})
public class CProducto extends HttpServlet {

    MProducto dao = new MProducto();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String accion = request.getParameter("op");

            switch (accion) {
                case "listar":
                    Listar(request, response);
                    break;
                case "listarID":
                    ListarID(request, response);
                    break;
                case "insertar":
                    Insertar(request, response);
                    break;
                case "actualizar":
                    Actualizar(request, response);
                    break;
                case "eliminar":
                    Eliminar(request, response);
                    break;
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void Listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //response.setContentType("application/json");
            PrintWriter out = response.getWriter();

            JSONArray array = new JSONArray();
            dao.Listar().forEach(elem -> {
                JSONObject obj = new JSONObject();
                obj.put("id", elem.getId_prod());
                obj.put("nombre", elem.getNom_prod());
                obj.put("precio", elem.getPrecio_prod());
                obj.put("estado", elem.getEstado_prod());
                array.add(obj);
            });

            out.print(array);
        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Insertar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter out = response.getWriter();
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));

            Producto producto = new Producto(0, nombre, precio, true);
            boolean resp = dao.Insertar(producto);

            String enviar = (resp) ? "true" : "false";
            out.print(enviar);

        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter out = response.getWriter();
            
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));

            Producto producto = new Producto(id, nombre, precio, true);
            boolean resp = dao.Actualizar(producto);

            String enviar = (resp) ? "true" : "false";
            out.print(enviar);

        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ListarID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter out = response.getWriter();

            int id = Integer.parseInt(request.getParameter("id"));
            
            Producto producto = dao.ListarID(id);
            JSONObject obj = new JSONObject();
            obj.put("id", producto.getId_prod());
            obj.put("nombre", producto.getNom_prod());
            obj.put("precio", producto.getPrecio_prod());
            obj.put("estado", producto.getEstado_prod());

            out.print(obj);
        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter out = response.getWriter();
            
            int id = Integer.parseInt(request.getParameter("id"));

            boolean resp = dao.Eliminar(id);

            String enviar = (resp) ? "true" : "false";
            out.print(enviar);

        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
