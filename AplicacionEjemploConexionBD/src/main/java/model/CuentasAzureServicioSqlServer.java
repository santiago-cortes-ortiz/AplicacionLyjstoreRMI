/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bd.ConexionSqlServer;
import estructural.CuentasAzure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class CuentasAzureServicioSqlServer {
    
    ResultSet resultSet;
    ConexionSqlServer bdS = new ConexionSqlServer();

    public CuentasAzureServicioSqlServer() {
    
    }
    public boolean insertar(CuentasAzure cuentasAzure){
        String query = "INSERT INTO cuentas_azure VALUES("+ cuentasAzure.getIdCuenta() +",'"+ cuentasAzure.getCorreo()+ "','"+cuentasAzure.getContrasenia() + "','"+ cuentasAzure.getPaisCreacion()+ "')";
        if (bdS.executeUpdateStatement(query)) {
            return true;
        }
        else{
            return false;
        }
    }
    
    public void actualizar(CuentasAzure cuentasAzure, Integer id){
        String query = "UPDATE cuentas_azure SET contrasenia = '"+cuentasAzure.getContrasenia()+"' WHERE id_cuenta = " + id;
        bdS.executeUpdateStatement(query);
    }
    
    public void eliminar(Integer id){
        CuentasAzure cuentasAzure = buscar(id);
        String query = "DELETE FROM cuentas_azure WHERE id_cuenta = "+cuentasAzure.getIdCuenta();
        bdS.executeQueryStatement(query);
             
    }
    
    public CuentasAzure buscar(Integer id){
    String query ="SELECT * FROM cuentas_azure WHERE id_cuenta = " + id;
        Number idCuenta = null;
        String correo = null;
        String contrasenia = null;
        String paisCreacion = null;
        try {
            resultSet = bdS.executeQueryStatement(query);
            
            while(resultSet.next()){
                idCuenta = Integer.parseInt(resultSet.getString(1));
                correo = resultSet.getString(2);
                contrasenia = resultSet.getString(3);
                paisCreacion = resultSet.getString(4);
            }
        } catch (SQLException ex) {

        }
        System.out.println("Tiempo 2 buscar sqlserver: "+LocalDateTime.now());
        return new CuentasAzure(idCuenta,correo,contrasenia,paisCreacion);
    }
    
    public List<CuentasAzure> listar(){
    String query ="SELECT * FROM cuentas_azure";
        List<CuentasAzure> nuevaListar = new ArrayList<>();
        try{
            resultSet = bdS.executeQueryStatement(query);
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
            resultSet = bdS.executeQueryStatement(query);
            while (resultSet.next()){
                nuevaListar.put(resultSet.getString("PAIS_CREACION"), resultSet.getInt("contador"));
                
            }
        }catch(SQLException ex){
            System.out.println("error");
        }
        return nuevaListar;
    }
    
        
        
}
