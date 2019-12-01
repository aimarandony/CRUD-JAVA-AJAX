package Interface;

import Bean.Producto;
import java.sql.SQLException;
import java.util.List;

public interface IProducto {
    public List<Producto> Listar() throws SQLException;
    public Producto ListarID(int id_prod) throws SQLException;
    public boolean Insertar(Producto producto) throws SQLException;
    public boolean Actualizar(Producto producto) throws SQLException;
    public boolean ActualizarEstado(int id_prod) throws SQLException;
    public boolean Eliminar(int id_prod) throws SQLException;
}
