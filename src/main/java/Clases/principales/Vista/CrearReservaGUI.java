package Vista;

import Model.ClienteItem;
import Model.Cliente;
import Controlador.HotelDAO;
import Controlador.ClienteDAO;
import Model.Reserva;
import Controlador.ReservaDAO;
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
    private ReservaDAO reservaDAO;
    private ClienteDAO clienteDAO;
    private HotelDAO hotelDAO;

    public CrearReservaGUI() {
        setTitle("Crear Reserva");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        setLocationRelativeTo(null);

        reservaDAO = new ReservaDAO();
        clienteDAO = new ClienteDAO();
        hotelDAO = new HotelDAO();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        panel.add(new JLabel("Cliente:"));
        cbCliente = new JComboBox<>();
        cargarClientes(); 
        panel.add(cbCliente);

        // Hotel JComboBox
        panel.add(new JLabel("Hotel:"));
        cbHotel = new JComboBox<>();
        cargarHoteles();  
        panel.add(cbHotel);

        panel.add(new JLabel("Habitación:"));
        txtHabitacion = new JTextField();
        panel.add(txtHabitacion);

        panel.add(new JLabel("Fecha Inicio:"));
        txtFechaInicio = new JTextField();
        panel.add(txtFechaInicio);

        panel.add(new JLabel("Fecha Fin:"));
        txtFechaFin = new JTextField();
        panel.add(txtFechaFin);

        panel.add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(new String[]{"Confirmado", "Pendiente", "Cancelado"});
        panel.add(cbEstado);

        JButton btnCrear = new JButton("Crear Reserva");
        panel.add(btnCrear);

        JButton btnCancelar = new JButton("Cancelar");
        panel.add(btnCancelar);

        add(panel);

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearReserva();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
    }
    private void cargarClientes() {
    try {
    
        List<Cliente> listaClientes = clienteDAO.obtenerTodosLosClientes();

        cbCliente.removeAllItems();

        for (Cliente cliente : listaClientes) {
            cbCliente.addItem(new ClienteItem(cliente.getId(), cliente.getNombre()));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + e.getMessage());
    }
}

    private void cargarHoteles() {
        try {
            List<String> hoteles = hotelDAO.obtenerHoteles();
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
      
        ClienteItem clienteSeleccionado = (ClienteItem) cbCliente.getSelectedItem();
        if (clienteSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente.");
            return;
        }
        int idCliente = clienteSeleccionado.getId();
        
    
        int idHotel = hotelDAO.obtenerIdHotelPorNombre(hotel);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaInicioUtil = formatoFecha.parse(fechaInicio);
        java.util.Date fechaFinUtil = formatoFecha.parse(fechaFin);
        Date sqlFechaInicio = new Date(fechaInicioUtil.getTime());
        Date sqlFechaFin = new Date(fechaFinUtil.getTime());

        Reserva reserva = new Reserva(idCliente, idHotel, Integer.parseInt(habitacion), sqlFechaInicio, sqlFechaFin, estado);

        reservaDAO.crearReserva(reserva,idCliente);
        JOptionPane.showMessageDialog(this, "Reserva creada exitosamente.");
        dispose(); 
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
