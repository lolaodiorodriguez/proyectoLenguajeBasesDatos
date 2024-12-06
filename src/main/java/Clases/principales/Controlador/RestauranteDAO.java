
package Controlador;

import Conexion.DatabaseConnection;
import Model.Restaurante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nelson
 */
public class RestauranteDAO {
    
    public void consultarRestaurantes(int idHotel) {
        String query = "SELECT * FROM restaurante WHERE id_hotel = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idHotel);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idRestaurante = rs.getInt("id_restaurante");
                String nombre = rs.getString("nombre");
                String tipoCocina = rs.getString("tipo_cocina");
                int capacidad = rs.getInt("capacidad");

                System.out.println("Restaurante ID: " + idRestaurante);
                System.out.println("Nombre: " + nombre);
                System.out.println("Tipo de Cocina: " + tipoCocina);
                System.out.println("Capacidad: " + capacidad);
                System.out.println("--------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public List<Restaurante> obtenerRestaurantes() {
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM restaurante";
        
        try (Connection conn =  DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Restaurante restaurante = new Restaurante(
                    rs.getInt("ID_RESTAURANTE"),
                    rs.getString("NOMBRE"),
                    rs.getString("TIPO_COCINA"),
                    rs.getInt("CAPACIDAD")
                );
                restaurantes.add(restaurante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return restaurantes;
    }
}
