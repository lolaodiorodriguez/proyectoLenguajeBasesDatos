package Controlador;

import Model.Hotel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    private Connection connection;

    public HotelDAO() {
        // Inicializar la conexión a la base de datos
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UsuarioAdmin", "pass123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener los nombres de los hoteles
    public List<String> obtenerHoteles() throws SQLException {
        List<String> hoteles = new ArrayList<>();
        String query = "SELECT nombre FROM hotel"; // Ajusta según el esquema de tu base de datos

        // Usar try-with-resources para manejar automáticamente el cierre de recursos
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            // Recorrer los resultados y agregar cada nombre a la lista
            while (rs.next()) {
                hoteles.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            // Registrar el error o manejarlo de acuerdo a tu lógica
            System.err.println("Error al obtener hoteles: " + e.getMessage());
            throw e; // Rethrow para notificar al llamador
        }
        return hoteles;
    }

    public List<Hotel> obtenerHotel() throws SQLException {
        List<Hotel> suministros = new ArrayList<>();
        String sql = "SELECT * FROM hotel";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Hotel suministro = new Hotel(
                        resultSet.getInt("ID_HOTEL"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getString("DIRECCION"),
                        resultSet.getString("TELEFONO")
                );
                suministros.add(suministro);
            }
        }
        return suministros;
    }

    public List<Hotel> obtenerHotelBuscar(int id) throws SQLException {
        List<Hotel> hoteles = new ArrayList<>();
        String sql = "SELECT * FROM Hotel where ID_HOTEL= '" + id + "'";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Hotel hotel = new Hotel(
                        resultSet.getInt("ID_HOTEL"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getString("DIRECCION"),
                        resultSet.getString("TELEFONO")
                );
                hoteles.add(hotel);
            }
        }
        return hoteles;
    }

    public int obtenerIdHotelPorNombre(String nombre) throws SQLException {
        String query = "SELECT id_hotel FROM hotel WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_hotel");
                } else {
                    throw new SQLException("Hotel no encontrado.");
                }
            }
        }
    }

    public int obtenerIdHotelPorID(int id) throws SQLException {
        String query = "SELECT id_hotel FROM hotel WHERE id_hotel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_hotel");
                } else {
                    return 0;
                }
            }
        }
    }

    public void crearHotel(Hotel hotel) throws SQLException {
        String sql = "INSERT INTO Hotel (ID_HOTEL, NOMBRE, DIRECCION, TELEFONO) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotel.getId_hotel());
            statement.setString(2, hotel.getNombre());
            statement.setString(3, hotel.getDireccion());
            statement.setString(4, hotel.getTelefono());
            statement.executeUpdate();
        }
    }

    public void modificarHotel(Hotel hotel) throws SQLException {
        String sql = "UPDATE hotel SET NOMBRE = ?, DIRECCION = ?, TELEFONO = ? WHERE ID_HOTEL = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hotel.getNombre());
            statement.setString(2, hotel.getDireccion());
            statement.setString(3, hotel.getTelefono());
            statement.setInt(4, hotel.getId_hotel());
            statement.executeUpdate();
        }
    }

    public void Eliminar(int id) throws SQLException {
        String sql = "DELETE FROM hotel WHERE ID_HOTEL = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
