package Vista;

import Conexion.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ClienteGestor extends JFrame {

    private JTable tableClientes;
    private JTextField txtCodigo, txtNombre, txtApellido, txtApellido2, txtTelefono, txtDireccion, txtEmail;
    private JButton btnRegistrar, btnModificar, btnCargar, btnRegresar;

    // Conexión a la base de datos
    private Connection conexion;

    public ClienteGestor() {
        setTitle("Gestión de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Establecer conexión a la base de datos
        conectarBaseDatos();

        // Panel Principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel de Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(10, 5, 20, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        // Panel de Formulario
        panelFormulario.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Apellido 1:"));
        txtApellido = new JTextField();
        panelFormulario.add(txtApellido);

        panelFormulario.add(new JLabel("Apellido 2:"));
        txtApellido2 = new JTextField();  // Nuevo campo para apellido2
        panelFormulario.add(txtApellido2);

        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panelFormulario.add(txtEmail);

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();  // Nuevo campo para dirección
        panelFormulario.add(txtDireccion);

        btnRegistrar = new JButton("Registrar");
        btnModificar = new JButton("Modificar");
        btnCargar = new JButton("Cargar Datos");
        btnRegresar = new JButton("Regresar");
        panelFormulario.add(btnRegistrar);
        panelFormulario.add(btnModificar);
        panelFormulario.add(btnCargar);
        panelFormulario.add(btnRegistrar);
        panelFormulario.add(btnModificar);
        panelFormulario.add(btnRegresar);
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);

        // Tabla para visualizar clientes
        String[] columnas = {"ID Cliente", "Nombre", "Apellido", "Teléfono", "Email"};
        Object[][] datos = {};
        tableClientes = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tableClientes);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Botón cargar datos
        panelPrincipal.add(btnCargar, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Eventos de botones
        btnRegistrar.addActionListener(e -> registrarCliente());
        btnModificar.addActionListener(e -> modificarCliente());
        btnCargar.addActionListener(e -> cargarClientes());

        btnRegresar.addActionListener(e -> {

            Menu men = new Menu();
            men.setVisible(true);
            dispose();

        });
    }

    private void conectarBaseDatos() {
        try {
            conexion = DatabaseConnection.getConnection(); // Llamamos a la clase DatabaseConnection
            System.out.println("Conexión establecida correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.");
        }
    }

    private void registrarCliente() {
        try {
            String sql = "INSERT INTO cliente (id_cliente, nombre, apellido1, apellido2, email, telefono, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql);

            // Asignamos los valores a los parámetros
            stmt.setInt(1, Integer.parseInt(txtCodigo.getText()));  // id_cliente
            stmt.setString(2, txtNombre.getText());                 // nombre
            stmt.setString(3, txtApellido.getText());               // apellido1
            stmt.setString(4, txtApellido2.getText());              // apellido2
            stmt.setString(5, txtEmail.getText());                  // email
            stmt.setString(6, txtTelefono.getText());               // telefono
            stmt.setString(7, txtDireccion.getText());              // direccion

            // Ejecutamos la consulta de inserción
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Cliente registrado exitosamente.");
                cargarClientes();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar el cliente.");
        }
    }

    private void cargarClientes() {
        try {
            String sql = "SELECT * FROM cliente";  // Consulta para obtener todos los clientes
            PreparedStatement stmt = conexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Limpiar la tabla antes de cargar los nuevos datos
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID Cliente", "Nombre", "Apellido1", "Apellido2", "Teléfono", "Email", "Dirección"}, 0);

            // Llenar la tabla con los datos de la base de datos
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_cliente"), // id_cliente
                    rs.getString("nombre"), // nombre
                    rs.getString("apellido1"), // apellido1
                    rs.getString("apellido2"), // apellido2
                    rs.getString("telefono"), // telefono
                    rs.getString("email"), // email
                    rs.getString("direccion") // direccion
                };
                model.addRow(row);
            }

            // Establecer el modelo de la tabla con los datos obtenidos
            tableClientes.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos.");
        }
    }

    private void modificarCliente() {
        int selectedRow = tableClientes.getSelectedRow();
        if (selectedRow != -1) {
            try {
                int idCliente = (int) tableClientes.getValueAt(selectedRow, 0);  // Obtener id_cliente de la fila seleccionada
                String sql = "UPDATE cliente SET nombre = ?, apellido1 = ?, apellido2 = ?, telefono = ?, email = ?, direccion = ? WHERE id_cliente = ?";
                PreparedStatement stmt = conexion.prepareStatement(sql);

                // Asignamos los nuevos valores desde los campos del formulario
                stmt.setString(1, txtNombre.getText());
                stmt.setString(2, txtApellido.getText());
                stmt.setString(3, txtApellido2.getText());
                stmt.setString(4, txtTelefono.getText());
                stmt.setString(5, txtEmail.getText());
                stmt.setString(6, txtDireccion.getText());
                stmt.setInt(7, idCliente);

                // Ejecutamos la consulta de actualización
                int filasAfectadas = stmt.executeUpdate();
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Cliente modificado exitosamente.");
                    cargarClientes();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al modificar el cliente.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para modificar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteGestor gestor = new ClienteGestor();
            gestor.setVisible(true);
        });
    }
}
