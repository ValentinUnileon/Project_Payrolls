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
import controlador.Categorias;
import controlador.Empresas;
import controlador.Nomina;
import controlador.Trabajador;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.*;
import util.HibernateUtil;

/**
 *
 * @author david
 */
public class DBManager {
    
    private List<Trabajador> trabajadoresCorrectos;
    private List<Nomina> nominasCorrectas;
    private Map<String, String> categoriaSalarioBase;
    private Map<String, String> categoriaComplementos;
    private SessionFactory sessionFactory;
    private Session session;
    
    private List<Categorias> listaCategorias;
    private List<Empresas> listaEmpresas;
    private List<Trabajador> listaTrabajadores;
    private List<Nomina> listaNominas;

    
    public DBManager(List<Trabajador> trabajadoresCorrectos, Map<String, String> categoriaSalarioBase, Map<String, String> categoriaComplementos, List<Nomina> nominasCorrectas){
        this.trabajadoresCorrectos=trabajadoresCorrectos;
        this.categoriaSalarioBase=categoriaSalarioBase;
        this.categoriaComplementos=categoriaComplementos;
        this.sessionFactory = HibernateUtil.getSessionFactory();
        this.nominasCorrectas = nominasCorrectas;
    }
    
    public void actualizarBaseDatos(){
        introducirEmpresas();
        introducirCategorias();
        introducirTrabajadores();
        introducirNominas();
        
        System.out.println("EJECUCION TERMINADA :)");
    }
    
    private void introducirEmpresas(){
        session = sessionFactory.openSession();
        session.beginTransaction();
        
        String consulta = "select e from Empresas e";
        Query query = session.createQuery(consulta);
        listaEmpresas = query.list();        

        session.getTransaction().commit();
        session.close();
        
        for(int i = 0; i<trabajadoresCorrectos.size(); i++){
            
            if(empresaExiste(trabajadoresCorrectos.get(i).getEmpresa().getCif())==false){ 
                
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(trabajadoresCorrectos.get(i).getEmpresa());
                session.getTransaction().commit();
                listaEmpresas.add(trabajadoresCorrectos.get(i).getEmpresa());
                session.close();
            } else {
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.update(trabajadoresCorrectos.get(i).getEmpresa());
                session.getTransaction().commit();
                session.close();
            }
        }
        
    }
    
    private boolean empresaExiste(String cif){
        for(int i=0; i<listaEmpresas.size(); i++){
           
            if(listaEmpresas.get(i).getCif().equals(cif)){
                return true;
            }
        }
        return false;
    }
    
    private void introducirCategorias(){
        
        
        
        session = sessionFactory.openSession();
        session.beginTransaction();
        String consulta = "select c from Categorias c";
        Query query = session.createQuery(consulta);
        listaCategorias = query.list();
        session.getTransaction().commit();
        session.close();

        for(Map.Entry<String, String> entry : categoriaSalarioBase.entrySet()){
            
            String nombreCategoria = entry.getKey();
            
            if(categoriaExiste(nombreCategoria)==false){
                
                session = sessionFactory.openSession();
                session.beginTransaction();
                
                
                
                Categorias categoria = new Categorias(1, nombreCategoria, Double.parseDouble(categoriaSalarioBase.get(nombreCategoria)), Double.parseDouble(categoriaComplementos.get(nombreCategoria)));
                categoria = asignarIdCategoria(categoria);
                session.save(categoria);
                session.getTransaction().commit();
                
                
                listaCategorias.add(categoria);
                session.close();
            } else {
            
                session = sessionFactory.openSession();
                session.beginTransaction();
                
                
                int idNuevaCategoria = obtenerIdCategoriaPorNombre(nombreCategoria);
                Categorias categoria = new Categorias(idNuevaCategoria, nombreCategoria, Double.parseDouble(categoriaSalarioBase.get(nombreCategoria)), Double.parseDouble(categoriaComplementos.get(nombreCategoria)));
                categoria = asignarIdCategoria(categoria);
                session.update(categoria);
                session.getTransaction().commit();               
                
                
                listaCategorias.add(categoria);
                session.close();
            }
        }        
        
    }
    
    public int obtenerIdCategoriaPorNombre(String nombre) {
    
        int categoriaEncontrada = 0;
        for (int i=0; i<listaCategorias.size(); i++) {
            if (listaCategorias.get(i).getNombreCategoria().equals(nombre)) {
            
                categoriaEncontrada = listaCategorias.get(i).getIdCategoria();
            }
        }
        
        return categoriaEncontrada;
    }
    
    public Categorias asignarIdCategoria(Categorias categoria) {
        boolean categoriaExistente = false;
        int nuevoIdCategoria = 1;

        for (int i = 0; i < listaCategorias.size(); i++) {
            Categorias categoriaAux = listaCategorias.get(i);
            if (categoriaAux.getNombreCategoria().equals(categoria.getNombreCategoria())) {
                categoriaExistente = true;
                categoria.setIdCategoria(categoriaAux.getIdCategoria());
                break;
            }
        }

        if (categoriaExistente) {
            
            // Si la categoria ya existe, se le ha asignado antes al objeto de tipo categoria el id ya existente
            
            
        } else {
                    // Si la categoria no existe, se busca el máximo idCategoria y se incrementa en 1

        for (int i = 0; i < listaCategorias.size(); i++) {
                Categorias categoriaAux = listaCategorias.get(i);
                int idCategoria = categoriaAux.getIdCategoria();
                if (idCategoria >= nuevoIdCategoria) {
                    nuevoIdCategoria = idCategoria + 1;
                    
                }
        }
        
        categoria.setIdCategoria(nuevoIdCategoria);        
        }

        return categoria;
    }
    
    private boolean categoriaExiste(String nombreCategoria){
            for(int i=0; i<listaCategorias.size(); i++){
                if(listaCategorias.get(i).getNombreCategoria().equals(nombreCategoria))
                    return true;
            }
            return false;
        }
    
    
    private void introducirTrabajadores(){
        settearIdsTrabajadores();
        session = sessionFactory.openSession();
        session.beginTransaction();
        String consulta = "select t from Trabajador t";
        Query query = session.createQuery(consulta);
        listaTrabajadores = query.list();
        session.getTransaction().commit();
        session.close();
        
        for(Trabajador trabajador: trabajadoresCorrectos){
            if(trabajadorExiste(trabajador)==false){
                
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(trabajador);
                session.getTransaction().commit();
                
                listaTrabajadores.add(trabajador);
                session.close();
            } else {
            
                session = sessionFactory.openSession();
                session.beginTransaction();
                
                
                int nuevoId = getIdTrabajadorExistente(trabajador);
                trabajador.setIdTrabajador(nuevoId);
                session.update(trabajador);
                session.getTransaction().commit();
                session.close();
                
                session = sessionFactory.openSession();
                session.beginTransaction();
                consulta = "select t from Trabajador t";
                query = session.createQuery(consulta);
                listaTrabajadores = query.list();
                session.getTransaction().commit();
                session.close();
            }
        }
    }
    
    public int getIdTrabajadorExistente(Trabajador trabajador) {
        
        int idTrabajadorExistente =0;
        
        for(int i=0; i<listaTrabajadores.size(); i++){
            if(listaTrabajadores.get(i).getNombre().equals(trabajador.getNombre()) && listaTrabajadores.get(i).getNifnie().equals(trabajador.getNifnie()) && listaTrabajadores.get(i).getFechaAlta().equals(trabajador.getFechaAlta())){
                idTrabajadorExistente = listaTrabajadores.get(i).getIdTrabajador();
            }
         }
        
        return idTrabajadorExistente;
    }
    
    public void settearIdsTrabajadores() {
    
        for (int i=0; i<trabajadoresCorrectos.size(); i++) {
            
                    
            for (int j=0; j<listaEmpresas.size(); j++){
            
                if (trabajadoresCorrectos.get(i).getEmpresa().getCif().equals(listaEmpresas.get(j).getCif())) {
                    
                    trabajadoresCorrectos.get(i).setIdEmpresa(listaEmpresas.get(j).getIdEmpresa());
                }
            }
            
            for (int k=0; k<listaCategorias.size(); k++){
            
                if (trabajadoresCorrectos.get(i).getCategoria().getNombreCategoria().equals(listaCategorias.get(k).getNombreCategoria())) {
                    
                    trabajadoresCorrectos.get(i).setIdCategoria(listaCategorias.get(k).getIdCategoria());
                }
            }
            
        }
        
        
    }
    
    private boolean trabajadorExiste(Trabajador trabajador){
         for(int i=0; i<listaTrabajadores.size(); i++){
            if(listaTrabajadores.get(i).getNombre().equals(trabajador.getNombre()) && listaTrabajadores.get(i).getNifnie().equals(trabajador.getNifnie()) && listaTrabajadores.get(i).getFechaAlta().equals(trabajador.getFechaAlta())){
                return true;
            }
         }
         return false;
    }
    
    private void introducirNominas(){
        
        //Guardamos las nominas de la DB en una lista
        session = sessionFactory.openSession();
        session.beginTransaction();
        String consulta = "select n from Nomina n";
        Query query = session.createQuery(consulta);
        listaNominas = query.list();
        session.getTransaction().commit();
        session.close();
        
        for(Nomina nomina: nominasCorrectas){
            if(existeNomina(nomina)==false){
                //Inserta trabajador y nomina normal
                
                session = sessionFactory.openSession();
                session.beginTransaction();
                
                nomina = asignarIdNomina(nomina);
                session.save(nomina);
                session.getTransaction().commit();
                
                //Añade el trabajador insertada a la lista
                listaNominas.add(nomina);
                session.close();
            } else {
            
                session = sessionFactory.openSession();
                session.beginTransaction();
                
                nomina.setIdNomina(getIdNominaExistente(nomina));
                session.update(nomina);
                session.getTransaction().commit();
                
                //Añade el trabajador insertada a la lista
                session.close();
                
                
                session = sessionFactory.openSession();
                session.beginTransaction();
                consulta = "select n from Nomina n";
                query = session.createQuery(consulta);
                listaNominas = query.list();
                session.getTransaction().commit();
                session.close();
            }
        }
    }
    
    public int getIdNominaExistente(Nomina nomina) {
        
        int idNominaExistente =0;
        
        for(int i=0; i<listaNominas.size(); i++){
            if(listaNominas.get(i).getMes()==nomina.getMes() && listaNominas.get(i).getAnio()==nomina.getAnio() && 
                    listaNominas.get(i).getIdTrabajador() == nomina.getIdTrabajador() && 
                    listaNominas.get(i).getLiquidoNomina()==nomina.getLiquidoNomina() && listaNominas.get(i).getBrutoNomina()==nomina.getBrutoNomina()){
                idNominaExistente = listaNominas.get(i).getIdNomina();
            }
         }
        
        return idNominaExistente;
    }
    
    public Nomina asignarIdNomina(Nomina nomina) {
        boolean nominaExistente = false;
        int nuevoIdNomina = 1;

        for (int i = 0; i < listaNominas.size(); i++) {
            Nomina nominaAux = listaNominas.get(i);
            if (nominaAux.getMes() == nomina.getMes() && nominaAux.getAnio() == nomina.getAnio() &&
                nominaAux.getIdTrabajador() == nomina.getIdTrabajador() && 
                nominaAux.getLiquidoNomina() == nomina.getLiquidoNomina() && nominaAux.getBrutoNomina() == nomina.getBrutoNomina()) {
                nominaExistente = true;
                nomina.setIdNomina(nominaAux.getIdNomina());
                break;
            }
        }

        if (nominaExistente) {
            
            // Si la nomina ya existe, se le ha asignado antes el id que ya existe
            
        } else {
                    // Si la nomina no existe, se busca el máximo idNomina y se incrementa en 1

        for (int i = 0; i < listaNominas.size(); i++) {
                Nomina nominaAux = listaNominas.get(i);
                int idNomina = nominaAux.getIdNomina();
                if (idNomina >= nuevoIdNomina) {
                    nuevoIdNomina = idNomina + 1;
                    
                }
        }
        
        nomina.setIdNomina(nuevoIdNomina);        
        }

        return nomina;
    }
    
    private boolean existeNomina(Nomina nominaNueva){
        
        for(int i=0; i<listaNominas.size(); i++){
            if(listaNominas.get(i).getMes()==nominaNueva.getMes() && listaNominas.get(i).getAnio()==nominaNueva.getAnio() && 
                    listaNominas.get(i).getIdTrabajador() == nominaNueva.getIdTrabajador() && 
                    listaNominas.get(i).getLiquidoNomina()==nominaNueva.getLiquidoNomina() && listaNominas.get(i).getBrutoNomina()==nominaNueva.getBrutoNomina()){
                return true;
            }
        }
        return false;
    }
}
