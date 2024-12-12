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

    public List<String> obtenerProveedoresporNombre() throws SQLException {
        List<String> prov = new ArrayList<>();
        String query = "SELECT nombre FROM PROVEEDOR"; // Ajusta según el esquema de tu base de datos

        // Usar try-with-resources para manejar automáticamente el cierre de recursos
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            // Recorrer los resultados y agregar cada nombre a la lista
            while (rs.next()) {
                prov.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            // Registrar el error o manejarlo de acuerdo a tu lógica
            System.err.println("Error al obtener hoteles: " + e.getMessage());
            throw e; // Rethrow para notificar al llamador
        }
        return prov;
    }

    public int obtenerIdProveedorPorNombre(String nombre) throws SQLException {
        String query = "SELECT ID_PROVEEDOR FROM PROVEEDOR WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_PROVEEDOR");
                } else {
                    throw new SQLException("Hotel no encontrado.");
                }
            }
        }
    }

    // Crear proveedor
    public void crearProveedor(Proveedor proveedor) throws SQLException {
        String sql = "INSERT INTO PROVEEDOR (ID_PROVEEDOR,NOMBRE, TELEFONO, DIRECCION, EMAIL, ID_HOTEL,CATEGORIA) VALUES (factura_seq.NEXTVAL,?, ?, ?, ?, ?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getEmail());
            ps.setInt(5, proveedor.getIdHotel());
            ps.setString(6, proveedor.getCategoria());
            ps.executeUpdate();
        }
    }

    // Modificar proveedor
    public void modificarProveedor(Proveedor proveedor) throws SQLException {
        String sql = "UPDATE PROVEEDOR SET NOMBRE = ?, TELEFONO = ?, DIRECCION = ?, EMAIL = ?, ID_HOTEL = ?,CATEGORIA=? WHERE ID_PROVEEDOR = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getEmail());
            ps.setInt(5, proveedor.getIdHotel());
            ps.setString(6, proveedor.getCategoria());
            ps.setInt(7, proveedor.getIdProveedor());
            ps.executeUpdate();
        }
    }

    // Eliminar proveedor
    public void eliminarProveedor(int idProveedor) throws SQLException {
        String sql = "DELETE FROM PROVEEDOR WHERE ID_PROVEEDOR = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            ps.executeUpdate();
        }
    }

    // Listar todos los proveedores
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
                        rs.getInt("ID_HOTEL"),
                        rs.getString("CATEGORIA")
                );
                proveedores.add(proveedor);
            }
        }
        return proveedores;
    }
}
