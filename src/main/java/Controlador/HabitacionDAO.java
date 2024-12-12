package Controlador;

import Conexion.DatabaseConnection;
import Model.Habitacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {

    public List<Habitacion> obtenerTodasLasHabitaciones() throws SQLException {
        String query = "SELECT * FROM habitacion";
        List<Habitacion> habitaciones = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Habitacion habitacion = new Habitacion(
                        rs.getInt("ID_HABITACION"),
                        rs.getString("TIPO_HABITACION"),
                        rs.getInt("PRECIO"),
                        rs.getInt("CAPACIDAD"),
                        rs.getString("DISPONIBILIDAD"),
                        rs.getInt("ID_HOTEL"));
                habitaciones.add(habitacion);
            }
        }

        return habitaciones;
    }

    public List<Habitacion> obtenerHabitacionesBuscar(int id) throws SQLException {
        List<Habitacion> habi = new ArrayList<>();
        String sql = "SELECT * FROM habitacion where ID_HABITACION= '" + id + "'";
        try (Statement statement = DatabaseConnection.getConnection().createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Habitacion habita = new Habitacion(
                        rs.getInt("ID_HABITACION"),
                        rs.getString("TIPO_HABITACION"),
                        rs.getInt("PRECIO"),
                        rs.getInt("CAPACIDAD"),
                        rs.getString("DISPONIBILIDAD"),
                        rs.getInt("ID_HOTEL")
                );
                habi.add(habita);
            }
        }
        return habi;
    }

    public int obtenerHabitacionesPorID(int id) throws SQLException {
        String query = "SELECT ID_HABITACION FROM habitacion WHERE ID_HABITACION = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_HABITACION");
                } else {
                    return 0;
                }
            }
        }
    }

    public void crearHabitacion(Habitacion hotel) throws SQLException {
        String sql = "INSERT INTO habitacion (ID_HABITACION, TIPO_HABITACION, PRECIO, CAPACIDAD,DISPONIBILIDAD,ID_HOTEL) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, hotel.getId());
            statement.setString(2, hotel.getTipo());
            statement.setInt(3, hotel.getPrecio());
            statement.setInt(4, hotel.getCapacidad());
            statement.setString(5, hotel.getEstado());
            statement.setInt(6, hotel.getId_hotel());
            statement.executeUpdate();
        }
    }

    public void modificarHabitaciones(Habitacion hotel) throws SQLException {
        String sql = "UPDATE habitacion SET TIPO_HABITACION = ?, PRECIO = ?, CAPACIDAD = ?,DISPONIBILIDAD=?, ID_HOTEL=? WHERE ID_HABITACION = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {

            statement.setString(1, hotel.getTipo());
            statement.setInt(2, hotel.getPrecio());
            statement.setInt(3, hotel.getCapacidad());
            statement.setString(4, hotel.getEstado());
            statement.setInt(5, hotel.getId_hotel());
            statement.setInt(6, hotel.getId());
            statement.executeUpdate();
        }
    }

    public void Eliminar(int id) throws SQLException {
        String sql = "DELETE FROM habitacion WHERE ID_HABITACION = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

}
