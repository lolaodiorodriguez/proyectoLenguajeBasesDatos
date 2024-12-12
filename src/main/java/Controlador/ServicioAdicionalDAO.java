package Controlador;

import Conexion.DatabaseConnection;
import Model.ServicioAdicional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioAdicionalDAO {

    public List<ServicioAdicional> obtenerTodosLosServicios() throws SQLException {
        String query = "SELECT * FROM servicio_adicional";
        List<ServicioAdicional> servicios = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                servicios.add(new ServicioAdicional(
                        rs.getInt("id_servicio"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha"),
                        rs.getDouble("precio"),
                        rs.getInt("id_reserva")
                ));
            }
        }
        return servicios;
    }

    public void agregarServicio(ServicioAdicional servicio) throws SQLException {
        String query = "INSERT INTO servicio_adicional (descripcion, fecha, precio, id_reserva) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, servicio.getDescripcion());
            stmt.setDate(2, servicio.getFecha());
            stmt.setDouble(3, servicio.getPrecio());
            stmt.setInt(4, servicio.getIdReserva());

            stmt.executeUpdate();
        }
    }

    public void EliminarRegistro(int id) throws SQLException {
        String sql = "DELETE FROM servicio_adicional WHERE ID_SERVICIO = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }

    }

    public void actualizarServicio(ServicioAdicional servicio) throws SQLException {
        String query = "UPDATE servicio_adicional SET descripcion = ?, fecha = ?, precio = ?, id_reserva = ? WHERE id_servicio = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, servicio.getDescripcion());
            stmt.setDate(2, servicio.getFecha());
            stmt.setDouble(3, servicio.getPrecio());
            stmt.setInt(4, servicio.getIdReserva());
            stmt.setInt(5, servicio.getIdServicio());

            stmt.executeUpdate();
        }
    }

    public ServicioAdicional obtenerServicioPorId(int idServicio) throws SQLException {
        String query = "SELECT * FROM servicio_adicional WHERE id_servicio = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idServicio);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ServicioAdicional(
                            rs.getInt("id_servicio"),
                            rs.getString("descripcion"),
                            rs.getDate("fecha"),
                            rs.getDouble("precio"),
                            rs.getInt("id_reserva")
                    );
                }
            }
        }
        throw new SQLException("Servicio no encontrado");
    }
}
