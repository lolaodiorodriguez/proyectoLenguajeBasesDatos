
package Vista;
import Controlador.FacturaDAO;
import Controlador.PagoDAO;
import Controlador.RestauranteDAO;
import Model.Factura;
import Model.Restaurante;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaFacturacionRestaurantes extends JFrame {

    private JTable tableRestaurantes;
    private JTable tableFacturas;
    private JButton btnVerRestaurantes;
    private JButton btnRegistrarPago;
    private JTextField txtMontoPago;
    private JTextField txtMetodoPago;

    public VistaFacturacionRestaurantes() {
        setTitle("Gestión de Restaurantes y Facturación");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

     
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(1, 2));

       
        JPanel panelRestaurantes = new JPanel();
        panelRestaurantes.setLayout(new BorderLayout());
        String[] columnasRestaurantes = {"ID Restaurante", "Nombre", "Tipo Cocina", "Capacidad"};
        DefaultTableModel modelRestaurantes = new DefaultTableModel(columnasRestaurantes, 0);
        tableRestaurantes = new JTable(modelRestaurantes);
        JScrollPane scrollPaneRestaurantes = new JScrollPane(tableRestaurantes);
        panelRestaurantes.add(scrollPaneRestaurantes, BorderLayout.CENTER);
        btnVerRestaurantes = new JButton("Ver Restaurantes");
        panelRestaurantes.add(btnVerRestaurantes, BorderLayout.SOUTH);

      
        JPanel panelFacturas = new JPanel();
        panelFacturas.setLayout(new BorderLayout());
        String[] columnasFacturas = {"ID Factura", "ID Reserva", "Fecha Emisión", "Monto Total"};
        DefaultTableModel modelFacturas = new DefaultTableModel(columnasFacturas, 0);
        tableFacturas = new JTable(modelFacturas);
        JScrollPane scrollPaneFacturas = new JScrollPane(tableFacturas);
        panelFacturas.add(scrollPaneFacturas, BorderLayout.CENTER);

    
        JPanel panelPago = new JPanel();
        panelPago.setLayout(new FlowLayout());
        JLabel lblMontoPago = new JLabel("Monto Pago: ");
        txtMontoPago = new JTextField(10);
        JLabel lblMetodoPago = new JLabel("Método Pago: ");
        txtMetodoPago = new JTextField(10);
        btnRegistrarPago = new JButton("Registrar Pago");

        panelPago.add(lblMontoPago);
        panelPago.add(txtMontoPago);
        panelPago.add(lblMetodoPago);
        panelPago.add(txtMetodoPago);
        panelPago.add(btnRegistrarPago);

        panelFacturas.add(panelPago, BorderLayout.SOUTH);

      
        panelCentral.add(panelRestaurantes);
        panelCentral.add(panelFacturas);
        add(panelCentral, BorderLayout.CENTER);

       
        btnVerRestaurantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarRestaurantes();
            }
        });

       
        btnRegistrarPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPago();
            }
        });
    }

    
    private void cargarRestaurantes() {
        RestauranteDAO restauranteDAO = new RestauranteDAO();
        List<Restaurante> restaurantes = restauranteDAO.obtenerRestaurantes();

        DefaultTableModel model = (DefaultTableModel) tableRestaurantes.getModel();
        model.setRowCount(0); 

        for (Restaurante restaurante : restaurantes) {
            model.addRow(new Object[]{restaurante.getIdRestaurante(), restaurante.getNombre(),
                                      restaurante.getTipoCocina(), restaurante.getCapacidad()});
        }
    }

    
    private void registrarPago() {
        int idReserva = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese ID de la reserva:"));
        double monto = Double.parseDouble(txtMontoPago.getText());
        String metodoPago = txtMetodoPago.getText();
        int idFactura = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese ID de la factura:"));

       PagoDAO pagoDAO = new PagoDAO();
        pagoDAO.registrarPago(idReserva, monto, metodoPago, idFactura);
        FacturaDAO fac=new FacturaDAO();
        fac.generarFactura(idReserva, monto);
        JOptionPane.showMessageDialog(this, "Pago registrado.");
        actualizarTablaFacturas();
    }

    
    private void actualizarTablaFacturas() {
        FacturaDAO facturaDAO = new FacturaDAO();
        List<Factura> facturas = facturaDAO.obtenerFacturas();

        DefaultTableModel model = (DefaultTableModel) tableFacturas.getModel();
        model.setRowCount(0); 

        for (Factura factura : facturas) {
            model.addRow(new Object[]{factura.getIdFactura(), factura.getIdReserva(),
                                      factura.getFechaEmision(), factura.getMontoTotal()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VistaFacturacionRestaurantes vista = new VistaFacturacionRestaurantes();
                vista.setVisible(true);
            }
        });
    }
}
