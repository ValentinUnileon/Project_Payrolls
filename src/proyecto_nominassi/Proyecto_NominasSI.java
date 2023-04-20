/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_nominassi;

import java.sql.Connection;
import java.util.Scanner;

import DAO.TrabajadorDAO;
import util.HibernateUtil;

import configuracion.Trabajador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author valen
 */
public class Proyecto_NominasSI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        
        // TODO code application logic here
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
        
        System.out.println("Introducir el CIF del trabajador:");
        Scanner scan = new Scanner(System.in);
        String cif = scan.nextLine();
        TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
        trabajadorDAO.setConector(session);
        Trabajador trabajador = trabajadorDAO.encontrarPorCif(cif);
        if (trabajador == null) {
            System.out.println("No hemos encontrado al trabajador en nuestro sistema");
        } else {
            //datos Trabajador
            System.out.println("Nombre trabajador: " + trabajador.getNombre());
            System.out.println("Apellidos trabajador: " + trabajador.getApellido1() + " " + trabajador.getApellido2());
            System.out.println("NIF trabajador: " + trabajador.getNifnie());

            
            //
            HibernateUtil.shutdown();
        }
             
        
 
    }
    
}
