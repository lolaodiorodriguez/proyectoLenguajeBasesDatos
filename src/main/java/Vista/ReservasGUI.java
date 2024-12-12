/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.ClienteDAO;
import Controlador.HotelDAO;
import Controlador.ReservaDAO;
import Model.Reserva;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tesci
 */
public class ReservasGUI extends javax.swing.JFrame {

    private DefaultTableModel modeloTabla;
    private ClienteDAO clienteDAO;
    private HotelDAO hotelDAO;
    private ReservaDAO reservaDAO;

    /**
     * Creates new form ReservasGUI
     */
    public ReservasGUI() {
        initComponents();
        
        reservaDAO = new ReservaDAO();
        clienteDAO = new ClienteDAO();
        hotelDAO = new HotelDAO();
        MostraTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("SISTEMA DE RESERVAS");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Estado", "Fecha" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton2.setText("Crear Reserva");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Modificar Reserva");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("CANCELAR RESERVA");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtro de Busqueda");

        jButton5.setText("Regresar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Actualizar Tabla");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(257, 257, 257))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(jButton5))
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
Menu principal= new Menu();
principal.setVisible(true);
this.dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(null, "Modificacion de Datos");
        int idcodigo=Integer.parseInt(JOptionPane.showInputDialog(null, "Escribe ID:"));
        String idcliente=JOptionPane.showInputDialog(null, "Escribe ID del Cliente:");
        String idhotel=JOptionPane.showInputDialog(null, "Escribe ID del HOTEL:");
        String idhabitacion=JOptionPane.showInputDialog(null, "Escribe ID del Habitacion:");
        String fechainicio=JOptionPane.showInputDialog(null, "Escribe Fecha que inicio su Reserva(YYYY-MM-DD):");
        String fechafinal=JOptionPane.showInputDialog(null, "Escribe Fecha que Fin su Reserva(YYYY-MM-DD):");
        String estado_de_reserva=JOptionPane.showInputDialog(null, "Escribe ID del Habitacion:");
        
        try {
            modificarReservaEnBD(idcodigo,idcliente,idhotel,idhabitacion,fechainicio,fechafinal,estado_de_reserva);
            MostraTabla();
        } catch (ParseException ex) {
            Logger.getLogger(ReservasGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
CrearReservaGUI creando=new  CrearReservaGUI();    
creando.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
eliminarReserva();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
MostraTabla();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if(jComboBox1.getSelectedItem().equals("Cliente")){
         MostraTablaconFiltroNombre("nombre", jTextField1.getText()); 
    } 
    if(jComboBox1.getSelectedItem().equals("Estado")){
                 MostraTablaconFiltroEstado("nombre", jTextField1.getText()); 

    }
    if(jComboBox1.getSelectedItem().equals("Fecha")){
        MostraTablaconFiltroFecha("nombre", jTextField1.getText()); 
    }
         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReservasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservasGUI().setVisible(true);
            }
        });
    }
    
public void MostraTabla(){
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("CODIGO DE RESERVA");
        modelo.addColumn("Nombre de Cliente");
        modelo.addColumn("Nombre de Hotel");
        modelo.addColumn("Habitacion");
        modelo.addColumn("FECHA DE INICIO");
        modelo.addColumn("FECHA DE FIN");
        modelo.addColumn("ESTADO_RESERVA");
        List<Object[]> reservas = new ArrayList<>();
        reservas = reservaDAO.obtenerReserva();

         try {
            for (Object[] reserva : reservas) {
            modelo.addRow(reserva);
        } 
        }catch(Exception e){
            
        }

        // Asignar el modelo a la tabla
        jTable1.setModel(modelo);

    }


public void MostraTablaconFiltroNombre(String filtro,String valor){
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("CODIGO DE RESERVA");
        modelo.addColumn("Nombre de Cliente");
        modelo.addColumn("Nombre de Hotel");
        modelo.addColumn("Habitacion");
        modelo.addColumn("FECHA DE INICIO");
        modelo.addColumn("FECHA DE FIN");
        modelo.addColumn("ESTADO_RESERVA");
        List<Object[]> reservas = new ArrayList<>();
        try {
            reservas = reservaDAO.getReservasConNombres(filtro, valor);
        } catch (SQLException ex) {
            Logger.getLogger(ReservasGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

         try {
            for (Object[] reserva : reservas) {
            modelo.addRow(reserva);
        } 
        }catch(Exception e){
            
        }

        // Asignar el modelo a la tabla
        jTable1.setModel(modelo);

    }
 
public void MostraTablaconFiltroEstado(String filtro,String valor){
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("CODIGO DE RESERVA");
        modelo.addColumn("Nombre de Cliente");
        modelo.addColumn("Nombre de Hotel");
        modelo.addColumn("Habitacion");
        modelo.addColumn("FECHA DE INICIO");
        modelo.addColumn("FECHA DE FIN");
        modelo.addColumn("ESTADO_RESERVA");
        List<Object[]> reservas = new ArrayList<>();
        try {
            reservas = reservaDAO.getReservasConNombres2(filtro, valor);
        } catch (SQLException ex) {
            Logger.getLogger(ReservasGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

         try {
            for (Object[] reserva : reservas) {
            modelo.addRow(reserva);
        } 
        }catch(Exception e){
            
        }

        // Asignar el modelo a la tabla
        jTable1.setModel(modelo);

    }
 public void MostraTablaconFiltroFecha(String filtro,String valor){
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("CODIGO DE RESERVA");
        modelo.addColumn("Nombre de Cliente");
        modelo.addColumn("Nombre de Hotel");
        modelo.addColumn("Habitacion");
        modelo.addColumn("FECHA DE INICIO");
        modelo.addColumn("FECHA DE FIN");
        modelo.addColumn("ESTADO_RESERVA");
        List<Object[]> reservas = new ArrayList<>();
        try {
            reservas = reservaDAO.getReservasConNombres3(filtro, valor);
        } catch (SQLException ex) {
            Logger.getLogger(ReservasGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

         try {
            for (Object[] reserva : reservas) {
            modelo.addRow(reserva);
        } 
        }catch(Exception e){
            
        }

        // Asignar el modelo a la tabla
        jTable1.setModel(modelo);

    }
 



private void modificarReservaEnBD(int id, String cliente, String hotel, String habitacion, String inicio, String fin, String estado) throws ParseException {
    try {
        // Convertir las fechas de String a java.sql.Date
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaInicioUtil = formatoFecha.parse(inicio);
        java.util.Date fechaFinUtil = formatoFecha.parse(fin);

        java.sql.Date fechaInicio = new java.sql.Date(fechaInicioUtil.getTime());
        java.sql.Date fechaFin = new java.sql.Date(fechaFinUtil.getTime());

        // Crear un objeto Reserva con los datos proporcionados
        Reserva reserva = new Reserva();
        reserva.setIdReserva(id);
        reserva.setIdCliente(Integer.parseInt(cliente));
        reserva.setIdHabitacion(Integer.parseInt(habitacion)); // Convertir ID de habitación a entero
        reserva.setEstadoReserva(estado);
        reserva.setFechaInicio(fechaInicio); // java.sql.Date
        reserva.setFechaFin(fechaFin);       // java.sql.Date

        // Llamar al método actualizarReserva del DAO
        reservaDAO.actualizarReserva(reserva);

        JOptionPane.showMessageDialog(this, "Reserva actualizada con éxito.");
         // Actualizar la tabla después de modificar
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar la reserva: " + e.getMessage());
        e.printStackTrace();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El ID de la habitación debe ser un número válido.");
    }
}
    
 private void eliminarReserva() {
    int filaSeleccionada = Integer.parseInt(JOptionPane.showInputDialog(null,"ID DE la Reserva"));
    if (filaSeleccionada >= 0) {
       
        
            try {
                // Llamar al método en ReservaDAO para actualizar el estado
                reservaDAO.actualizarEstadoReserva(filaSeleccionada, "cancelada");
                // Mostrar un mensaje de éxito
                JOptionPane.showMessageDialog(this, "La reserva ha sido cancelada exitosamente.");
                MostraTabla();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cancelar la reserva: " + ex.getMessage());
            }
        
   } else {
        JOptionPane.showMessageDialog(this, "Escriba un numero 1 mayor.");
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}