package Controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private Connection connection;

    public HotelDAO() {
    
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> obtenerHoteles() throws SQLException {
        List<String> hoteles = new ArrayList<>();
        String query = "SELECT nombre FROM hotel";  

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                hoteles.add(rs.getString("nombre"));
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
}
