/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lauritas
 */
public interface IActualizableCuentaAzure extends Remote{
    
    public void cambio()throws RemoteException;
}
