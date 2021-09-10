/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import estructural.CuentasAzure;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author Lauritas
 */
public interface CuentasAzureServicio extends Remote {
    boolean insertar(CuentasAzure cuentasAzure) throws RemoteException;
    void actualizar(CuentasAzure cuentasAzure, Integer id) throws RemoteException;
    void eliminar(Integer id) throws RemoteException;
    CuentasAzure buscar(Integer id) throws RemoteException;
    List<CuentasAzure> listar() throws RemoteException;
    HashMap<String, Integer> listarPaises() throws RemoteException;
}
