package Vista;

import Model.Habitacion;
import Controlador.HabitacionDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HabitacionesGUI extends JFrame {

    private JTable tablaHabitaciones;
    private DefaultTableModel modeloTabla;
    private HabitacionDAO habitacionDAO;

    public HabitacionesGUI() {
        setTitle("Gestión de Habitaciones");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar el DAO
        habitacionDAO = new HabitacionDAO();

        // Configurar tabla
        modeloTabla = new DefaultTableModel(new String[]{"CODIGO", "Tipo de Habitacion", "Disponibilidad"}, 0);
        tablaHabitaciones = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaHabitaciones);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Panel superior para el botón "Regresar"
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton regresarBtn = new JButton("Regresar");
        regresarBtn.addActionListener(e -> {
            dispose();

            Menu men = new Menu();
            men.setVisible(true);
            dispose();
        });
        panelSuperior.add(regresarBtn);
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

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
