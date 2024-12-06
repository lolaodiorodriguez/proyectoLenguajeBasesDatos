
package Controlador;
import Conexion.DatabaseConnection;
import Model.Factura;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    public void generarFactura(int idReserva, double montoTotal) {
        String insertFacturaSQL = "INSERT INTO factura (id_factura, id_reserva, fecha_emision, monto_total) "
           + "VALUES (factura_seq.NEXTVAL, ?, ?, ?)"; 


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertFacturaSQL)) {

            stmt.setInt(1, idReserva);
            stmt.setDate(2, new Date(System.currentTimeMillis()));
            stmt.setBigDecimal(3, BigDecimal.valueOf(montoTotal));
            stmt.executeUpdate();
           
    int rowsAffected = stmt.executeUpdate();
    if (rowsAffected > 0) {
        System.out.println("Registro insertado correctamente.");
    }
            System.out.println("Factura generada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void actualizarEstadoFactura(int idFactura) {
        String queryFactura = "SELECT f.monto_total, SUM(p.monto) AS total_pagado FROM factura f "
                            + "LEFT JOIN pago p ON f.id_factura = p.id_factura "
                            + "WHERE f.id_factura = ? GROUP BY f.id_factura";

        String updateEstadoSQL = "UPDATE factura SET estado_pago = ? WHERE id_factura = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(queryFactura);
             PreparedStatement updateStmt = connection.prepareStatement(updateEstadoSQL)) {

            stmt.setInt(1, idFactura);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double montoTotal = rs.getDouble("monto_total");
                double totalPagado = rs.getDouble("total_pagado");

                String estadoPago = (montoTotal == totalPagado) ? "Pagada" : "Pendiente";

                updateStmt.setString(1, estadoPago);
                updateStmt.setInt(2, idFactura);
                updateStmt.executeUpdate();

                System.out.println("Estado de la factura actualizado a: " + estadoPago);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public List<Factura> obtenerFacturas() {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM factura";
        
        try (Connection conn =  DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Factura factura = new Factura(
                    rs.getInt("ID_FACTURA"),
                    rs.getInt("ID_RESERVA"),
                    rs.getDate("FECHA_EMISION"),
                    rs.getDouble("MONTO_TOTAL")
                );
                facturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return facturas;
    }
}
