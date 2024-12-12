package Controlador;

import Conexion.DatabaseConnection;
import Model.Suministro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuministroDAO {

    private Connection connection;

    public SuministroDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(SuministroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearSuministro(Suministro suministro) throws SQLException {
        String sql = "INSERT INTO suministro (id_suministro, nombre, descripcion, cantidad, precio_unitario, id_proveedor, id_hotel) VALUES (suministro_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, suministro.getNombre());
            statement.setString(2, suministro.getDescripcion());
            statement.setInt(3, suministro.getCantidad());
            statement.setDouble(4, suministro.getPrecioUnitario());
            statement.setInt(5, suministro.getIdProveedor());
            statement.setInt(6, suministro.getIdHotel());
            statement.executeUpdate();
        }
    }

    public List<Suministro> obtenerSuministros() throws SQLException {
        List<Suministro> suministros = new ArrayList<>();
        String sql = "SELECT * FROM suministro";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Suministro suministro = new Suministro(
                        resultSet.getInt("id_suministro"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getInt("cantidad"),
                        resultSet.getDouble("precio_unitario"),
                        resultSet.getInt("id_proveedor"),
                        resultSet.getInt("id_hotel")
                );
                suministros.add(suministro);
            }
        }
        return suministros;
    }

    public List<Suministro> obtenerSuministrosBuscar(int id) throws SQLException {
        List<Suministro> suministros = new ArrayList<>();
        String sql = "SELECT * FROM suministro where id_suministro= '" + id + "'";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Suministro suministro = new Suministro(
                        resultSet.getInt("id_suministro"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getInt("cantidad"),
                        resultSet.getDouble("precio_unitario"),
                        resultSet.getInt("id_proveedor"),
                        resultSet.getInt("id_hotel")
                );
                suministros.add(suministro);
            }
        }
        return suministros;
    }

    public void modificarSuministro(Suministro suministro, int id) throws SQLException {
        String sql = "UPDATE suministro SET nombre = ?, descripcion = ?, cantidad = ?, precio_unitario = ?, id_proveedor = ?, id_hotel = ? WHERE id_suministro = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, suministro.getNombre());
            statement.setString(2, suministro.getDescripcion());
            statement.setInt(3, suministro.getCantidad());
            statement.setDouble(4, suministro.getPrecioUnitario());
            statement.setInt(5, suministro.getIdProveedor());
            statement.setInt(6, suministro.getIdHotel());
            statement.setInt(7, id);
            statement.executeUpdate();
        }
    }

    public void eliminarSuministro(int idSuministro) throws SQLException {
        String sql = "DELETE FROM suministro WHERE id_suministro = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idSuministro);
            statement.executeUpdate();
        }
    }
}
