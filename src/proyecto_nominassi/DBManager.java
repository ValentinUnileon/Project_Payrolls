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
    private Map<String, String> categoriaSalarioBase;
    private Map<String, String> categoriaComplementos;
    private SessionFactory sessionFactory;
    private Session session;
    
    private List<Categorias> listaCategorias;
    
    public DBManager(List<Trabajador> trabajadoresCorrectos, Map<String, String> categoriaSalarioBase, Map<String, String> categoriaComplementos){
        this.trabajadoresCorrectos=trabajadoresCorrectos;
        this.categoriaSalarioBase=categoriaSalarioBase;
        this.categoriaComplementos=categoriaComplementos;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    public void actualizarBaseDatos(){
        //almacenarEmpresas();
        almacenarCategorias();
        //almacenarTrabajadorYNominas();
    }
    
    private void almacenarEmpresas(){
        session = sessionFactory.openSession();
        session.beginTransaction();
        
        String consulta = "select e from Empresas e";
        Query query = session.createQuery(consulta);
        List<Empresas> listaEmpresasIntroducidas = query.list();

        session.getTransaction().commit();
        session.close();
        
        for(int i = 0; i<trabajadoresCorrectos.size(); i++){
                    
            if(empresaExiste(trabajadoresCorrectos.get(i).getEmpresa().getIdEmpresa(), listaEmpresasIntroducidas)==false){                 
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(trabajadoresCorrectos.get(i).getEmpresa());
                session.getTransaction().commit();
                listaEmpresasIntroducidas.add(trabajadoresCorrectos.get(i).getEmpresa());
                session.close();
            }
        }
        
    }
    
    private boolean empresaExiste(int id, List<Empresas> list){
        for(int i=0; i<list.size(); i++){
           
            if(list.get(i).getIdEmpresa() == id){
                return true;
            }
        }
        return false;
    }
    
    private void almacenarCategorias(){
        
        
        
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
            }
        }
        
        System.out.println("categorias procesadas correctamente");
        
        
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
    
}



