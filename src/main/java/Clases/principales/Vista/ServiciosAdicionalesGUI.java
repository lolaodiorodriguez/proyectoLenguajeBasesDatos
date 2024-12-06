package Vista;

import Vista.ServicioAdicional;
import Controlador.ServicioAdicionalDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiciosAdicionalesGUI extends JFrame {
    private JTable tablaServicios;
    private DefaultTableModel modeloTabla;
    private ServicioAdicionalDAO servicioAdicionalDAO;

    public ServiciosAdicionalesGUI() {
        setTitle("Gestión de Servicios Adicionales");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        servicioAdicionalDAO = new ServicioAdicionalDAO();

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Descripción", "Fecha", "Precio", "ID Reserva"}, 0);
        tablaServicios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaServicios);


        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

       
        JPanel panelBotones = new JPanel();
        JButton agregarBtn = new JButton("Agregar");
        JButton editarBtn = new JButton("Editar");
        JButton recargarBtn = new JButton("Recargar");

        panelBotones.add(agregarBtn);
        panelBotones.add(editarBtn);
        panelBotones.add(recargarBtn);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        agregarBtn.addActionListener(e -> mostrarFormularioServicio(null));
        editarBtn.addActionListener(e -> editarServicioSeleccionado());
        recargarBtn.addActionListener(e -> cargarServicios());

        add(panelPrincipal);

       
        cargarServicios();
    }

    private void cargarServicios() {
        try {
            modeloTabla.setRowCount(0); 
            List<ServicioAdicional> servicios = servicioAdicionalDAO.obtenerTodosLosServicios();

            for (ServicioAdicional servicio : servicios) {
                modeloTabla.addRow(new Object[]{
                    servicio.getIdServicio(),
                    servicio.getDescripcion(),
                    servicio.getFecha(),
                    servicio.getPrecio(),
                    servicio.getIdReserva()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los servicios: " + ex.getMessage());
        }
    }

    private void mostrarFormularioServicio(ServicioAdicional servicioExistente) {
        JDialog dialogo = new JDialog(this, servicioExistente == null ? "Agregar Servicio" : "Editar Servicio", true);
        dialogo.setSize(400, 300);
        dialogo.setLayout(new GridLayout(5, 2));
        dialogo.setLocationRelativeTo(this);

        
        JTextField descripcionField = new JTextField(servicioExistente != null ? servicioExistente.getDescripcion() : "");
        JTextField fechaField = new JTextField(servicioExistente != null ? servicioExistente.getFecha().toString() : "");
        JTextField precioField = new JTextField(servicioExistente != null ? String.valueOf(servicioExistente.getPrecio()) : "");
        JTextField reservaField = new JTextField(servicioExistente != null ? String.valueOf(servicioExistente.getIdReserva()) : "");

        dialogo.add(new JLabel("Descripción:"));
        dialogo.add(descripcionField);
        dialogo.add(new JLabel("Fecha (YYYY-MM-DD):"));
        dialogo.add(fechaField);
        dialogo.add(new JLabel("Precio:"));
        dialogo.add(precioField);
        dialogo.add(new JLabel("ID Reserva:"));
        dialogo.add(reservaField);

       
        JButton guardarBtn = new JButton("Guardar");
        JButton cancelarBtn = new JButton("Cancelar");

        guardarBtn.addActionListener(e -> {
            try {
                ServicioAdicional servicio = new ServicioAdicional(
                        servicioExistente != null ? servicioExistente.getIdServicio() : 0,
                        descripcionField.getText(),
                        Date.valueOf(fechaField.getText()),
                        Double.parseDouble(precioField.getText()),
                        Integer.parseInt(reservaField.getText())
                );

                if (servicioExistente == null) {
                    servicioAdicionalDAO.agregarServicio(servicio);
                } else {
                    servicioAdicionalDAO.actualizarServicio(servicio);
                }

                cargarServicios();
                dialogo.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialogo, "Error al guardar el servicio: " + ex.getMessage());
            }
        });

        cancelarBtn.addActionListener(e -> dialogo.dispose());

        dialogo.add(guardarBtn);
        dialogo.add(cancelarBtn);

        dialogo.setVisible(true);
    }

    private void editarServicioSeleccionado() {
        int filaSeleccionada = tablaServicios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idServicio = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            try {
                ServicioAdicional servicio = servicioAdicionalDAO.obtenerServicioPorId(idServicio);
                mostrarFormularioServicio(servicio);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar el servicio: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un servicio para editar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServiciosAdicionalesGUI().setVisible(true));
    }
}
