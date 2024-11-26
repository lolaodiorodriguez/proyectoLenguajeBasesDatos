import Clases.principales.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionConex {
    public List<Habitacion> obtenerTodasLasHabitaciones() throws SQLException {
        String query = "SELECT ID_HABITACION, TIPO_HABITACION, DISPONIBILIDAD FROM habitacion";
        List<Habitacion> habitaciones = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setId(rs.getInt("ID_HABITACION"));
                habitacion.setTipo(rs.getString("TIPO_HABITACION"));
                habitacion.setEstado(rs.getString("DISPONIBILIDAD"));
                habitaciones.add(habitacion);
            }
        }

        return habitaciones;
    }
}
