package model;

import estructural.CuentasAzure;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface CuentasAzureServicio extends Remote {
    boolean insertar(CuentasAzure cuentasAzure) throws RemoteException;
    void actualizar(CuentasAzure cuentasAzure, Integer id) throws RemoteException;
    void eliminar(Integer id) throws RemoteException;
    CuentasAzure buscar(Integer id) throws RemoteException;
    List<CuentasAzure> listar() throws RemoteException;
    HashMap<String, Integer> listarPaises() throws RemoteException;
}
