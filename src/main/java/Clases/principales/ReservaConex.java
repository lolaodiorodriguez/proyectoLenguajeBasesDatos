package Clases.principales;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tesci
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

public class ReservaConex {
public List<Cliente> obtenerTodosLosClientes() throws SQLException {
    String query = "SELECT id_cliente, nombre FROM clientes";
    List<Cliente> clientes = new ArrayList<>();

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id_cliente");
            String nombre = rs.getString("nombre");
            clientes.add(new Cliente(id, nombre));
        }
    }
    return clientes;
}

    public List<Reserva> getReservas(String filtro, String valor) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM reserva WHERE " + filtro + " LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + valor + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("id_reserva"));
                reserva.setIdCliente(rs.getInt("id_cliente"));
                reserva.setIdHabitacion(rs.getInt("id_habitacion"));
                reserva.setEstadoReserva(rs.getString("estado_reserva"));
                reserva.setFechaInicio(rs.getDate("fecha_inicio"));
                reserva.setFechaFin(rs.getDate("fecha_fin"));
                reservas.add(reserva);
            }
        }
        return reservas;
    }
public void crearReserva(Reserva reserva,int idCliente) {
    String query = "INSERT INTO reserva (id_reserva, id_cliente, id_hotel, fecha_inicio, fecha_fin, estado_reserva, id_habitacion) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, reserva.getIdReserva());
        stmt.setInt(2, idCliente);
        stmt.setInt(3, reserva.getIdHabitacion());
        stmt.setDate(4, new java.sql.Date(reserva.getFechaInicio().getTime()));
        stmt.setDate(5, new java.sql.Date(reserva.getFechaFin().getTime()));
        stmt.setString(6, reserva.getEstadoReserva());
        stmt.setInt(7, reserva.getIdHabitacion());

        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void actualizarReserva(Reserva reserva) throws SQLException {
        String query = "UPDATE reserva SET ID_CLIENTE=?, estado_reserva = ?, id_habitacion = ?, fecha_inicio = ?, fecha_fin = ? WHERE id_reserva = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Convertir las fechas de String a java.sql.Date
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            // Convertir la fecha de inicio de String a java.util.Date y luego a java.sql.Date
            java.util.Date fechaInicioUtil = formato.parse(reserva.getFechaInicio().toString());  // Convierte String a java.util.Date
            Date fechaInicio = new Date(fechaInicioUtil.getTime()); // Convierte java.util.Date a java.sql.Date

            // Convertir la fecha de fin de String a java.util.Date y luego a java.sql.Date
            java.util.Date fechaFinUtil = formato.parse(reserva.getFechaFin().toString());  // Convierte String a java.util.Date
            Date fechaFin = new Date(fechaFinUtil.getTime()); // Convierte java.util.Date a java.sql.Date

            // Establecer los parámetros en el PreparedStatement
            
            stmt.setInt(1, reserva.getIdCliente());
            stmt.setString(2, reserva.getEstadoReserva());
            stmt.setInt(3, reserva.getIdHabitacion());
            stmt.setDate(4, fechaInicio);  // Usamos java.sql.Date
            stmt.setDate(5, fechaFin);     // Usamos java.sql.Date
            stmt.setInt(6, reserva.getIdReserva());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // Imprimir errores para facilitar la depuración
        }
    }

    public void eliminarReserva(int idReserva) throws SQLException {
        String query = "DELETE FROM reserva WHERE id_reserva = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idReserva);
            stmt.executeUpdate();
        }
    }

    
    
    public void actualizarEstadoReserva(int idReserva, String nuevoEstado) throws SQLException {
    String query = "UPDATE reserva SET estado_reserva = ? WHERE id_reserva = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setString(1, nuevoEstado);
        stmt.setInt(2, idReserva);

        stmt.executeUpdate();
    }
}

    
    
    public List<Object[]> getReservasConNombres(String filtro, String valor) throws SQLException {
        List<Object[]> reservas = new ArrayList<>();
        String query
                = "SELECT r.id_reserva, c.nombre AS cliente, h.nombre AS hotel, ha.tipo_habitacion AS habitacion, "
                + "r.fecha_inicio, r.fecha_fin, r.estado_reserva "
                + "FROM reserva r "
                + "INNER JOIN cliente c ON r.id_cliente = c.id_cliente "
                + "INNER JOIN hotel h ON r.id_hotel = h.id_hotel "
                + "INNER JOIN habitacion ha ON r.id_habitacion = ha.id_habitacion "
                + "WHERE LOWER(" + filtro + ") LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + valor.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] reserva = {
                    rs.getInt("id_reserva"),
                    rs.getString("cliente"),
                    rs.getString("hotel"),
                    rs.getString("habitacion"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getString("estado_reserva")
                };
                reservas.add(reserva);
            }
        }
        return reservas;
    }
}
