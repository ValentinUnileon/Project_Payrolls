/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_nominassi;

import controlador.Categorias;
import DAO.CategoriasDAO;

import controlador.Empresas;
import DAO.EmpresaDAO;

import controlador.Nomina;
import DAO.NominasDAO;

import java.sql.Connection;
import java.util.Scanner;

import DAO.TrabajadorDAO;
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
       

        
        // TODO code application logic here
        
        /*
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
        }
            /*
            //Datos Empresa
            Empresas empresa = trabajador.getEmpresas();
            System.out.println("Nombre Empresa: " + empresa.getNombre());
            System.out.println("Nombre Empresa: " + empresa.getCif());

            //Numero de trabajadores
            int numTrabajadores = empresa.getTrabajadors().size();
            System.out.println("Numero de trabajadores en " + empresa.getNombre() + ": " + numTrabajadores);

            //Actualizar salario categorias
            CategoriasDAO categoriaDAO = new CategoriasDAO();
            categoriaDAO.setConector(session);
            categoriaDAO.actualizarSalario(trabajador.getCategorias());
            
            //Cambiar nombre
            EmpresaDAO empresaDAO = new EmpresaDAO();
            empresaDAO.setConector(session);
            empresaDAO.actualizarNombreEmpresa(trabajador.getEmpresas());
            
            //Eliminar nominas en base al IRPF maximo
            NominasDAO nominaDAO = new NominasDAO();
            nominaDAO.setConector(session);
            nominaDAO.eliminarNominas_IRPF_MAX();
            //
            //
            HibernateUtil.shutdown();
        }
             */
        
            
        //////////////////////////////////////////////////////////////////////////////////////////
        
         //Ejercicio 3

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
            resolverEjercicio.generarNominasTrabajadores("04/2017");
            // PDFManager lol = new PDFManager();
            // lol.crear();

        } catch (IOException ex) {
            Logger.getLogger(Proyecto_NominasSI.class.getName()).log(Level.SEVERE, null, ex);
        //} catch (ParseException ex) {
        //    Logger.getLogger(Proyecto_NominasSI.class.getName()).log(Level.SEVERE, null, ex);
        //}

        }
        
        // Ejercicio 5
        
        /*
        for (int i=0; i<resolverEjercicio.getTrabajadoresCorrectos().size(); i++) {
            
            System.out.println("lista de trabajadores: "+ resolverEjercicio.getTrabajadoresCorrectos().get(i).getIdTrabajador());
        }
        */
        
        DBManager ej5 = new DBManager(resolverEjercicio.obtenerTrabajadoresCorrectos(), resolverEjercicio.getCategoriaSalarioBase(), resolverEjercicio.getCategoriaComplementos(), resolverEjercicio.getNominasTrabajadores());
        ej5.actualizarBaseDatos();
        
        System.out.println("LLEGA AL FINAL DEL MAIN");
    }
    
}
