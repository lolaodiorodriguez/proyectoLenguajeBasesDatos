package Controlador;

import Conexion.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;

public class PagoDAO {

    public void registrarPago(int idReserva, double monto, String metodoPago, int idFactura) {
        String insertPagoSQL = "INSERT INTO pago (id_pago, id_reserva, monto, fecha_pago, metodo_pago, id_factura) "
                             + "VALUES (pago_seq.NEXTVAL, ?, ?, ?, ?, ?)";

        String updateFacturaSQL = "UPDATE factura SET monto_total = monto_total - ? WHERE id_factura = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertPagoSQL);
             PreparedStatement updateStmt = connection.prepareStatement(updateFacturaSQL)) {

            // Insertar pago
            insertStmt.setInt(1, idReserva);
            insertStmt.setDouble(2, monto);
            insertStmt.setDate(3, new Date(System.currentTimeMillis())); 
            insertStmt.setString(4, metodoPago);
            insertStmt.setInt(5, idFactura);
            insertStmt.executeUpdate();

            updateStmt.setDouble(1, monto);
            updateStmt.setInt(2, idFactura);
            updateStmt.executeUpdate();

            System.out.println("Pago registrado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarPagos(int idFactura) {
        String query = "SELECT p.id_pago, p.monto, p.fecha_pago, p.metodo_pago "
                     + "FROM pago p "
                     + "WHERE p.id_factura = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idFactura);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idPago = rs.getInt("id_pago");
                double monto = rs.getDouble("monto");
                Date fechaPago = rs.getDate("fecha_pago");
                String metodoPago = rs.getString("metodo_pago");

                System.out.println("Pago ID: " + idPago);
                System.out.println("Monto: " + monto);
                System.out.println("Fecha Pago: " + fechaPago);
                System.out.println("Método de Pago: " + metodoPago);
                System.out.println("--------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
