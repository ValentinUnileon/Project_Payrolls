/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_nominassi;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import static com.itextpdf.kernel.pdf.PdfName.Border;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.*;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import controlador.Empresas;
import controlador.Nomina;
import controlador.Trabajador;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.*;
import util.HibernateUtil;

/**
 *
 * @author david
 */
public class DBManager {
    
    private ArrayList<Trabajador> trabajadoresCorrectos;
    private HashMap<String, Double> categoriaSalarioBase;
    private HashMap<String, Double> categoriaComplementos;
    private SessionFactory sessionFactory;
    private Session session;
    
    public DBManager(ArrayList<Trabajador> trabajadoresCorrectos, HashMap<String, Double> categoriaSalarioBase, HashMap<String, Double> categoriaComplementos){
        this.trabajadoresCorrectos=trabajadoresCorrectos;
        this.categoriaSalarioBase=categoriaSalarioBase;
        this.categoriaComplementos=categoriaComplementos;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    public void actualizarBaseDatos(){
        almacenarEmpresas();
        //almacenarCategorias();
        //almacenarTrabajadorYNominas();
    }
    
    private void almacenarEmpresas(){
        //Guardamos las empresas de la DB en una lista
        session = sessionFactory.openSession();
        session.beginTransaction();
        String consulta = "select e from Empresas e";
        Query query = session.createQuery(consulta);
        List<Empresas> empresasDB = query.list();
        session.getTransaction().commit();
        session.close();
        
        //Recorre trabajadores e inserta en DB las empresas que no esten
        for(Trabajador trabajador: trabajadoresCorrectos){
            if(EmpresaExiste(trabajador.getEmpresa().getCif(), empresasDB)==false){
                 //Insertar
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(trabajador.getEmpresa());
                session.getTransaction().commit();
                //Añade la empresa insertada a la lista
                empresasDB.add(trabajador.getEmpresa());
                session.close();
            }
        } 
    }
    
    private boolean EmpresaExiste(String elem, List<Empresas> list){
        for(int i=0; i<list.size(); i++){
           
            if((list.get(i).getCif()).equals(elem))
                return true;
        }
        return false;
    }
    
}



