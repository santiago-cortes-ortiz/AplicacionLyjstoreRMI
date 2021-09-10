/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionljstorermicliente;

import java.rmi.Naming;
import model.CuentasAzureServicio;
import vistas.GUIPrincipal;

/**
 *
 * @author Lauritas
 */
public class AplicacionLJStoreRMICliente {
    
          public static void main(String[] args) {
        String rmiRegistryHost = "127.0.0.1";
     	try {
            if (args.length > 0) {
                rmiRegistryHost = args[0];
            }
            
            CuentasAzureServicio model = (CuentasAzureServicio)Naming.lookup("//" + rmiRegistryHost +"/AplicacionCuentasAzure");
            if(model == null){
                System.out.println("Error... Cliente ");
      		return;	
            }
            GUIPrincipal gui = new GUIPrincipal(model);
            gui.setVisible(true);
    	} catch (Exception e) {
            System.out.println("Error...  " + e);
    	}

    }

    
}
