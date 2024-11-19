package Clases.principales;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelConex {
    private Connection connection;

    public HotelConex() {
        // Inicializar la conexión a la base de datos
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "C##NEL", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener los nombres de los hoteles
    public List<String> obtenerHoteles() throws SQLException {
        List<String> hoteles = new ArrayList<>();
        String query = "SELECT nombre FROM hotel";  // Cambia según tu esquema

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                hoteles.add(rs.getString("nombre"));
            }
        }
        return hoteles;
    }

    // Método para obtener el ID de un hotel a partir de su nombre
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
}
