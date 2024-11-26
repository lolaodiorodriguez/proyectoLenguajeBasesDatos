package Clases.principales;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaGUI extends JFrame {
    private JTable tablaReservas;
    private DefaultTableModel modeloTabla;
    private JTextField filtroField;
    private JComboBox<String> filtroCombo;
    private ReservaConex reservaC;

    public ReservaGUI() {
        setTitle("Gestión de Reservas");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       
        reservaC = new ReservaConex();

       
        modeloTabla = new DefaultTableModel(new String[]{
            "ID", "Cliente", "Hotel", "Habitación", "Inicio", "Fin", "Estado"}, 0);
        tablaReservas = new JTable(modeloTabla);
        add(new JScrollPane(tablaReservas), BorderLayout.CENTER);

        
        JPanel filtroPanel = new JPanel();
        filtroPanel.setLayout(new FlowLayout());

        filtroPanel.add(new JLabel("Filtrar por:"));
        filtroCombo = new JComboBox<>(new String[]{"Cliente", "Estado", "Fecha"});
        filtroPanel.add(filtroCombo);

        filtroField = new JTextField(15);
        filtroPanel.add(filtroField);

        JButton buscarBtn = new JButton("Buscar");
        filtroPanel.add(buscarBtn);

        add(filtroPanel, BorderLayout.NORTH);

       
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout());

        JButton crearBtn = new JButton("Crear Reserva");
        JButton modificarBtn = new JButton("Modificar Reserva");
        JButton cancelarBtn = new JButton("Cancelar Reserva");

        botonesPanel.add(crearBtn);
        botonesPanel.add(modificarBtn);
        botonesPanel.add(cancelarBtn);

        add(botonesPanel, BorderLayout.SOUTH);

      
        buscarBtn.addActionListener(e -> buscarReservas());
        crearBtn.addActionListener(e -> crearReserva());
        modificarBtn.addActionListener(e -> modificarReserva());
        cancelarBtn.addActionListener(e -> eliminarReserva());
    }
    private void buscarReservas() {
        try {
            String filtroSeleccionado = filtroCombo.getSelectedItem().toString().toLowerCase();
            String filtroColumna;
            if ("cliente".equals(filtroSeleccionado)) {
                filtroColumna = "c.nombre";
            } else if ("estado".equals(filtroSeleccionado)) {
                filtroColumna = "r.estado_reserva";
            } else if ("fecha".equals(filtroSeleccionado)) {
                filtroColumna = "r.fecha_inicio";
            } else {
                filtroColumna = "r.estado_reserva"; // Valor por defecto
            }

            String valor = filtroField.getText();

            modeloTabla.setRowCount(0); // Limpiar la tabla

            for (Object[] reserva : reservaC.getReservasConNombres(filtroColumna, valor)) {
                modeloTabla.addRow(reserva);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar reservas: " + ex.getMessage());
        }
    }
    private void crearReserva() {
        JOptionPane.showMessageDialog(this, "Abriendo Ventana de Crear Reserva.");
      
         CrearReservaGUI creando = new CrearReservaGUI();
       creando.setVisible(true);
    }
    private void modificarReserva() {
        int filaSeleccionada = tablaReservas.getSelectedRow();
        if (filaSeleccionada >= 0) {
            Object[] datosReserva = obtenerDatosDeFila(filaSeleccionada);
            mostrarFormularioReserva("Modificar Reserva", datosReserva);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una reserva para modificar.");
        }
    }

 private void mostrarFormularioReserva(String titulo, Object[] datosReserva) {
        JDialog dialogo = new JDialog(this, titulo, true);
        dialogo.setSize(400, 300);
        dialogo.setLayout(new GridLayout(7, 2));
        dialogo.setLocationRelativeTo(this);

        // Campos de entrada
        JTextField clienteField = new JTextField(datosReserva != null ? datosReserva[1].toString() : "");
        JTextField hotelField = new JTextField(datosReserva != null ? datosReserva[2].toString() : "");
        JTextField habitacionField = new JTextField(datosReserva != null ? datosReserva[3].toString() : "");
        JTextField inicioField = new JTextField(datosReserva != null ? datosReserva[4].toString() : "");
        JTextField finField = new JTextField(datosReserva != null ? datosReserva[5].toString() : "");
        JTextField estadoField = new JTextField(datosReserva != null ? datosReserva[6].toString() : "");

        
        dialogo.add(new JLabel("Cliente:"));
        dialogo.add(clienteField);
        dialogo.add(new JLabel("Hotel:"));
        dialogo.add(hotelField);
        dialogo.add(new JLabel("Habitación:"));
        dialogo.add(habitacionField);
        dialogo.add(new JLabel("Fecha Inicio:"));
        dialogo.add(inicioField);
        dialogo.add(new JLabel("Fecha Fin:"));
        dialogo.add(finField);
        dialogo.add(new JLabel("Estado:"));
        dialogo.add(estadoField);

        // Botones
        JButton guardarBtn = new JButton("Guardar");
        JButton cancelarBtn = new JButton("Cancelar");

        guardarBtn.addActionListener(e -> {
            try {
                modificarReservaEnBD((int) datosReserva[0], clienteField.getText(), hotelField.getText(),
                        habitacionField.getText(), inicioField.getText(), finField.getText(),
                        estadoField.getText());
            } catch (ParseException ex) {
                Logger.getLogger(ReservaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            dialogo.dispose();
        });

        cancelarBtn.addActionListener(e -> dialogo.dispose());

        dialogo.add(guardarBtn);
        dialogo.add(cancelarBtn);

        dialogo.setVisible(true);
    }
 private void modificarReservaEnBD(int id, String cliente, String hotel, String habitacion, String inicio, String fin, String estado) throws ParseException {
    try {
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaInicioUtil = formatoFecha.parse(inicio);
        java.util.Date fechaFinUtil = formatoFecha.parse(fin);

        java.sql.Date fechaInicio = new java.sql.Date(fechaInicioUtil.getTime());
        java.sql.Date fechaFin = new java.sql.Date(fechaFinUtil.getTime());

        
        Reserva reserva = new Reserva();
        reserva.setIdReserva(id);
        reserva.setIdCliente(Integer.parseInt(cliente));
        reserva.setIdHabitacion(Integer.parseInt(habitacion)); 
        reserva.setEstadoReserva(estado);
        reserva.setFechaInicio(fechaInicio); 
        reserva.setFechaFin(fechaFin);       

      
        reservaC.actualizarReserva(reserva);

        JOptionPane.showMessageDialog(this, "Reserva actualizada con éxito.");
        buscarReservas(); 
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar la reserva: " + e.getMessage());
        e.printStackTrace();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El ID de la habitación debe ser un número válido.");
    }
}
 private void eliminarReserva() {
    int filaSeleccionada = tablaReservas.getSelectedRow();
    if (filaSeleccionada >= 0) {
        int idReserva = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        int confirmacion = JOptionPane.showConfirmDialog(
            this, 
            "¿Estás seguro de que deseas cancelar la reserva ID " + idReserva + "?", 
            "Confirmar cancelación", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                
                reservaC.actualizarEstadoReserva(idReserva, "cancelada");

                
                JOptionPane.showMessageDialog(this, "La reserva ha sido cancelada exitosamente.");

               
                buscarReservas();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cancelar la reserva: " + ex.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona una reserva para cancelar.");
    }
}
 private Object[] obtenerDatosDeFila(int fila) {
        Object[] datos = new Object[modeloTabla.getColumnCount()];
        for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
            datos[i] = modeloTabla.getValueAt(fila, i);
        }
        return datos;
    }
 public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReservaGUI().setVisible(true));
    }
}
//.