package Controlador;

import Conexion.DatabaseConnection;
import Model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection connection;

    public ClienteDAO() {
        // Inicializar la conexión a la base de datos
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "UsuarioAdmin", "pass123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> obtenerTodosLosClientes() throws SQLException {
        String query = "SELECT id_cliente, nombre FROM cliente";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                clientes.add(new Cliente(id, nombre));
            }
        }
        return clientes;
    }

    // Método para obtener el ID de un cliente a partir de su nombre
    public int obtenerIdClientePorNombre(String nombre) throws SQLException {
        String query = "SELECT ID_CLIENTE FROM cliente WHERE NOMBRE = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_CLIENTE");
                } else {
                    throw new SQLException("Cliente no encontrado.");
                }
            }
        }
    }
}
