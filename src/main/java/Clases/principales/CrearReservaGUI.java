package Clases.principales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class CrearReservaGUI extends JFrame {
    private JComboBox<ClienteItem> cbCliente;

    private JComboBox<String>  cbHotel;
    private JTextField txtHabitacion, txtFechaInicio, txtFechaFin;
    private JComboBox<String> cbEstado;
    private ReservaConex reservaC;
    private ClienteConex clienteC;
    private HotelConex hotelC;

    public CrearReservaGUI() {
        setTitle("Crear Reserva");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  // Cerrar solo esta ventana
        setLocationRelativeTo(null);

        // Inicializar DAOs
        reservaC = new ReservaConex();
        clienteC = new ClienteConex();
        hotelC = new HotelConex();

        // Panel de formulario
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        // Cliente JComboBox
        panel.add(new JLabel("Cliente:"));
        cbCliente = new JComboBox<>();
        cargarClientes();  // Método para cargar clientes
        panel.add(cbCliente);

        // Hotel JComboBox
        panel.add(new JLabel("Hotel:"));
        cbHotel = new JComboBox<>();
        cargarHoteles();  // Método para cargar hoteles
        panel.add(cbHotel);

        // Habitacion
        panel.add(new JLabel("Habitación:"));
        txtHabitacion = new JTextField();
        panel.add(txtHabitacion);

        // Fecha de inicio
        panel.add(new JLabel("Fecha Inicio:"));
        txtFechaInicio = new JTextField();
        panel.add(txtFechaInicio);

        // Fecha de fin
        panel.add(new JLabel("Fecha Fin:"));
        txtFechaFin = new JTextField();
        panel.add(txtFechaFin);

        // Estado de la reserva
        panel.add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(new String[]{"Confirmado", "Pendiente", "Cancelado"});
        panel.add(cbEstado);

        // Botones de acción
        JButton btnCrear = new JButton("Crear Reserva");
        panel.add(btnCrear);

        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnCancelar);

        add(panel);

        // Acción de crear reserva
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearReserva();
            }
        });

        // Acción de cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana de creación
            }
        });
    }
    private void cargarClientes() {
    try {
        // Obtener todos los clientes
        List<Cliente> listaClientes = clienteC.obtenerTodosLosClientes();

        // Limpiar el ComboBox antes de llenarlo
        cbCliente.removeAllItems();

        // Agregar cada cliente como un objeto ClienteItem
        for (Cliente cliente : listaClientes) {
            cbCliente.addItem(new ClienteItem(cliente.getId(), cliente.getNombre()));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + e.getMessage());
    }
}

    private void cargarHoteles() {
        try {
            List<String> hoteles = hotelC.obtenerHoteles();
            for (String hotel : hoteles) {
                cbHotel.addItem(hotel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los hoteles: " + e.getMessage());
        }
    }
private void crearReserva() {
    String hotel = (String) cbHotel.getSelectedItem();
    String habitacion = txtHabitacion.getText();
    String fechaInicio = txtFechaInicio.getText();
    String fechaFin = txtFechaFin.getText();
    String estado = (String) cbEstado.getSelectedItem();
         try {
        // Obtener el cliente seleccionado
        ClienteItem clienteSeleccionado = (ClienteItem) cbCliente.getSelectedItem();
        if (clienteSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente.");
            return;
        }
        int idCliente = clienteSeleccionado.getId();
        
        // Obtener ID del hotel
        int idHotel = hotelC.obtenerIdHotelPorNombre(hotel);

        // Convertir fechas
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaInicioUtil = formatoFecha.parse(fechaInicio);
        java.util.Date fechaFinUtil = formatoFecha.parse(fechaFin);
        Date sqlFechaInicio = new Date(fechaInicioUtil.getTime());
        Date sqlFechaFin = new Date(fechaFinUtil.getTime());

        // Crear el objeto Reserva
        Reserva reserva = new Reserva(idCliente, idHotel, Integer.parseInt(habitacion), sqlFechaInicio, sqlFechaFin, estado);

        // Guardar en la base de datos
        reservaC.crearReserva(reserva,idCliente);
        JOptionPane.showMessageDialog(this, "Reserva creada exitosamente.");
        dispose(); // Cerrar la ventana actual
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al crear la reserva: " + e.getMessage());
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Error en el formato de la fecha: " + e.getMessage());
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CrearReservaGUI().setVisible(true));
    }
}
