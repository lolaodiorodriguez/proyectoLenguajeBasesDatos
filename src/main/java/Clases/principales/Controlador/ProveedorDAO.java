
package Controlador;

import Conexion.DatabaseConnection;
import Model.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    private Connection connection;

    public ProveedorDAO() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearProveedor(Proveedor proveedor) throws SQLException {
        String sql = "INSERT INTO PROVEEDOR (ID_PROVEEDOR,NOMBRE, TELEFONO, DIRECCION, EMAIL, ID_HOTEL) VALUES (factura_seq.NEXTVAL,?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getEmail());
            ps.setInt(5, proveedor.getIdHotel());
            ps.executeUpdate();
        }
    }

    public void modificarProveedor(Proveedor proveedor) throws SQLException {
        String sql = "UPDATE PROVEEDOR SET NOMBRE = ?, TELEFONO = ?, DIRECCION = ?, EMAIL = ?, ID_HOTEL = ? WHERE ID_PROVEEDOR = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getEmail());
            ps.setInt(5, proveedor.getIdHotel());
            ps.setInt(6, proveedor.getIdProveedor());
            ps.executeUpdate();
        }
    }

    public void eliminarProveedor(int idProveedor) throws SQLException {
        String sql = "DELETE FROM PROVEEDOR WHERE ID_PROVEEDOR = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            ps.executeUpdate();
        }
    }

    public List<Proveedor> obtenerProveedores() throws SQLException {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM PROVEEDOR";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                        rs.getInt("ID_PROVEEDOR"),
                        rs.getString("NOMBRE"),
                        rs.getString("TELEFONO"),
                        rs.getString("DIRECCION"),
                        rs.getString("EMAIL"),
                        rs.getInt("ID_HOTEL")
                );
                proveedores.add(proveedor);
            }
        }
        return proveedores;
    }
}
