/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibague.ingsistemas.main;

import estructural.CuentasAzure;
import model.CuentasAzureServicio;
import model.CuentasAzureServicioImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author profesor lugo
 */
public class Principal {

    public static void main(String[] args){

        String dbHost = "127.0.0.1";
        try {
            if (args.length > 0) {
                dbHost = args[0];
            }
            //puerto donde recide el servidor (model)
            LocateRegistry.createRegistry(1099);
            CuentasAzureServicio model = new CuentasAzureServicioImpl();
            Naming.rebind("//"+dbHost+"/AplicacionCuentasAzure", model);
            System.out.println("Objeto Model en el servidor...");            
        } catch (Exception e) {
            System.out.println("Eror: " + e.getMessage());
        }

    }
}
