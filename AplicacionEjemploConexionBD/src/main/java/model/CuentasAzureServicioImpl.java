package model;

import bd.ConexionOracle;
import bd.ConexionSqlServer;
import estructural.CuentasAzure;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CuentasAzureServicioImpl extends UnicastRemoteObject implements CuentasAzureServicio {

    CuentasAzureServicioOracle servicioOracle = new CuentasAzureServicioOracle();
    CuentasAzureServicioSqlServer servicioSqlServer = new  CuentasAzureServicioSqlServer();

    public CuentasAzureServicioImpl() throws RemoteException {
    }

    @Override
    public boolean insertar(CuentasAzure cuentasAzure) throws RemoteException {
        try{
            CompletableFuture cf = new CompletableFuture();
                cf.runAsync(()
                        -> {
                    System.out.println("tiempo 1 insertar oracle: " + LocalDateTime.now());
                    servicioOracle.insertar(cuentasAzure);
                }).exceptionally(fn -> {System.out.println("Error: "+fn);return null;});
                cf.runAsync(()
                        -> {
                    System.out.println("tiempo 2 insertar sqlserver: " + LocalDateTime.now());
                    servicioSqlServer.insertar(cuentasAzure);
                }).exceptionally(fn -> {System.out.println("Error: "+fn);return null;});
                return true;
        }catch(Exception e){
            System.out.println("Error: "+e);
            return false;
        }  
    }

    @Override
    public void actualizar(CuentasAzure cuentasAzure, Integer id) throws RemoteException {
        CompletableFuture cf = new CompletableFuture();
        cf.runAsync(() -> {System.out.println("tiempo 1 actualizar oracle: " + LocalDateTime.now());servicioOracle.actualizar(cuentasAzure, id);})
                .exceptionally(fn ->{System.out.println("Error: "+fn);return null;});
        cf.runAsync(() ->{System.out.println("tiempo 2 actualizar sqlserver: " + LocalDateTime.now());servicioSqlServer.actualizar(cuentasAzure, id);})
                .exceptionally(fn ->{System.err.println("Error: "+fn);return null;});
    }

    @Override
    public void eliminar(Integer id) throws RemoteException {
        CompletableFuture cf = new CompletableFuture();
        cf.runAsync(() -> {System.out.println("tiempo 1 eliminar oracle: "+LocalDateTime.now());
        servicioOracle.eliminar(id);});
        cf.runAsync(() -> {System.out.println("tiempo 2 eliminar sqlserver: " +LocalDateTime.now());
        servicioSqlServer.eliminar(id);});
    }

    @Override
    public CuentasAzure buscar(Integer id) throws RemoteException {
        CompletableFuture<CuentasAzure> cuentaFuturo01 = CompletableFuture.supplyAsync(() ->servicioOracle.buscar(id));
        CompletableFuture<CuentasAzure> cuentaFuturo02 = CompletableFuture.supplyAsync(() ->servicioSqlServer.buscar(id));
        CompletableFuture<Object> cuentaEnTusManos = CompletableFuture.anyOf(cuentaFuturo01,cuentaFuturo02);
        try{
            return (CuentasAzure) cuentaEnTusManos.get();
        }catch(Exception e){
            System.out.println("Error: "+e);
            return null;
        }
    }

    @Override
    public List<CuentasAzure> listar() throws RemoteException {
        CompletableFuture<List<CuentasAzure>> cuentasFuture01 = CompletableFuture.supplyAsync(() -> servicioOracle.listar());
        CompletableFuture<List<CuentasAzure>> cuentasFuture02 = CompletableFuture.supplyAsync(() -> servicioSqlServer.listar());
        CompletableFuture<Object> cuentasEnTusManos = CompletableFuture.anyOf(cuentasFuture01,cuentasFuture02);       
        try{
            return (List<CuentasAzure>) cuentasEnTusManos.get();
        }catch(Exception e){
            System.out.println("Error: " +e);
            return null;           
        }
    }

    @Override
    public HashMap<String, Integer> listarPaises() throws RemoteException {
        CompletableFuture<HashMap<String, Integer>> paisesFuture01 = CompletableFuture.supplyAsync(() -> servicioOracle.listarPaises());
        CompletableFuture<HashMap<String, Integer>> paisesFuture02 = CompletableFuture.supplyAsync(() -> servicioSqlServer.listarPaises());
        CompletableFuture<Object> paisesEnTusManos = CompletableFuture.anyOf(paisesFuture01,paisesFuture02);       
        try{
            return (HashMap<String, Integer>) paisesEnTusManos.get();
        }catch(Exception e){
            System.out.println("Error: " +e);
            return null;           
        }
    }

    
    
    
}
