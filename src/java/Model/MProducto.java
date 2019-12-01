package Model;

import Bean.Producto;
import Interface.IProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MProducto extends Conexion implements IProducto{
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @Override
    public List<Producto> Listar() throws SQLException{
        try {
            String sql = "SELECT * FROM PRODUCTOS";
            List<Producto> lista = new ArrayList<>();
            
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getBoolean(4));
                
                lista.add(producto);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally{
            rs.close();
            ps.close();
            con.close();
        }
    }

    @Override
    public Producto ListarID(int id_prod) throws SQLException{
        try {
            String sql = "SELECT * FROM PRODUCTOS WHERE id_prod = "+id_prod+"";
            
            Producto producto = null;
            
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {                
                producto = new Producto(
                        rs.getInt(1),
                        rs.getString(2) ,
                        rs.getDouble(3),
                        rs.getBoolean(4));
                
            }
            
            return producto;
        } catch (SQLException ex) {
            Logger.getLogger(MProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally{
            rs.close();
            ps.close();
            con.close();
        }
    }

    @Override
    public boolean Insertar(Producto producto) throws SQLException {
        try {
            String sql = "INSERT INTO PRODUCTOS VALUES(null, ?, ?, default)";
            
            boolean respuesta = false;
            
            con = conectar();
            
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNom_prod());
            ps.setDouble(2, producto.getPrecio_prod());

            if (ps.executeUpdate() == 1) {                
                respuesta = true;
            }

            return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(MProducto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally{
            ps.close();
            con.close();
        }
    }

    @Override
    public boolean Actualizar(Producto producto) throws SQLException {
        try {
            String sql = "UPDATE PRODUCTOS SET nom_prod = ?, precio_prod = ? WHERE id_prod = ?";
            
            boolean respuesta = false;
            
            con = conectar();
            
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNom_prod());
            ps.setDouble(2, producto.getPrecio_prod());
            ps.setDouble(3, producto.getId_prod());
            
            if (ps.executeUpdate() == 1) {                
                respuesta = true;
            }

            return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(MProducto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally{
            ps.close();
            con.close();
        }
    }

    @Override
    public boolean ActualizarEstado(int id_prod) throws SQLException {
        try {
            String sql = "UPDATE PRODUCTOS SET estado_prod = false WHERE id_prod = "+id_prod+"";
            
            boolean respuesta = false;
            
            con = conectar();            
            ps = con.prepareStatement(sql);
            
            if (ps.executeUpdate() == 1) {                
                respuesta = true;
            }

            return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(MProducto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally{
            ps.close();
            con.close();
        }
    }

    @Override
    public boolean Eliminar(int id_prod) throws SQLException {
        try {
            String sql = "DELETE FROM PRODUCTOS WHERE id_prod = "+id_prod+"";
            
            boolean respuesta = false;
            
            con = conectar();            
            ps = con.prepareStatement(sql);
            
            if (ps.executeUpdate() == 1) {                
                respuesta = true;
            }

            return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(MProducto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally{
            ps.close();
            con.close();
        }
    }
    
//    public static void main(String[] args) throws SQLException {
//        MProducto dao = new MProducto();
//        dao.Listar().stream().forEach(System.out::println);
//    }
}
