package Clases.principales;


import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ClienteGestor extends JFrame {

    private JTable tableClientes;
    private JTextField txtCodigo, txtNombre, txtApellido, txtApellido2, txtTelefono, txtDireccion, txtEmail;
    private JButton btnRegistrar, btnModificar, btnCargar;

    private Connection conexion;

    public ClienteGestor() {
        setTitle("Gestión de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        conectarBaseDatos();

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

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
        txtApellido2 = new JTextField();
        panelFormulario.add(txtApellido2);

        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panelFormulario.add(txtEmail);

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        btnRegistrar = new JButton("Registrar");
        btnModificar = new JButton("Modificar");
        btnCargar = new JButton("Cargar Datos");

        panelFormulario.add(btnRegistrar);
        panelFormulario.add(btnModificar);
        panelFormulario.add(btnCargar);

        panelFormulario.add(btnRegistrar);
        panelFormulario.add(btnModificar);

        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);

        String[] columnas = {"ID Cliente", "Nombre", "Apellido", "Teléfono", "Email"};
        Object[][] datos = {};
        tableClientes = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tableClientes);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(btnCargar, BorderLayout.SOUTH);

        add(panelPrincipal);

        btnRegistrar.addActionListener(e -> registrarCliente());
        btnModificar.addActionListener(e -> modificarCliente());
        btnCargar.addActionListener(e -> cargarClientes());
    }

    private void conectarBaseDatos() {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String user = "C##NEL";
            String password = "123456";

            conexion = DriverManager.getConnection(url, user, password);
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

           
            stmt.setInt(1, Integer.parseInt(txtCodigo.getText()));  
            stmt.setString(2, txtNombre.getText());                 
            stmt.setString(3, txtApellido.getText());               
            stmt.setString(4, txtApellido2.getText());              
            stmt.setString(5, txtEmail.getText());                  
            stmt.setString(6, txtTelefono.getText());               
            stmt.setString(7, txtDireccion.getText());              

         
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
            String sql = "SELECT * FROM cliente";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID Cliente", "Nombre", "Apellido1", "Apellido2", "Teléfono", "Email", "Dirección"}, 0);

            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("apellido1"),
                    rs.getString("apellido2"),
                    rs.getString("telefono"), 
                    rs.getString("email"), 
                    rs.getString("direccion") 
                };
                model.addRow(row);
            }

            
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
                int idCliente = (int) tableClientes.getValueAt(selectedRow, 0);  
                String sql = "UPDATE cliente SET nombre = ?, apellido1 = ?, apellido2 = ?, telefono = ?, email = ?, direccion = ? WHERE id_cliente = ?";
                PreparedStatement stmt = conexion.prepareStatement(sql);

                stmt.setString(1, txtNombre.getText());
                stmt.setString(2, txtApellido.getText());
                stmt.setString(3, txtApellido2.getText());
                stmt.setString(4, txtTelefono.getText());
                stmt.setString(5, txtEmail.getText());
                stmt.setString(6, txtDireccion.getText());
                stmt.setInt(7, idCliente);

               
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
//.