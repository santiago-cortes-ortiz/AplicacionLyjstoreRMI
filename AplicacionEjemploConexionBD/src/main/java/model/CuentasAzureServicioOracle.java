/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bd.ConexionOracle;
import estructural.CuentasAzure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeiss
 */
public class CuentasAzureServicioOracle {
    
    ResultSet resultSet;
    ConexionOracle bd = new ConexionOracle();

    public CuentasAzureServicioOracle() {
    }
    
    public boolean insertar(CuentasAzure cuentasAzure){
        String query = "INSERT INTO cuentas_azure VALUES("+ cuentasAzure.getIdCuenta() +",'"+ cuentasAzure.getCorreo()+ "','"+cuentasAzure.getContrasenia() + "','"+ cuentasAzure.getPaisCreacion()+ "')";
        try {
            bd.executeQueryStatement(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CuentasAzureServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void actualizar(CuentasAzure cuentasAzure, Integer id){
        String query = "UPDATE cuentas_azure SET contrasenia = '"+cuentasAzure.getContrasenia()+"' WHERE id_cuenta = " + id;
        bd.executeUpdateStatement(query);
    }
    
    public void eliminar(Integer id){
        CuentasAzure cuentasAzure = buscar(id);
        String query = "DELETE FROM cuentas_azure WHERE id_cuenta = "+cuentasAzure.getIdCuenta();
        try {
            bd.executeQueryStatement(query);
        } catch (SQLException ex) {
            Logger.getLogger(CuentasAzureServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    
    public CuentasAzure buscar(Integer id){
    String query ="SELECT * FROM cuentas_azure WHERE id_cuenta = " + id;
        Number idCuenta = null;
        String correo = null;
        String contrasenia = null;
        String paisCreacion = null;
        try {
            resultSet = bd.executeQueryStatement(query);
            while(resultSet.next()){
                idCuenta = Integer.parseInt(resultSet.getString(1));
                correo = resultSet.getString(2);
                contrasenia = resultSet.getString(3);
                paisCreacion = resultSet.getString(4);
            }
        } catch (SQLException ex) {

        }
        
        System.out.println("Tiempo 1 buscar oracle: "+LocalDateTime.now());

        return new CuentasAzure(idCuenta,correo,contrasenia,paisCreacion);
    }
    
    public List<CuentasAzure> listar(){
    String query ="SELECT * FROM cuentas_azure";
        List<CuentasAzure> nuevaListar = new ArrayList<>();
        try{
            resultSet = bd.executeQueryStatement(query);
            while (resultSet.next()){
                nuevaListar.add(new CuentasAzure(Integer.parseInt(resultSet.getString(1)),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
            }
        }catch(SQLException ex){

        }
        return nuevaListar;
    
    }
    
    public HashMap<String, Integer> listarPaises(){
    
        String query ="Select PAIS_CREACION, COUNT(*) AS contador from cuentas_azure group by pais_creacion";
   
        HashMap<String, Integer> nuevaListar = new HashMap<String, Integer>();
        try{
            resultSet = bd.executeQueryStatement(query);
            while (resultSet.next()){
                nuevaListar.put(resultSet.getString("PAIS_CREACION"), resultSet.getInt("contador"));
                
            }
        }catch(SQLException ex){
            System.out.println("error");
        }
        return nuevaListar;
    }
    
}
