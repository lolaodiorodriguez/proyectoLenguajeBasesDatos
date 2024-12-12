package Controlador;

import Conexion.DatabaseConnection;
import Model.Restaurante;
import Model.Suministro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestauranteDAO {

    public List<Restaurante> consultarRestaurantes2(int idHotel) {
        String query = "SELECT * FROM restaurante WHERE id_restaurante = ?";
        List<Restaurante> restaurantes = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idHotel);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idRestaurante = rs.getInt("id_restaurante");
                String nombre = rs.getString("nombre");
                String tipoCocina = rs.getString("tipo_cocina");
                int capacidad = rs.getInt("capacidad");
                int idhotel = rs.getInt("ID_HOTEL");

                Restaurante restaurante = new Restaurante(idRestaurante, nombre, tipoCocina, capacidad, idhotel);
                restaurantes.add(restaurante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantes;
    }

    public List<Restaurante> consultarRestaurantes(int idHotel) {
        String query = "SELECT * FROM restaurante WHERE id_hotel = ?";
        List<Restaurante> restaurantes = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idHotel);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idRestaurante = rs.getInt("id_restaurante");
                String nombre = rs.getString("nombre");
                String tipoCocina = rs.getString("tipo_cocina");
                int capacidad = rs.getInt("capacidad");
                int idhotel = rs.getInt("ID_HOTEL");

                Restaurante restaurante = new Restaurante(idRestaurante, nombre, tipoCocina, capacidad, idhotel);
                restaurantes.add(restaurante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantes;
    }

    public List<Restaurante> obtenerRestaurantes() {
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM restaurante";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Restaurante restaurante = new Restaurante(
                        rs.getInt("ID_RESTAURANTE"),
                        rs.getString("NOMBRE"),
                        rs.getString("TIPO_COCINA"),
                        rs.getInt("CAPACIDAD"),
                        rs.getInt("ID_HOTEL")
                );
                restaurantes.add(restaurante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantes;
    }

    public void CrearRegistro(Restaurante res) {

        String sql = "INSERT INTO restaurante (ID_RESTAURANTE, NOMBRE, TIPO_COCINA, CAPACIDAD, ID_HOTEL) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {

            statement.setInt(1, res.getIdRestaurante());
            statement.setString(2, res.getNombre());
            statement.setString(3, res.getTipoCocina());
            statement.setInt(4, res.getCapacidad());
            statement.setInt(5, res.getIDHotel());
            statement.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void modificarRestaurante(Restaurante res) {
        String sql = "UPDATE restaurante SET NOMBRE = ?, TIPO_COCINA = ?, CAPACIDAD = ?, ID_HOTEL = ? WHERE ID_RESTAURANTE = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            statement.setString(1, res.getNombre());
            statement.setString(2, res.getTipoCocina());
            statement.setInt(3, res.getCapacidad());
            statement.setDouble(4, res.getIDHotel());
            statement.setInt(5, res.getIdRestaurante());
            statement.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void eliminarRestaurante(int idSuministro) {
        String sql = "DELETE FROM restaurante WHERE ID_RESTAURANTE = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, idSuministro);
            statement.executeUpdate();
        } catch (Exception e) {

        }
    }
}
