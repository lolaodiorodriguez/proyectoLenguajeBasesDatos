import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HabitacionesGUI extends JFrame {
    private JTable tablaHabitaciones;
    private DefaultTableModel modeloTabla;
    private HabitacionConex habitacionDAO;

    public HabitacionesGUI() {
        setTitle("Gestión de Habitaciones");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar el DAO
        habitacionDAO = new HabitacionConex();

        // Configurar tabla
        modeloTabla = new DefaultTableModel(new String[]{"CODIGO", "Tipo de Habitacion", "Disponibilidad"}, 0);
        tablaHabitaciones = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaHabitaciones);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Botón para recargar
        JButton recargarBtn = new JButton("Recargar");
        recargarBtn.addActionListener(e -> cargarHabitaciones());

        panelPrincipal.add(recargarBtn, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Cargar datos al iniciar
        cargarHabitaciones();
    }

    private void cargarHabitaciones() {
        try {
            modeloTabla.setRowCount(0); // Limpiar la tabla
            List<Habitacion> habitaciones = habitacionDAO.obtenerTodasLasHabitaciones();

            for (Habitacion habitacion : habitaciones) {
                modeloTabla.addRow(new Object[]{
                    habitacion.getId(),
                    habitacion.getTipo(),
                    habitacion.getEstado()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar las habitaciones: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HabitacionesGUI().setVisible(true));
    }
}
