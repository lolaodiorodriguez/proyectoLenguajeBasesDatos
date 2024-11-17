
package com.mycompany.proyectolenguajes.igu;

import Clases.principales.Reserva;
import ConexionSQLDB.DataBaseConnection;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Reservas extends javax.swing.JFrame {
    
    public static void main(String[] args) {
    
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Reservas().setVisible(true);  
        }
    });
}
    
    public Reservas() {
        initComponents();
        loadReservas();  
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cancelarReserva = new javax.swing.JButton();
        modificarReserva = new javax.swing.JButton();
        agregarReserva = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Total de reservas");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cliente", "Hotel", "Fecha inicio", "Fecha fin", "Habitacion", "Estado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        cancelarReserva.setText("Cancelar Reserva");
        cancelarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarReservaActionPerformed(evt);
            }
        });

        modificarReserva.setText("Modificar Reserva");
        modificarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarReservaActionPerformed(evt);
            }
        });

        agregarReserva.setText("Agregar Reserva");
        agregarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarReservaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cancelarReserva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modificarReserva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregarReserva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(agregarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(modificarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarReservaActionPerformed
        int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona una reserva para cancelar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
     int idReserva = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
    // Confirmar la cancelación
    int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cancelar esta reserva?", "Confirmar", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        // Llamar al método para cancelar la reserva
        boolean success = DataBaseConnection.cancelarReserva(idReserva);
        if (success) {
            JOptionPane.showMessageDialog(this, "Reserva cancelada exitosamente.");
            loadReservas(); // Recargar la tabla para reflejar los cambios
        } else {
            JOptionPane.showMessageDialog(this, "Error al cancelar la reserva. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cancelarReservaActionPerformed
    }
    private void modificarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarReservaActionPerformed

    private void agregarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarReservaActionPerformed
       
       agregarReserva frameAgregar = new agregarReserva();
       frameAgregar.setVisible(true);
       frameAgregar.setLocationRelativeTo(null);
    
    }//GEN-LAST:event_agregarReservaActionPerformed
    
    
    private void loadReservas() {
    List<Reserva> reservas = DataBaseConnection.getReservas();  
    String[] columnas = {"ID", "Cliente", "Hotel", "Fecha inicio", "Fecha fin", "Habitacion", "Estado"};
    DefaultTableModel model = new DefaultTableModel(columnas, 0);  
    
    for (Reserva reserva : reservas) {
        
        Object[] row = {
            reserva.getIdReserva(),
            reserva.getCliente().getNombre(), 
            reserva.getHotel().getNombre(),
            reserva.getFechaInicio(), 
            reserva.getFechaFin(),
            reserva.getHabitacion().getTipoHabitacion(),
            reserva.getEstadoReserva()
        };
        model.addRow(row);  
    }
    
    jTable1.setModel(model);  
    jTable1.getColumnModel().getColumn(0).setMinWidth(0);
jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
jTable1.getColumnModel().getColumn(0).setWidth(0);
    

}

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarReserva;
    private javax.swing.JButton cancelarReserva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton modificarReserva;
    // End of variables declaration//GEN-END:variables
}
