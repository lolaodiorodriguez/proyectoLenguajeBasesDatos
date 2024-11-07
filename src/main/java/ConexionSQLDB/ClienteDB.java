/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionSQLDB;

import Clases.principales.Clientes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ClienteDB {
    
//LISTADO DEL CLIENTE    
    public ArrayList<Clientes> ListClientes() {
        ArrayList<Clientes> cliente = new ArrayList<>();
        try {
            Connection cnx = DataBaseConnection.getConneccion();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT ID_CLIENTE,NOMBRE,APELLIDO1,APELLIDO2,EMAIL,TELEFONO,DIRECCION"
                    + " FROM CLIENTES ORDER BY 2 ");
            while (rs.next()) {
                Clientes cl = new Clientes();
                cl.setId_cliente(rs.getInt("ID_CLIENTE"));
                cl.setNombre(rs.getString("NOMBRE"));
                cl.setApellido1(rs.getString("APELLIDO1"));
                cl.setApellido2(rs.getString("APELLIDO2"));
                cl.setEmail(rs.getString("EMAIL"));
                cl.setTelefono(rs.getString("TELEFONO"));
                cl.setDireccion(rs.getString("DIRECCION"));
                cliente.add(cl);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en listado");
        }
        return cliente;
    }
    
}
