/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import estructural.CuentasAzure;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.CuentasAzureServicio;

/**
 *
 * @author Lauritas
 */
public class GUIActualizarCuentaAzure extends javax.swing.JFrame {

    private CuentasAzureServicio servicioCuenta;
    /**
     * Creates new form GUIActualizarCuentaAzure
     */
    public GUIActualizarCuentaAzure(CuentasAzureServicio ser) {
        initComponents();
        servicioCuenta = ser;
        setLocationRelativeTo(null);
        this.setTitle("Actualizar Cuenta Azure");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID:");

        jLabel2.setText("Contrasena:");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    Integer idCuenta;
    String correo;
    String contrasenia;
    String paisCreacion;
    CuentasAzure cuenta= null;
    
    
    idCuenta = Integer.parseInt(jTextField1.getText());
    
    try {
            cuenta = servicioCuenta.buscar(idCuenta);
            
            
       
        } catch (RemoteException ex) {
            
            
            Logger.getLogger(GUIEliminarCuentaAzure.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
        if(cuenta != null){
            jTextField1.setText(String.valueOf(cuenta.getIdCuenta()));
            jTextField2.setText(cuenta.getContrasenia());

        }
     
        if(cuenta.getIdCuenta() == null) {
            JOptionPane.showMessageDialog(this, "La Cuenta Azure: " + idCuenta +" NO existe!", "Aviso", JOptionPane.ERROR_MESSAGE);
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField1.grabFocus();
        }
              
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
    Integer idCuenta;
    String contrasenia;
    CuentasAzure cuenta= null;
  

    idCuenta = Integer.parseInt(jTextField1.getText());
    
        try {
            cuenta = servicioCuenta.buscar(idCuenta);
            
            if(cuenta.getIdCuenta()!= null){
               
                cuenta.setContrasenia(jTextField2.getText());
               servicioCuenta.actualizar(cuenta, idCuenta);
              JOptionPane.showMessageDialog(this, "La Cuenta Azure: " + idCuenta +" se ha actualizado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
            JOptionPane.showMessageDialog(this, "La Cuenta Azure: " + idCuenta +" NO ha sido actualizada!", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
          
            
            
        } catch (RemoteException ex) {
            Logger.getLogger(GUIActualizarCuentaAzure.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
