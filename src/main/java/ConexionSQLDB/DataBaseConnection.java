
package ConexionSQLDB;

import Clases.principales.Clientes;
import Clases.principales.Habitacion;
import Clases.principales.Hotel;
import Clases.principales.Reserva;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {
    
    public static Connection getConneccion(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");  // Cargar el driver Oracle
            String myDB = "jdbc:oracle:thin:@localhost:1522:XE"; // URL de conexión
            String usuario = "proyecto"; // Usuario de la base de datos
            String password = "1234"; // Contraseña
            Connection cnx = DriverManager.getConnection(myDB, usuario, password);
            return cnx;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error al conectar con la base de datos: " + ex.getMessage());
        }
        return null;
    }
    
    //Clientes    
    public static Clientes getClienteById(int idCliente) {
    Clientes cliente = null;
    String query = "SELECT * FROM cliente WHERE id_cliente = ?";
    try (Connection conn = getConneccion(); 
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, idCliente);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            cliente = new Clientes();
            cliente.setId_cliente(rs.getInt("id_cliente"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setApellido1(rs.getString("apellido1"));
            cliente.setApellido2(rs.getString("apellido2"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefono(rs.getString("telefono"));
            cliente.setDireccion(rs.getString("direccion"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return cliente;
}

       
    ///Metodos de las reservas    
    public static List<Reserva> getReservas() {
    List<Reserva> reservas = new ArrayList<>();
    String query = "SELECT * FROM reserva";  // Consulta para obtener todas las reservas
    
    try (Connection conn = getConneccion(); 
         Statement stmt = conn.createStatement(); 
         ResultSet rs = stmt.executeQuery(query)) {
        
        while (rs.next()) {
            int idReserva = rs.getInt("id_reserva");
            int idCliente = rs.getInt("id_cliente");
            int idHotel = rs.getInt("id_hotel");
            Date fechaInicio = rs.getDate("fecha_inicio");
            Date fechaFin = rs.getDate("fecha_fin");
            String estadoReserva = rs.getString("estado_reserva");
            int idHabitacion = rs.getInt("id_habitacion");
            
            // Obtener los datos completos de las entidades relacionadas
            Clientes cliente = getClienteById(idCliente);
            Hotel hotel = getHotelById(idHotel);
            Habitacion habitacion = getHabitacionById(idHabitacion);
            
            // Crear la reserva con los datos completos
            Reserva reserva = new Reserva(idReserva, cliente, hotel, fechaInicio, fechaFin, estadoReserva, habitacion);
            reservas.add(reserva);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener las reservas: " + e.getMessage());
    }
    return reservas;
    }
    
    public static boolean cancelarReserva(int idReserva) {
    String call = "{CALL CancelarReserva(?)}"; 
    try (Connection conn = getConneccion();
         CallableStatement stmt = conn.prepareCall(call)) {
        stmt.setInt(1, idReserva); 
        stmt.execute();
        return true;
    } catch (SQLException e) {
        System.out.println("Error al cancelar la reserva: " + e.getMessage());
        return false; 
    }
    }
    
    public static boolean agregarReserva(String idReserva, String idCliente, String idHotel, String fechaInicio,
                                     String fechaFin, String estadoReserva, String idHabitacion) {
        // Llamar al procedimiento almacenado
        String sql = "{CALL AgregarReserva(?, ?, ?, ?, ?, ?, ?)}"; 

        try (Connection conn = getConneccion(); 
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Asignar los parámetros
            stmt.setInt(1, Integer.parseInt(idReserva));
            stmt.setInt(2, Integer.parseInt(idCliente));
            stmt.setInt(3, Integer.parseInt(idHotel));
            stmt.setDate(4, java.sql.Date.valueOf(fechaInicio));  
            stmt.setDate(5, java.sql.Date.valueOf(fechaFin));  
            stmt.setString(6, estadoReserva);
            stmt.setInt(7, Integer.parseInt(idHabitacion));

            // Ejecutar el procedimiento
            stmt.execute();
            return true;  // La reserva se agregó correctamente
        } catch (SQLException e) {
            System.out.println("Error al agregar la reserva: " + e.getMessage());
            return false;  // Si ocurre un error, se devuelve false
        }
    }


    
    //Metodos para los Hoteles    
    public static Hotel getHotelById(int idHotel) {
    Hotel hotel = null;
    String query = "SELECT * FROM hotel WHERE id_hotel = ?";
    try (Connection conn = getConneccion(); 
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, idHotel);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            hotel = new Hotel();
            hotel.setIdHotel(rs.getInt("id_hotel"));
            hotel.setNombre(rs.getString("nombre"));
            hotel.setDireccion(rs.getString("direccion"));
            hotel.setTelefono(rs.getString("telefono"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return hotel;
    }

    //Metodo para obtener las Habitaciones    
    public static Habitacion getHabitacionById(int idHabitacion) {
    Habitacion habitacion = null;
    String query = "SELECT * FROM habitacion WHERE id_habitacion = ?";
    try (Connection conn = getConneccion(); 
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, idHabitacion);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            habitacion = new Habitacion();
            habitacion.setIdHabitacion(rs.getInt("id_habitacion"));
            habitacion.setTipoHabitacion(rs.getString("tipo_habitacion"));
            habitacion.setPrecio(rs.getDouble("precio"));
            habitacion.setCapacidad(rs.getInt("capacidad"));
            habitacion.setDisponibilidad(rs.getInt("disponibilidad"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return habitacion;
    }


    
    /*public static void main(String[] args) {
        List<Clientes> clientes = getClientes();
        for (Clientes cliente : clientes) {
            System.out.println("ID: " + cliente.getId_cliente() + ", Nombre: " + cliente.getNombre());
        }
    }*/
}

