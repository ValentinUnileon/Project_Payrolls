/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_nominassi;

import controlador.Categorias;
import controlador.Empresas;
import controlador.Nomina;

import java.sql.Connection;
import java.util.Scanner;

import util.HibernateUtil;

import controlador.Trabajador;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;

/**
 *
 * @author valen
 */
public class Proyecto_NominasSI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
       

        
        
        
         //Ejercicio 2 y 3

        ExcelManager resolverEjercicio = new ExcelManager();


         try {
 
             resolverEjercicio.mapearHoja1(); 
            
             resolverEjercicio.mapearHoja2(); 
             resolverEjercicio.mapearHoja3(); 
             resolverEjercicio.mapearHoja4(); 
             resolverEjercicio.procesarDNI();
             resolverEjercicio.generarGmailTrabajadores();         
             resolverEjercicio.generarIBANTrabajadores();


            
             
    
        } catch (Exception ex) {
           ex.printStackTrace();
        
        }
         
        // Ejercicio 4
        
        System.out.println("Introduce mes y año de la nomina (MM/AAAA): ");
        Scanner scan2 = new Scanner(System.in);
        String fecha = scan2.nextLine();
         
        try {
            resolverEjercicio.generarNominasTrabajadores(fecha);

        } catch (IOException ex) {
            Logger.getLogger(Proyecto_NominasSI.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        // Ejercicio 5
        
        DBManager ej5 = new DBManager(resolverEjercicio.obtenerTrabajadoresCorrectos(), resolverEjercicio.getCategoriaSalarioBase(), resolverEjercicio.getCategoriaComplementos(), resolverEjercicio.getNominasTrabajadores());
        ej5.actualizarBaseDatos();
                       
    }
    
}
