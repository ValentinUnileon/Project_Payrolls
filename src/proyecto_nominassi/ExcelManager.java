/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_nominassi;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import controlador.Categorias;
import controlador.Nomina;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jdk.internal.org.xml.sax.SAXException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import controlador.Trabajador;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.poi.ss.usermodel.CellType;
import org.w3c.dom.Text;

import controlador.Empresas;
import controlador.Categorias;
import java.math.BigInteger;
import java.util.Calendar;

/**
 *
 * @author David
 */
public class ExcelManager {
    
    //Ubicacion excel
    //portatil private String localizacionExcel ="C:/Users/valen/Documents/git/Proyecto_NominasSI/src/resources/SistemasInformacionII.xlsx";
    //private String localizacionExcel ="C:/Users/w10/Documents/GitHub/Proyecto_NominasSI/src/resources/SistemasInformacionII.xlsx";
    private String localizacionExcel ="C:/Users/Torre/Documents/GitHub/Proyecto_NominasSI/src/resources/SistemasInformacionII.xlsx";
    
    //Datos de las hojas del excel
    
    private List<Trabajador> trabajadoresHoja1= new ArrayList<>();
    
    List<Trabajador> trabajadoresErroneos= new ArrayList();
    List<String> CCCErroneo= new ArrayList();
    
    
    private final Map<String, String> categoria_Complementos= new HashMap<>();
    private final Map<String, String> categoria_SalarioBase=new HashMap<>();
    
    private final Map<Float, Float> trienios= new HashMap<>();
    
    private final Map<Float, Float> retencion = new HashMap<>();
    
    private final Map<String, Float> datosEmpresa = new HashMap<>(); 
    
    //
    
    private static final List<Character> letras = new ArrayList<Character>();
    private static final List<Character> letrasPais = new ArrayList<Character>();
    private static final List<String> numerosPais = new ArrayList<String>();
    private static final List<Integer> numerosMultiplicarCCC = new ArrayList<Integer>();
    
    private static final List<Nomina> nominasTrabajadores = new ArrayList<Nomina>();
    
    
    //Metodos para guardar la hojas del excel
    
        
    public void mapearHoja1() throws IOException, ParseException{
        
        List<String> paisOrigen = this.obtenerColumnasDatos(localizacionExcel, "Pais Origen Cuenta Bancaria", 0);
        List<String> codigoCuenta = this.obtenerColumnasDatos(localizacionExcel, "CodigoCuenta", 0);
        List<String> iban = this.obtenerColumnasDatos(localizacionExcel, "IBAN", 0);
        List<String> email = this.obtenerColumnasDatos(localizacionExcel, "Email", 0);
        List<String> fechaAltaEmpresa = this.obtenerColumnasDatos(localizacionExcel, "FechaAltaEmpresa", 0);
        List<String> cifEmpresa = this.obtenerColumnasDatos(localizacionExcel, "Cif empresa", 0);
        List<String> nombreEmpresa = this.obtenerColumnasDatos(localizacionExcel, "Nombre empresa", 0);
        List<String> categoria = this.obtenerColumnasDatos(localizacionExcel, "Categoria", 0);
        List<String> apellido1 = this.obtenerColumnasDatos(localizacionExcel, "Apellido1", 0);
        List<String> apellido2 = this.obtenerColumnasDatos(localizacionExcel, "Apellido2", 0);
        List<String> nombre = this.obtenerColumnasDatos(localizacionExcel, "Nombre", 0);
        List<String> dnis = this.obtenerColumnasDatos(localizacionExcel, "NIF/NIE", 0);
        List<String> prorrata = this.obtenerColumnasDatos(localizacionExcel, "ProrrataExtra", 0);
        List<String> fechaBajaLaboral = this.obtenerColumnasDatos(localizacionExcel, "FechaBajaLaboral", 0);
        List<String> fechaAltaLaboral = this.obtenerColumnasDatos(localizacionExcel, "FechaAltaLaboral", 0);

        int contador=2;

        for(int i=0; i<codigoCuenta.size(); i++){
            
            if(!codigoCuenta.get(i).equals("")){
               
                //System.out.println("El dni de la fila es "+" ---- "+fechaAltaLaboral.get(i)+" ----- APE "+apellido1.get(i)+" y el nombre es "+nombre.get(i));                
                //System.out.println(fechaAltaEmpresa.get(i));
                
                //Preparamos las fechas

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMM-yyyy", new Locale("es", "ES"));
                SimpleDateFormat formatoFechaNuevo = new SimpleDateFormat("dd-MM-yyyy");
 
                /* Para cuando generemos las fechas  -- gestionar para cuando no hay fecha y se le pasa ""
                Date fecha_fechaAltaEmpresa = formatoFecha.parse(fechaAltaEmpresa.get(i));
                Date fecha_fechaBajaLaboral = formatoFecha.parse(fechaBajaLaboral.get(i));
                Date fecha_fechaAltaLaboral = formatoFecha.parse(fechaAltaLaboral.get(i));
 
                */
                           
                Date fecha_fechaAltaEmpresa = formatoFecha.parse(fechaAltaEmpresa.get(i));
                Date fecha_fechaAltaLaboral = null;
                Date fecha_fechaBajaLaboral = null;
                
                if (fechaBajaLaboral.get(i).length() > 0) {              
                    fecha_fechaBajaLaboral = formatoFecha.parse(fechaBajaLaboral.get(i));  
                }
                 
                if (fechaAltaLaboral.get(i).length() > 0) {              
                    fecha_fechaAltaLaboral = formatoFecha.parse(fechaAltaLaboral.get(i));   
                }
                
                boolean prorrataAux = true;
                
                if (prorrata.get(i).equals("SI")) {
                    prorrataAux = true;
                } else {
                    prorrataAux = false;
                }

                Trabajador aux = new Trabajador(this.obtenerNumFila(localizacionExcel, codigoCuenta.get(i), apellido1.get(i))
                        , codigoCuenta.get(i)
                        , iban.get(i)
                        , email.get(i)
                        , fecha_fechaAltaEmpresa
                        , apellido1.get(i)
                        , apellido2.get(i)
                        , nombre.get(i)
                        , dnis.get(i)
                        , fecha_fechaBajaLaboral
                        , fecha_fechaAltaLaboral
                        , paisOrigen.get(i)
                        , prorrataAux); 
                

                // EMPRESA TIENE ID -> NOMBRE -> CIF
                
                Empresas EmpresaAux = new Empresas(nombreEmpresa.get(i), cifEmpresa.get(i));
                aux.setEmpresa(EmpresaAux);
                
                Categorias categorias = new Categorias();
                categorias.setNombreCategoria(categoria.get(i));
                aux.setCategoria(categorias); //TODO - RELLENAR CATEGORIA CORRECTAMENTE
                
                
                trabajadoresHoja1.add(aux);
     
            }

        }
        //System.out.println("FINAL "+trabajadoresHoja1.get(0).getNombre()+" locura longitud "+ trabajadoresHoja1.size());
       

    }
    
    
    public void mapearHoja2() throws IOException{
        
        List<String> categoria=this.obtenerColumnasDatos(localizacionExcel, "Categoria", 1);
        List<String> complementos=this.obtenerColumnasDatos(localizacionExcel, "Complementos", 1);
        List<String> salarioBase=this.obtenerColumnasDatos(localizacionExcel, "Salario Base", 1);
  
        for(int i=0; i<categoria.size(); i++){
            categoria_Complementos.put(categoria.get(i), complementos.get(i));    
            categoria_SalarioBase.put(categoria.get(i), salarioBase.get(i));
        }

        
        /*
        for (Map.Entry<String, String> entry : categoria_SalarioBase.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }  */            
    }
    
    
    public void mapearHoja3() throws IOException{
       
        List<String> numTrienios = this.obtenerColumnasDatos(localizacionExcel, "Número de trienios", 2);
        List<String> impBruto = this.obtenerColumnasDatos(localizacionExcel, "Importe bruto", 2);

        for(int i=0; i<numTrienios.size(); i++){
            trienios.put(Float.parseFloat(numTrienios.get(i)), Float.parseFloat(impBruto.get(i)));
        }
        
    }
    
    public void mapearHoja4() throws IOException{
        
        List<String> brutoAnual = this.obtenerColumnasDatos(localizacionExcel, "Bruto anual", 3);
        List<String> columnaRetencion = this.obtenerColumnasDatos(localizacionExcel, "Retención", 3);
        
        for(int i=0; i<brutoAnual.size(); i++ ){
            retencion.put(Float.parseFloat(brutoAnual.get(i)), Float.parseFloat(columnaRetencion.get(i))/100 );
        }
        
     
        //obtenemos el nombre la columna
        
        FileInputStream archivo = new FileInputStream(localizacionExcel);
        XSSFWorkbook libro = new XSSFWorkbook(archivo);
        Sheet hoja = libro.getSheetAt(3); 
        Row fila = hoja.getRow(0); 
        Cell celda = fila.getCell(5); 
        String nombreColum1 = celda.getStringCellValue();
        Cell celda2 = fila.getCell(6);
        Double nombreColum2 = celda2.getNumericCellValue();
        libro.close();
       
        
        List<String> accidentesTrabajo = this.obtenerColumnasDatosAux(nombreColum1, 3);
        List<String> valores = this.obtenerColumnasDatosAux( nombreColum2+"", 3);

        //System.out.println("ACCIDENTES: "+ accidentesTrabajo.toString());
        //System.out.println("VALORES: "+ valores.toString());
        datosEmpresa.put(nombreColum1, Float.parseFloat(nombreColum2+"")/100);

        for(int j=0; j<accidentesTrabajo.size(); j++ ){
            datosEmpresa.put(accidentesTrabajo.get(j), Float.parseFloat(valores.get(j))/100);
        }
        

        
    }

   
    //Final metodos para guardar la hojas del excel
    public List<String> obtenerColumnasDatos(String localizacionExcel, String nombreColumna, int numHoja) throws FileNotFoundException, IOException {
        int contadorFilas = 1;
        int tope = 0; 
        int bloqueo = 0; 
        int filaActual = 0; 
        int celdaActual = 0;


        File archivoExcel = new File(localizacionExcel);                
        InputStream flujoEntrada = new FileInputStream(archivoExcel);
        XSSFWorkbook libroExcel = new XSSFWorkbook(flujoEntrada); 
        XSSFSheet hojaExcel = libroExcel.getSheetAt(numHoja); 

        Iterator<Row> iteradorFilas = hojaExcel.iterator(); 
        List<String> listaResultado = new ArrayList<>();
        

        while(iteradorFilas.hasNext()) 
        {
            XSSFRow fila = (XSSFRow) iteradorFilas.next();     
            Iterator<Cell> iteradorCeldas = fila.cellIterator();          

            while(iteradorCeldas.hasNext())
            {
                XSSFCell celda = (XSSFCell) iteradorCeldas.next();     
                if(celda.toString().equals(nombreColumna) && bloqueo == 0)
                {
                    tope = contadorFilas;
                    bloqueo = 1;
                }                                            
                if(bloqueo == 1 && filaActual == 1)
                {
                    if(fila.getCell(tope-1) != null && celdaActual == 0)
                    {
                        listaResultado.add(fila.getCell(tope-1).toString());
                        celdaActual = 1;
                    }else if(fila.getCell(tope-1) == null && celdaActual == 0)
                    {
                        listaResultado.add("");
                        celdaActual = 1;
                    }
                }
                contadorFilas++;
            }
            filaActual = 1;
            celdaActual = 0;
        }

        return listaResultado;
    }
    
    public List<String> obtenerColumnasDatosAux(String nombreColumna, int numHoja) {
    
        List<String> lista = new ArrayList<>();
        try {
            FileInputStream archivoExcel = new FileInputStream(localizacionExcel); 
            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel); 
            XSSFSheet hojaExcel = libroExcel.getSheetAt(numHoja);

            int numColumna = -1;
            Row filaColumnas = hojaExcel.getRow(0); 

            for (Cell celda : filaColumnas) {
                XSSFCell celdaS = (XSSFCell) celda;

                if (celdaS.toString().equals(nombreColumna)) {
                    numColumna = celda.getColumnIndex();
                    break;
                }
            }

            boolean terminar=false;
            int i=1;
            
            while(!terminar){
                Row fila = hojaExcel.getRow(i);
                XSSFCell celda = (XSSFCell) fila.getCell(numColumna);
                
                if(fila!=null && celda!=null){
                    lista.add(celda.toString());
                }else{
                    terminar = true;
                }
                i++;
            }

            FileOutputStream archivoSalida = new FileOutputStream(localizacionExcel); 
            libroExcel.write(archivoSalida);
            archivoSalida.close();

            libroExcel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return lista;
    }    
    
    
    
    public void modificarDatos(String localizacionExcel, int numHoja, String antiguaCelda, String nuevaCelda) throws FileNotFoundException, IOException {


            File archivoExcel = new File(localizacionExcel);                
            InputStream flujoEntrada = new FileInputStream(archivoExcel);
            XSSFWorkbook libroExcel = new XSSFWorkbook(flujoEntrada); 
            XSSFSheet hojaExcel = libroExcel.getSheetAt(numHoja); 

            Iterator<Row> iteradorFilas = hojaExcel.iterator(); 
            List<String> listaResultado = new ArrayList<>();



            while(iteradorFilas.hasNext()) 
            {
                XSSFRow fila = (XSSFRow) iteradorFilas.next();     
                Iterator<Cell> iteradorCeldas = fila.cellIterator();      
                
                //System.out.println("la fila actual"+iteradorFilas);

                while(iteradorCeldas.hasNext())
                {
                    XSSFCell celda = (XSSFCell) iteradorCeldas.next();  
                    //System.out.println("la celda es: "+ celda.toString());
                    if(celda.toString().equals(antiguaCelda) )                   
                    {
                        celda.setCellValue(nuevaCelda);
                        //System.out.println("la celda es: "+ celda.toString());
                    }                                            

                }

            }
        flujoEntrada.close();
         //System.out.println("la celda es: ");
        
         try{
            FileOutputStream output_file = new FileOutputStream(new File(localizacionExcel));
            libroExcel.write(output_file);
            output_file.close(); 
            libroExcel.close();
            
         } catch (Exception e) {

            e.printStackTrace();
         }
       
    }
        
 
        
    //solo encuentra la primera aparicion    
    public List<String> obtenerFila(String localizacionExcel, String elemFila) throws FileNotFoundException, IOException{    //devuelve una lista con los elementos de una fila. La fila sera en la que se encuentre elemFila
    
        File archivoExcel = new File(localizacionExcel);                
        InputStream flujoEntrada = new FileInputStream(archivoExcel);
        XSSFWorkbook libroExcel = new XSSFWorkbook(flujoEntrada); 
        XSSFSheet hojaExcel = libroExcel.getSheetAt(0); 

        Iterator<Row> iteradorFilas = hojaExcel.iterator(); 
        List<String> listaResultado = new ArrayList<>();
        boolean encontrado=false;



        while(iteradorFilas.hasNext() && encontrado==false) 
        {
            XSSFRow fila = (XSSFRow) iteradorFilas.next(); 
            //System.out.println("NumFIla es: "+fila.getRowNum());          //PARA OBTENER EL NUMERO DE LA FILA DEL EXCEL
            Iterator<Cell> iteradorCeldas = fila.cellIterator();          

            while(iteradorCeldas.hasNext())
            {
                XSSFCell celda = (XSSFCell) iteradorCeldas.next();

                
                if(celda.toString().equals(elemFila)) {
                    encontrado=true;
                    int num=0;
                    Iterator<Cell> iteradorCeldasFila = fila.cellIterator();        //creamos nuevo iterador para la fila
                    while(iteradorCeldasFila.hasNext()) {
                        XSSFCell celdaFila = (XSSFCell) iteradorCeldasFila.next();
                        num=fila.getRowNum()+1;
                        
                        listaResultado.add(celdaFila.toString());
                        

                        
                        
                    }
                    listaResultado.add(""+num);
                    
                    
                }
            }

        }
        
      
        return listaResultado;
    }
    
    public List<Integer> obtenerRepetidos(String dniRepetido){
        
        int contador=0;
        List<Integer> resultado = new ArrayList<>();
        
        for(int i=0; i<trabajadoresHoja1.size(); i++){
            
            if(trabajadoresHoja1.get(i).getNifnie().equals(dniRepetido) && contador>0){
                resultado.add(i);                
            }else if(trabajadoresHoja1.get(i).getNifnie().equals(dniRepetido)){
                contador++;
            }
            
        }
        
        //Devuelve una lista con los numeros de los trabajadores con los dnis repetidos
        
        return resultado;
    }
    
    
    public List<String> obtenerFilaRepeticiones(String localizacionExcel, String elemFila, int repeticion) throws FileNotFoundException, IOException{    //devuelve una lista con los elementos de una fila. La fila sera en la que se encuentre elemFila
    
        File archivoExcel = new File(localizacionExcel);                
        InputStream flujoEntrada = new FileInputStream(archivoExcel);
        XSSFWorkbook libroExcel = new XSSFWorkbook(flujoEntrada); 
        XSSFSheet hojaExcel = libroExcel.getSheetAt(0); 

        Iterator<Row> iteradorFilas = hojaExcel.iterator(); 
        List<String> listaResultado = new ArrayList<>();
        boolean encontrado=false;
        
        int aux=repeticion-1;

        while(iteradorFilas.hasNext() && encontrado==false) 
        {
            XSSFRow fila = (XSSFRow) iteradorFilas.next(); 
            //System.out.println("NumFIla es: "+fila.getRowNum());          //PARA OBTENER EL NUMERO DE LA FILA DEL EXCEL
            Iterator<Cell> iteradorCeldas = fila.cellIterator();          

            while(iteradorCeldas.hasNext())
            {
                XSSFCell celda = (XSSFCell) iteradorCeldas.next();

                
                if(celda.toString().equals(elemFila) && aux==0) {
                    
                    
                    encontrado=true;
                    int num=0;
                    Iterator<Cell> iteradorCeldasFila = fila.cellIterator();        //creamos nuevo iterador para la fila
                    while(iteradorCeldasFila.hasNext()) {
                        XSSFCell celdaFila = (XSSFCell) iteradorCeldasFila.next();
                        num=fila.getRowNum()+1;
                        
                        listaResultado.add(celdaFila.toString());                     
                        
                    }
                    listaResultado.add(""+num);

                }else if(celda.toString().equals(elemFila)){
                    aux= aux-1;
                }
            }

        }

       
        return listaResultado;
    }

    public Map<String, Integer> contarRepeticiones(List<String> lista){
       
        Map<String, Integer> map = new HashMap<>();
        for (String elemento : lista) {
            if (map.containsKey(elemento)) {
                map.put(elemento, map.get(elemento) + 1);
            } else {
                map.put(elemento, 1);
            }
        }
       
        
        return map;
    }
    
    public int obtenerNumFila(String localizacionExcel, String codigoCuenta, String apellido1) throws FileNotFoundException, IOException{    //devuelve una lista con los elementos de una fila. La fila sera en la que se encuentre elemFila
    
        File archivoExcel = new File(localizacionExcel);                
        InputStream flujoEntrada = new FileInputStream(archivoExcel);
        XSSFWorkbook libroExcel = new XSSFWorkbook(flujoEntrada); 
        XSSFSheet hojaExcel = libroExcel.getSheetAt(0); 

        Iterator<Row> iteradorFilas = hojaExcel.iterator(); 
        boolean encontrado1=false;
        boolean encontrado2=false;
        int num=-1;
        
        while(iteradorFilas.hasNext() && (encontrado1==false || encontrado2==false) ) 
        {
            
            XSSFRow fila = (XSSFRow) iteradorFilas.next(); 
            Iterator<Cell> iteradorCeldas = fila.cellIterator();      
            
            encontrado1=false;
            encontrado2=false;        

            while(iteradorCeldas.hasNext())
            {
                XSSFCell celda = (XSSFCell) iteradorCeldas.next();
                
                if(celda.toString().equals(codigoCuenta)) {
                    encontrado1=true;       
                }
                
                if(celda.toString().equals(apellido1)) {
                    encontrado2=true;
                    
                    if(encontrado1 && encontrado2){
                        num=fila.getRowNum()+1;
                    }
                            
                }
                
                    
            }
        }
        return num;
    }    
        
    public void procesarDNI() throws FileNotFoundException, IOException, ParserConfigurationException, SAXException, org.xml.sax.SAXException {
        
        // SE RELLENA LA LISTA QUE CONTIENE LAS LETRAS DE LOS DNI
        char[] listaAux = new char[]{'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        List<Trabajador> trabajadoresErrores= new ArrayList<>();
        for(int i=0; i<23; i++) {
            letras.add(listaAux[i]);
        }
        

        //List<String> listaDNI = this.obtenerColumnasDatos(localizacionExcel, "NIF/NIE", 0);
        List<String> listaDNI= new ArrayList<>();
        
        for(int j=0; j< trabajadoresHoja1.size(); j++){
            listaDNI.add(trabajadoresHoja1.get(j).getNifnie());
        }
        Map<String, Integer> map = contarRepeticiones(listaDNI);
        List<String> listaDNI_Repetidos = new ArrayList<>();
        
        
        
        for(int i=0; i<trabajadoresHoja1.size(); i++){       
            
            
                
                //System.out.println("el elemento "+listaDNI.get(i)+" se repite estas veces: "+map.get(listaDNI.get(i)) );

                if(map.get(listaDNI.get(i))>1 && !listaDNI_Repetidos.contains(listaDNI.get(i)) && !listaDNI.get(i).equals("")){  //comprobar que el dni se repite y que no se encuentra en la lista de "ya añadidos"

                    //System.out.println("Añadidos a XML ERRORES por repeticion: "+ listaDNI.get(i)+" "+ map.get(listaDNI.get(i)));
                    
                    List<Integer> numTrabajadoresRepetidos = obtenerRepetidos(listaDNI.get(i));                
                    
                    for(int j=0; j< numTrabajadoresRepetidos.size(); j++){ 

                        trabajadoresErrores.add(trabajadoresHoja1.get(numTrabajadoresRepetidos.get(j)));
                        
                    }
                    
                    listaDNI_Repetidos.add(listaDNI.get(i));
                }else{
              
                    if (listaDNI.get(i).length() > 0) {
                        
                        if (listaDNI.get(i).charAt(0) == 'X' || listaDNI.get(i).charAt(0) == 'Y' || listaDNI.get(i).charAt(0) == 'Z') {
                            
                            // ESTAMOS TRABAJANDO CON UN NIE
                            
                            int comprobacion=esValidoNIE(listaDNI.get(i));

                            switch(comprobacion){

                                case 2:
                                    //el error se puede subsanar -> LA LETRA ESTA MAL

                                    String dniArreglado = arreglarNIE(listaDNI.get(i)); 
                                    this.modificarDatos(localizacionExcel, 0, listaDNI.get(i), dniArreglado);
                                    trabajadoresHoja1.get(i).setNifnie(dniArreglado);
                                                                       
                                    
                                    break;
                                case 3:
                                    //el error no es subsanable -> ESTÁ MAL ESTRUCTURADO -> añadir al XML

                                   trabajadoresErrores.add(trabajadoresHoja1.get(i));
                                   break;
                            }
                        } else {    // ESTAMOS TRABAJANDO CON UN DNI
                        
                        
                        int comprobacion=esValidoDNI(listaDNI.get(i));

                        switch(comprobacion){

                            case 2:
                                //el error se puede subsanar -> LA LETRA ESTA MAL

                                String dniArreglado = arreglarDNI(listaDNI.get(i)); 
                                this.modificarDatos(localizacionExcel, 0, listaDNI.get(i), dniArreglado);
                                trabajadoresHoja1.get(i).setNifnie(dniArreglado);
                                break;
                            case 3:
                                //el error no es subsanable -> ESTÁ MAL ESTRUCTURADO -> añadir al XML

                               trabajadoresErrores.add(trabajadoresHoja1.get(i));

                               break;
                        }
                        
                        
                        }
                    }else{
                        trabajadoresErrores.add(trabajadoresHoja1.get(i));
                    }
                    
                    
                }
            
        }
       
        if(trabajadoresErrores.size()>0){
            
            try {
                this.agregarTrabajadoresAXML(trabajadoresErrores);
            } catch (TransformerException ex) {
               System.out.println(ex);
            }
            
        }
  
    }        
    
    public static int esValidoDNI(String dni) {
        
        // RETURN:
        // 1 - VALIDO
        // 2 - ERROR SUBSANABLE
        // 3 - ERROR NO SUBSANABLE
    
        int esValido = 3;
        char letra;
        int cantidad;

        if (dni.length() == 9) {   //el dni tiene longitud 9
                  
            if (estaBienEstructurado(dni)) {
                
                letra = dni.charAt(8);
                cantidad = Integer.parseInt(dni.substring(0, dni.length()-1));

                if (letra == obtenerLetraCorrectaDNI(cantidad)) {  //si la letra es la correcta, el dni es valido

                    esValido = 1;
                } else {    // si la letra no es la correcta, el dni es erroneo pero subsanable
                
                    esValido = 2;
                }
            } 
        }

        return esValido;
    }

    public static boolean estaBienEstructurado(String dni) {
    
        boolean estaBien = true;
        boolean encontrado = false;
        char letra;

            for (int i=0; i<9; i++) {

                if (i<8) {    // PARA LOS 8 PRIMEROS DIGITOS SE COMPRUEBA QUE SEAN NUMEROS
                    encontrado = false;
                    letra = dni.charAt(i);

                    if (letra == '1' || letra == '2'|| letra == '3' || letra == '4' || letra == '5' || letra == '6' || letra == '7' || letra == '8' || letra == '9' || letra == '0') {
                        encontrado = true;
                    }
                    if (!encontrado) {
                        estaBien = false;
                    }
                } else {   // PARA EL ULTIMO DIGITO SE COMPRUEBA QUE SEA UNA DE LAS LETRAS VALIDAS
                    encontrado = false;
                    for (int j=0; j<letras.size(); j++) {
                        if (dni.charAt(8) == letras.get(j)) {
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        estaBien = false;
                    }
                }
            }     
        
        return estaBien;
    }

    public static char obtenerLetraCorrectaDNI(int suma){   //se devuelve la letra correcta teniendo en cuenta el numero
        int numeroBusqueda = suma % 23;
        char caracterRetorno = letras.get(numeroBusqueda);
        return caracterRetorno;
    }
    
    public static String arreglarDNI(String dni){

        String nuevoDNI = String.copyValueOf(dni.toCharArray());
        int cantidad = Integer.parseInt(dni.substring(0, dni.length()-1));
        char letra = obtenerLetraCorrectaDNI(cantidad);     //se obtiene la letra correspondiente al numero 
        nuevoDNI = ((cantidad + "")+ letra);   

        if (nuevoDNI.length() < 9) {
            for (int i=nuevoDNI.length(); i<9; i++) {
                nuevoDNI = ('0'+nuevoDNI);
            }
        }
        
        return nuevoDNI;
    }
    
    public static String arreglarNIE(String nie){

        String nuevoNIE = String.copyValueOf(nie.toCharArray());
        
        String nieAux = "";
                
        if (nie.charAt(0) == 'X') {                    
            nieAux = "0";
            nieAux = nieAux.concat(nie.substring(1));                   
                    
        } else if (nie.charAt(0) == 'Y') {
            nieAux = "1";
            nieAux = nieAux.concat(nie.substring(1));
        } else if (nie.charAt(0) == 'Z'){
        
            nieAux = "2";
            nieAux = nieAux.concat(nie.substring(1));
        }
        
        int cantidad = Integer.parseInt(nieAux.substring(0, nieAux.length()-1));

        char letra = obtenerLetraCorrectaDNI(cantidad);
        nuevoNIE = (nie.substring(0, nie.length()-1)+ letra);
        

        return nuevoNIE;
    }
    
    public static int esValidoNIE(String nie) {
        
        // 1 - VALIDO
        // 2 - ERROR SUBSANABLE
        // 3 - ERROR NO SUBSANABLE
    
        int esValido = 3;
        char letra;
        int cantidad;

        if (nie.length() == 9) {
                  
            if (estaBienEstructuradoNIE(nie)) {
                
                letra = nie.charAt(8);
                String nieAux = "";
                
                if (nie.charAt(0) == 'X') {                    
                    nieAux = "0";
                    nieAux = nieAux.concat(nie.substring(1));                   
                    
                } else if (nie.charAt(0) == 'Y') {
                    nieAux = "1";
                    nieAux = nieAux.concat(nie.substring(1));
                } else if (nie.charAt(0) == 'Z'){
                
                    nieAux = "2";
                    nieAux = nieAux.concat(nie.substring(1));
                }
                
                cantidad = Integer.parseInt(nieAux.substring(0, nieAux.length()-1));
                
                if (letra == obtenerLetraCorrectaDNI(cantidad)) {

                    esValido = 1;
                } else {
                
                    esValido = 2;
                }
            } 
        }

        return esValido;
    }
    
    public static boolean estaBienEstructuradoNIE(String nie) {
    
        boolean estaBien = true;
        boolean encontrado = false;
        char letra;

        for (int i=0; i<9; i++) {
            
            if (i == 0) {
            
                if (nie.charAt(i) == 'X' || nie.charAt(i) == 'Y' || nie.charAt(i) == 'Z') {
                
                    // LETRA CORRECTA
                } else {
                
                    estaBien = false;
                }
            }
            
            if (i<8 && i>0) {    // PARA LOS 7 SIGUIENTES DIGITOS SE COMPRUEBA QUE SEAN NUMEROS
                
                encontrado = false;
                letra = nie.charAt(i);

                if (letra == '1' || letra == '2'|| letra == '3' || letra == '4' || letra == '5' || letra == '6' || letra == '7' || letra == '8' || letra == '9' || letra == '0') {
                    
                    encontrado = true;
                }

                if (!encontrado) {
                    
                    estaBien = false;
                }
                
            } 
            
            if (i == 8) {// PARA EL ULTIMO DIGITO SE COMPRUEBA QUE SEA UNA DE LAS LETRAS VALIDAS

                encontrado = false;
                
                for (int j=0; j<letras.size(); j++) {
                    if (nie.charAt(8) == letras.get(j)) {
                        
                        encontrado = true;
                    }
                }
                
                if (!encontrado) {
                    estaBien = false;
                }
            }
        }
        return estaBien;
    }
    
    public void agregarTrabajadoresAXML(List<Trabajador> trabajadores) throws ParserConfigurationException, IOException, SAXException, TransformerException, org.xml.sax.SAXException {


            try{
            // cargamos el archivo XML existente en un objeto Document

            // String rutaXML = "C:/Users/w10/Documents/GitHub/Practica_SI/NominasSI/src/resources/Errores.xml";
            //portatil String rutaXML = "C:/Users/valen/Documents/git/Practica_SI/NominasSI/src/resources/Errores.xml";
            String rutaXML = "C:/Users/Torre/Documents/GitHub/Proyecto_NominasSI/src/resources/Errores.xml";



            File archivoXML = new File(rutaXML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.newDocument();
            Element rootElement = doc.createElement("Trabajadores");
            doc.appendChild(rootElement);

            // obtenemos la raíz del documento existente
            Element eRaiz = doc.getDocumentElement();

            // creamos un nuevo elemento para cada trabajador
            for (int i = 0; i < trabajadores.size(); i++) {

                Element xmlTrabajador = doc.createElement("Trabajador");


                Attr atributoID = doc.createAttribute("id");
                atributoID.setValue(""+trabajadores.get(i).getIdTrabajador());
                xmlTrabajador.setAttributeNode(atributoID);

                Element nif = doc.createElement("NIF_NIE");
                nif.appendChild(doc.createTextNode(trabajadores.get(i).getNifnie()));
                xmlTrabajador.appendChild(nif);


                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(trabajadores.get(i).getNombre()));
                xmlTrabajador.appendChild(nombre);



                Element apellido1 = doc.createElement("PrimerApellido");
                apellido1.appendChild(doc.createTextNode(trabajadores.get(i).getApellido1()));
                xmlTrabajador.appendChild(apellido1);

                Element apellido2 = doc.createElement("SegundoApellido");
                apellido2.appendChild(doc.createTextNode(trabajadores.get(i).getApellido2()));
                xmlTrabajador.appendChild(apellido2);


                Element empresa = doc.createElement("Empresa");
                empresa.appendChild(doc.createTextNode(trabajadores.get(i).getEmpresa().getNombre()));
                xmlTrabajador.appendChild(empresa);

                Element categoria = doc.createElement("Categoria");
                categoria.appendChild(doc.createTextNode(trabajadores.get(i).getCategoria().getNombreCategoria()));
                xmlTrabajador.appendChild(categoria);

                // añadimos el elemento del trabajador a la raíz del documento
                eRaiz.appendChild(xmlTrabajador);
            }

            // actualizamos el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // configuramos la propiedad para que se escriba en varias líneas
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivoXML);
            transformer.transform(source, result);

            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    
    
    //--------------------------------------------------PRACTICA 3----------------------------------------------------
    
    public void escribirCeldaColumna(String nombreColumna, String contenido, int posColumna, int numHoja) {
    
        try {
            FileInputStream archivoExcel = new FileInputStream(localizacionExcel); 
            XSSFWorkbook libroExcel = new XSSFWorkbook(archivoExcel); 
            XSSFSheet hojaExcel = libroExcel.getSheetAt(numHoja);

            int numColumna = -1;
            Row filaColumnas = hojaExcel.getRow(0); 

            for (Cell celda : filaColumnas) {
                if (celda.getCellType() == CellType.STRING && celda.getStringCellValue().equals(nombreColumna)) {
                    numColumna = celda.getColumnIndex();
                    break;
                }
            }


            Row fila = hojaExcel.getRow(posColumna); 
            Cell celda = fila.createCell(numColumna); 
            celda.setCellValue(contenido); 

            FileOutputStream archivoSalida = new FileOutputStream(localizacionExcel); 
            libroExcel.write(archivoSalida);
            archivoSalida.close();

            libroExcel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void generarGmailTrabajadores() throws IOException{
        
        
        for(int i=0; i<trabajadoresHoja1.size(); i++){
            //System.out.println("TE HE PILLADO "+trabajadoresHoja1.get(i).getIdTrabajador());
            if(trabajadoresHoja1.get(i).getIdTrabajador()==1){
               System.out.println("TE HE PILLADO "+trabajadoresHoja1.get(i).getApellido2()); 
            }
            
            if(trabajadoresHoja1.get(i).getEmail().equals("")){                     //solo lo genera cuando el trabajador no tiene email 
                
                //generar email y cambiarlo en el trabjador y en el excel//COMPROBAR SI HAY SEGUNDO APELLIDO
                
                String correoGeneradoCuerpo = trabajadoresHoja1.get(i).getNombre().charAt(0)+
                                        Character.toString(trabajadoresHoja1.get(i).getApellido1().charAt(0)); 
                                        
                
                if(!trabajadoresHoja1.get(i).getApellido2().equals("")){
                    correoGeneradoCuerpo=correoGeneradoCuerpo+Character.toString(trabajadoresHoja1.get(i).getApellido2().charAt(0));
                }
                
                String correGeneradoDominio= digitoRepeticion(correoGeneradoCuerpo) +
                                        "@"+ trabajadoresHoja1.get(i).getEmpresa().getNombre()+".com";
                

                this.trabajadoresHoja1.get(i).setEmail(correoGeneradoCuerpo+correGeneradoDominio);

                this.escribirCeldaColumna("Email", correoGeneradoCuerpo+correGeneradoDominio, trabajadoresHoja1.get(i).getIdTrabajador()-1, 0);
            }

        }
        
    }
    
    public String digitoRepeticion(String cuerpo){
        
        int contador=0;
        
        for(int i=0; i<trabajadoresHoja1.size(); i++){
            
            if(!trabajadoresHoja1.get(i).getEmail().equals("")){
                
                String cuerpoCorreo = "";
                boolean parada=false;
                
                for(int j=0; j<trabajadoresHoja1.get(i).getEmail().length() && parada==false; j++){

                    if(trabajadoresHoja1.get(i).getEmail().charAt(j)!= '@'){
                        cuerpoCorreo=cuerpoCorreo+Character.toString(trabajadoresHoja1.get(i).getEmail().charAt(j));
                    }else{
                        parada=true;
                    }                   
                }

                if(cuerpoCorreo.equals(cuerpo)){
                    contador++;
                }                 
            }       
        }
        
        String resultado="";
        
        if(contador<10){
            resultado="0"+contador;
        }else{
            resultado=""+contador;
        }
        
        return resultado;
    }
    
    public void generarIBANTrabajadores() throws IOException, ParserConfigurationException, TransformerException, SAXException, org.xml.sax.SAXException {
        
        //RELLENAMOS LAS LISTAS DE LETRAS Y NUMEROS NECESARIAS PARA HACER LOS CALCULOS
        
        
        char[] listaLetrasAux = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        for(int i=0; i<listaLetrasAux.length; i++) {
            letrasPais.add(listaLetrasAux[i]);
        }
        
        String[] listaNumerosAux = new String[]{"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35"};

        for(int i=0; i<listaNumerosAux.length; i++) {
            numerosPais.add(listaNumerosAux[i]);
        }
    
        this.comprobarCCCTrabajadores();
        
        for(int i=0; i<trabajadoresHoja1.size(); i++){
            
            if(trabajadoresHoja1.get(i).getIban().equals("")){
                
                //OBTENEMOS EL NUMERO DE CUENTA Y PAIS DEL TRABAJADOR
                
                String numeroCuenta = trabajadoresHoja1.get(i).getCodigoCuenta();
                String paisOrigen = trabajadoresHoja1.get(i).getPaisOrigen();
                
                //OBTENEMOS LOS DIGITOS DE CONTROL DEL IBAN
                
                String numerosIBAN = obtenerNumerosIBAN(paisOrigen, numeroCuenta);
                
                String ibanFinal = paisOrigen + numerosIBAN + numeroCuenta;
                
                // System.out.println(ibanFinal);
                
                for(int j =0; j<trabajadoresErroneos.size(); j++){
                    if(trabajadoresErroneos.get(j).getApellido1().equals(trabajadoresHoja1.get(i).getApellido1()) && trabajadoresErroneos.get(j).getCodigoCuenta().equals(trabajadoresHoja1.get(i).getCodigoCuenta())){
                        trabajadoresErroneos.get(j).setIban(ibanFinal);
                    } 
                }

                trabajadoresHoja1.get(i).setIban(ibanFinal);
                //System.out.println("CUIDAO QUE LO TENGO "+ trabajadoresHoja1.get(i).getIdTrabajador()+ "el iban es "+trabajadoresHoja1.get(i).getIban()+ " el nombre "+ trabajadoresHoja1.get(i).getApellido1());
                this.escribirCeldaColumna("IBAN",  trabajadoresHoja1.get(i).getIban(), trabajadoresHoja1.get(i).getIdTrabajador()-1, 0); 
                
            }
               
        }
        
        this.agregarErroresCCCXML(trabajadoresErroneos, CCCErroneo);
    
    }
    
    public String obtenerNumerosIBAN(String paisOrigen, String numeroCuenta) {
        
        String numerosLetra= "";
        
        for (int i=0; i<paisOrigen.length(); i++) {
            for (int j=0; j<letrasPais.size(); j++) {
                if (paisOrigen.charAt(i) == letrasPais.get(j)) {  
                    numerosLetra = numerosLetra + numerosPais.get(j);
                }
            }
        }
        
        String numeroTotal = numeroCuenta +""+ numerosLetra+ "00";
        
        BigInteger numeroTotalAux = new BigInteger(numeroTotal);
        BigInteger numeroDivision = new BigInteger("97");
        
        BigInteger resto = numeroTotalAux.mod(numeroDivision);
        
        int numeroFinal = 98-resto.intValue();
        
        return numeroFinal+"";
    }
    
    public void comprobarCCCTrabajadores() throws IOException, ParserConfigurationException, TransformerException, SAXException, org.xml.sax.SAXException {
        
   
        Integer[] listaMultiplicacionesAux = new Integer[]{1, 2, 4, 8, 5, 10, 9, 7, 3, 6};

        for(int i=0; i<listaMultiplicacionesAux.length; i++) {
            numerosMultiplicarCCC.add(listaMultiplicacionesAux[i]);
        }
        
        for(int i=0; i<trabajadoresHoja1.size(); i++){
            
            String numeroCuenta = trabajadoresHoja1.get(i).getCodigoCuenta();

            if (!esCorrectoCCC(numeroCuenta)) {
                CCCErroneo.add(numeroCuenta);
                String numeroCuentaCorregido = corregirCCC(numeroCuenta);
                trabajadoresHoja1.get(i).setCodigoCuenta(numeroCuentaCorregido);
                this.escribirCeldaColumna("CodigoCuenta", numeroCuentaCorregido, trabajadoresHoja1.get(i).getIdTrabajador()-1, 0);

                trabajadoresErroneos.add(trabajadoresHoja1.get(i));
            }
        }
        
        
        
     
    }
    
    public boolean esCorrectoCCC(String numeroCuenta) {
    
        boolean esCorrecto = true;
        
        String primeraCadena = numeroCuenta.substring(0, 8);
        primeraCadena = "00"+primeraCadena;
        
        String segundaCadena = numeroCuenta.substring(10);
        
        int primeraCantidad=0;
        int segundaCantidad=0;
        for (int i=0; i<10; i++) {
        
            primeraCantidad = primeraCantidad + numerosMultiplicarCCC.get(i)*Character.getNumericValue(primeraCadena.charAt(i));
            segundaCantidad = segundaCantidad + numerosMultiplicarCCC.get(i)*Character.getNumericValue(segundaCadena.charAt(i));
        }
        
        int primerDigito = 11-(primeraCantidad%11);
        int segundoDigito = 11-(segundaCantidad%11);
        
        if (primerDigito == 10) {
            primerDigito = 1;
        } else if (primerDigito == 11) {
            primerDigito = 0;
        }
        
        if (segundoDigito == 10) {
            segundoDigito = 1;
        } else if (segundoDigito == 11) {
            segundoDigito = 0;
        }
        
        if (Character.getNumericValue(numeroCuenta.charAt(8)) == primerDigito && Character.getNumericValue(numeroCuenta.charAt(9)) == segundoDigito) {
        
            esCorrecto = true;
        
        } else {

            esCorrecto = false;
        }
        
        return esCorrecto;
    }
    
    public String corregirCCC(String numeroCuenta) {
    
        String primeraAux = numeroCuenta.substring(0, 8);
        String primeraCadena = "00"+primeraAux;
        
        String segundaCadena = numeroCuenta.substring(10);
        
        // 20960043 01 3468900000
        // 20960043 11 103468900000
        
        
        int primeraCantidad=0;
        int segundaCantidad=0;
        for (int i=0; i<10; i++) {
        
            primeraCantidad = primeraCantidad + numerosMultiplicarCCC.get(i)*Character.getNumericValue(primeraCadena.charAt(i));
            segundaCantidad = segundaCantidad + numerosMultiplicarCCC.get(i)*Character.getNumericValue(segundaCadena.charAt(i));
        }
        
        int primerDigito = 11-(primeraCantidad%11);
        int segundoDigito = 11-(segundaCantidad%11);
        
        if (primerDigito == 10) {
            primerDigito = 1;
        } else if (primerDigito == 11) {
            primerDigito = 0;
        }
        
        if (segundoDigito == 10) {
            segundoDigito = 1;
        } else if (segundoDigito == 11) {
            segundoDigito = 0;
        }
        
        String numeroCorregido = primeraAux + primerDigito + segundoDigito + segundaCadena;
        
        return numeroCorregido;
    }
    
    public void agregarErroresCCCXML(List<Trabajador> trabajadores, List<String> CCCErroneo) throws ParserConfigurationException, IOException, SAXException, TransformerException, org.xml.sax.SAXException {


            try{
            // cargamos el archivo XML existente en un objeto Document

            // String rutaXML = "C:/Users/w10/Documents/GitHub/Practica_SI/NominasSI/src/resources/ErroresCCC.xml";
            //portatil String rutaXML = "C:/Users/valen/Documents/git/Practica_SI/NominasSI/src/resources/ErroresCCC.xml";
            String rutaXML = "C:/Users/Torre/Documents/GitHub/Proyecto_NominasSI/src/resources/ErroresCCC.xml";



            File archivoXML = new File(rutaXML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.newDocument();
            Element rootElement = doc.createElement("Cuentas");
            doc.appendChild(rootElement);

            // obtenemos la raíz del documento existente
            Element eRaiz = doc.getDocumentElement();

            // creamos un nuevo elemento para cada trabajador
            for (int i = 0; i < trabajadores.size(); i++) {

                Element xmlTrabajador = doc.createElement("Cuenta");


                Attr atributoID = doc.createAttribute("id");
                atributoID.setValue(""+trabajadores.get(i).getIdTrabajador());
                xmlTrabajador.setAttributeNode(atributoID);

                Element nif = doc.createElement("Nombre");
                nif.appendChild(doc.createTextNode(trabajadores.get(i).getNombre()));
                xmlTrabajador.appendChild(nif);


                Element nombre = doc.createElement("Apellidos");
                nombre.appendChild(doc.createTextNode(trabajadores.get(i).getApellido1()+ " " +trabajadores.get(i).getApellido2()));
                xmlTrabajador.appendChild(nombre);


                Element apellido1 = doc.createElement("Empresa");
                apellido1.appendChild(doc.createTextNode(trabajadores.get(i).getEmpresa().getNombre()));
                xmlTrabajador.appendChild(apellido1);

                Element cccErroneo = doc.createElement("CCCErroneo");
                cccErroneo.appendChild(doc.createTextNode(CCCErroneo.get(i)));
                xmlTrabajador.appendChild(cccErroneo);


                Element iban = doc.createElement("IBANCorrecto");
                iban.appendChild(doc.createTextNode(trabajadores.get(i).getIban()));
                xmlTrabajador.appendChild(iban);


                // añadimos el elemento del trabajador a la raíz del documento
                eRaiz.appendChild(xmlTrabajador);
            }

            // actualizamos el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // configuramos la propiedad para que se escriba en varias líneas
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivoXML);
            transformer.transform(source, result);

            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    //---------------------------------------PRACTICA 4--------------------------------------------------
    
    
    public void generarNominasTrabajadores(String fecha) throws IOException, ParseException {
    
        
        //AÑADIR EXCEPCION POR SI LA NOMINA SOLICITADA COINCIDE CON LA EL MES DE LA ENTRADA DEL TRABAJADOR
        this.mapearHoja3();
        
        String dia = "01";
        String mes = fecha.substring(0, 2);
        String anio = fecha.substring(3);
        
        String fechaAux = dia+"/"+mes+"/"+anio;
        
        
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaActual = formatoFecha.parse(fechaAux);

       
        System.out.println("FECHA ACTUAL: "+ fechaActual.getDay() + "/" + fechaActual.getMonth() +"/"+ fechaActual.getYear());
    
        for(int i=0; i<trabajadoresHoja1.size(); i++){ 
            
            Date fechaAltaTrabajador = trabajadoresHoja1.get(i).getFechaAlta();
            

            String categoriaTrabajador_s = trabajadoresHoja1.get(i).getCategoria().getNombreCategoria();            
            
            boolean prorrata = trabajadoresHoja1.get(i).getProrrata();
            
            String salarioBase_s = categoria_SalarioBase.get(categoriaTrabajador_s);
            

            
            float salarioBase = Float.parseFloat(salarioBase_s);
            String complementos_s = categoria_Complementos.get(categoriaTrabajador_s);
            float complementos = Float.parseFloat(complementos_s);
            

            // HAY QUE CALCULAR EL NUMERO DE TRIENIOS QUE LLEVA EN LA EMPRESA
  
            float numeroTrienios = calcularNumeroTrienios(fechaAltaTrabajador, fechaActual);
            

            
            
            float importeBrutoTrienios = 0;
            
            if (numeroTrienios >0) {
                
                importeBrutoTrienios = trienios.get(numeroTrienios);    
         
            }
            
            //Calculamos bruto anual
            
            float brutoAnual = 0;
            float nominaMensual = 0; 
            

            
            Nomina nomina = new Nomina();
            nomina.setBrutoAnual(brutoAnual);
                
            System.out.println("----------------------------------------------");  

            float desempleoTrabajador = datosEmpresa.get("Cuota desempleo TRABAJADOR");
            float meiTrabajador = datosEmpresa.get("MEI TRABAJADOR");            
            float seguridadSocialTrabajador = datosEmpresa.get("Cuota obrera general TRABAJADOR");
            float formacionTrabajador = datosEmpresa.get("Cuota formación TRABAJADOR");

            float accidentesTrabajo = datosEmpresa.get("Accidentes trabajo EMPRESARIO");
            float meiEmpresario = datosEmpresa.get("MEI EMPRESARIO");
            float seguridadSocialEmpresario = datosEmpresa.get("Contingencias comunes EMPRESARIO");
            float desempleoEmpresario = datosEmpresa.get("Desempleo EMPRESARIO");
            float formacionEmpresario = datosEmpresa.get("Formacion EMPRESARIO");  
            float fogasa = datosEmpresa.get("Fogasa EMPRESARIO");

            
            if(fechaAltaTrabajador.getYear() == fechaActual.getYear() && fechaAltaTrabajador.compareTo(fechaActual)<0 ){
               

                int numMeses = 12- fechaAltaTrabajador.getMonth();
                
                brutoAnual=(numMeses/14) * (salarioBase + complementos + (importeBrutoTrienios*14));
                nomina.setBrutoAnual(brutoAnual);
                float irpf=calcularIRPF(brutoAnual);
                if(prorrata){
                    
                    nominaMensual = brutoAnual/numMeses;
                    nominaMensual=nominaMensual + nominaMensual/6;
                    
                    //Impuestos
                    
                    nomina.setSeguridadSocialTrabajador(seguridadSocialTrabajador);
                    nomina.setImporteSeguridadSocialTrabajador(nominaMensual*seguridadSocialTrabajador);
                    
                    nomina.setFormacionTrabajador(formacionTrabajador);
                    nomina.setImporteFormacionTrabajador(nominaMensual*formacionTrabajador);
                    
                    nomina.setMeiTrabajador(Double.parseDouble(""+meiTrabajador));
                    nomina.setImporteMeiTrabajador(Double.parseDouble(""+(nominaMensual*meiTrabajador)));
                    
                    nomina.setDesempleoTrabajador(desempleoTrabajador);
                    nomina.setImporteDesempleoTrabajador(nominaMensual*desempleoTrabajador);
                    

                                      
                    
                    float liquidoMensual = nominaMensual - Float.parseFloat(""+(nomina.getImporteSeguridadSocialTrabajador()
                            -nomina.getImporteFormacionTrabajador()
                            -nomina.getImporteMeiTrabajador()
                            -nomina.getImporteDesempleoTrabajador()
  
                    ));
                    
                    
                    
                    
                    //Costes empresario
                    
                    nomina.setSeguridadSocialEmpresario(""+seguridadSocialEmpresario);              //CUIDADO ES UNA STRING
                    nomina.setImporteSeguridadSocialTrabajador(nominaMensual*seguridadSocialEmpresario);
                    
                    nomina.setFormacionEmpresario(formacionEmpresario);
                    nomina.setImporteFormacionEmpresario(nominaMensual*formacionEmpresario);
                    
                    nomina.setMeiEmpresario(Double.parseDouble(""+meiEmpresario));
                    nomina.setImporteMeiEmpresario(Double.parseDouble(""+(nominaMensual*meiEmpresario)));
                    
                    nomina.setDesempleoEmpresario(desempleoEmpresario);
                    nomina.setImporteDesempleoEmpresario(nominaMensual*desempleoEmpresario);
                    
                    nomina.setAccidentesTrabajoEmpresario(accidentesTrabajo);
                    nomina.setImporteAccidentesTrabajoEmpresario(nominaMensual*accidentesTrabajo);
                    
                    nomina.setFogasaempresario(fogasa);
                    nomina.setImporteFogasaempresario(nominaMensual*fogasa);
                            
                    
                    float costeEmpresa = nominaMensual + Float.parseFloat(""+(nomina.getImporteSeguridadSocialEmpresario()
                            +nomina.getImporteFormacionEmpresario()
                            +nomina.getImporteMeiEmpresario()
                            +nomina.getImporteDesempleoEmpresario()
                            +nomina.getImporteAccidentesTrabajoEmpresario()
                            +nomina.getImporteFogasaempresario()
                            
                    ));
                    
                    nomina.setBrutoNomina(nominaMensual);
                    nomina.setLiquidoNomina(liquidoMensual);
                    nomina.setCosteTotalEmpresario(costeEmpresa);
                    nomina.setIdTrabajador(trabajadoresHoja1.get(i).getIdTrabajador());

                    

                }else {  //no es prorrata
                    
                    nominaMensual = brutoAnual/numMeses;

                    float nominaExtra=0;
                    
                    if(fechaActual.getMonth()==11 && fechaActual.getMonth()==5){
                        
                        //diciembre 
                        if(fechaActual.getMonth()==11){                          
                            if(numMeses<7){ //EFMAMJJASOND
                                nominaExtra= (nominaMensual/6)*(numMeses-1);
                            }else{
                                //se le suma toda la paga extra
                                nominaExtra = nominaMensual;
                            }
                        }
                        
                        //junio
                        if(fechaActual.getMonth()==5){

                            int auxMeses = numMeses-7;

                            nominaExtra= (nominaMensual/6)*auxMeses;                   
                        }
                        
                        
                        Nomina nominaEX = new Nomina();
                        
                        nominaEX.setSeguridadSocialTrabajador(seguridadSocialTrabajador);
                        nominaEX.setImporteSeguridadSocialTrabajador(nominaExtra*seguridadSocialTrabajador);

                        nominaEX.setFormacionTrabajador(formacionTrabajador);
                        nominaEX.setImporteFormacionTrabajador(nominaExtra*formacionTrabajador);

                        nominaEX.setMeiTrabajador(Double.parseDouble(""+meiTrabajador));
                        nominaEX.setImporteMeiTrabajador(Double.parseDouble(""+(nominaExtra*meiTrabajador)));

                        nominaEX.setDesempleoTrabajador(desempleoTrabajador);
                        nominaEX.setImporteDesempleoTrabajador(nominaExtra*desempleoTrabajador);

                        nominaEX.setIrpf(irpf);
                        nominaEX.setImporteIrpf(nominaExtra*irpf);


                        float liquidoMensual = nominaExtra - Float.parseFloat(""+(nominaEX.getImporteSeguridadSocialTrabajador()
                                -nominaEX.getImporteFormacionTrabajador()
                                -nominaEX.getImporteMeiTrabajador()
                                -nominaEX.getImporteDesempleoTrabajador()
                                -nominaEX.getImporteIrpf()
                        ));
                    
                        nominaEX.setIdTrabajador(trabajadoresHoja1.get(i).getIdTrabajador());
                        nominaEX.esExtra=true;
                        nominasTrabajadores.add(nominaEX);
                        
                     
                        
                        

                    }
                    

                    //Impuestos
                    
                    nomina.setSeguridadSocialTrabajador(seguridadSocialTrabajador);
                    nomina.setImporteSeguridadSocialTrabajador(nominaMensual*seguridadSocialTrabajador);
                    
                    nomina.setFormacionTrabajador(formacionTrabajador);
                    nomina.setImporteFormacionTrabajador(nominaMensual*formacionTrabajador);
                    
                    nomina.setMeiTrabajador(Double.parseDouble(""+meiTrabajador));
                    nomina.setImporteMeiTrabajador(Double.parseDouble(""+(nominaMensual*meiTrabajador)));
                    
                    nomina.setDesempleoTrabajador(desempleoTrabajador);
                    nomina.setImporteDesempleoTrabajador(nominaMensual*desempleoTrabajador);
                    

                                      
                    
                    float liquidoMensual = nominaMensual - Float.parseFloat(""+(nomina.getImporteSeguridadSocialTrabajador()
                            -nomina.getImporteFormacionTrabajador()
                            -nomina.getImporteMeiTrabajador()
                            -nomina.getImporteDesempleoTrabajador()

                    ));
                    
                    

                    
                    
                    //Costes empresario
                    
                    nomina.setSeguridadSocialEmpresario(""+seguridadSocialEmpresario);              //CUIDADO ES UNA STRING
                    nomina.setImporteSeguridadSocialTrabajador(nominaMensual*seguridadSocialEmpresario);
                    
                    nomina.setFormacionEmpresario(formacionEmpresario);
                    nomina.setImporteFormacionEmpresario(nominaMensual*formacionEmpresario);
                    
                    nomina.setMeiEmpresario(Double.parseDouble(""+meiEmpresario));
                    nomina.setImporteMeiEmpresario(Double.parseDouble(""+(nominaMensual*meiEmpresario)));
                    
                    nomina.setDesempleoEmpresario(desempleoEmpresario);
                    nomina.setImporteDesempleoEmpresario(nominaMensual*desempleoEmpresario);
                    
                    nomina.setAccidentesTrabajoEmpresario(accidentesTrabajo);
                    nomina.setImporteAccidentesTrabajoEmpresario(nominaMensual*accidentesTrabajo);
                    
                    nomina.setFogasaempresario(fogasa);
                    nomina.setImporteFogasaempresario(nominaMensual*fogasa);
                            
                    
                    float costeEmpresa = nominaMensual + Float.parseFloat(""+(nomina.getImporteSeguridadSocialEmpresario()
                            +nomina.getImporteFormacionEmpresario()
                            +nomina.getImporteMeiEmpresario()
                            +nomina.getImporteDesempleoEmpresario()
                            +nomina.getImporteAccidentesTrabajoEmpresario()
                            +nomina.getImporteFogasaempresario()
                            
                    ));
                    
                    nomina.setBrutoNomina(nominaMensual);
                    nomina.setLiquidoNomina(liquidoMensual);
                    nomina.setCosteTotalEmpresario(costeEmpresa);
                    nomina.setIdTrabajador(trabajadoresHoja1.get(i).getIdTrabajador());
                            

  
                }                
               
                
                
            }else if(fechaAltaTrabajador.getYear() < fechaActual.getYear()){
                

                //cuando el año en el que ha entrado el trabajador es anterior al año actual de la nomina a calcular
                
                //Calulamos bruto anual con su salario anual, complemento e importe de trienios
                
                brutoAnual = salarioBase + complementos + (importeBrutoTrienios*14);
                nomina.setBrutoAnual(brutoAnual);
                float irpf=calcularIRPF(brutoAnual);
                //Impuestos y gastos trabajador y empresario

                if(prorrata){
 
                        
                    nominaMensual = brutoAnual / 14;

                    float nominaMensualAux = brutoAnual /12;
                    
                    
                                        
                    //Impuestos
                    
                    nomina.setSeguridadSocialTrabajador(seguridadSocialTrabajador);
                    nomina.setImporteSeguridadSocialTrabajador(nominaMensualAux*seguridadSocialTrabajador);
                    
                    nomina.setFormacionTrabajador(formacionTrabajador);
                    nomina.setImporteFormacionTrabajador(nominaMensualAux*formacionTrabajador);
                    if(fechaActual.getYear()>=123){
                        nomina.setMeiTrabajador(Double.parseDouble(""+meiTrabajador));
                        nomina.setImporteMeiTrabajador(Double.parseDouble(""+(nominaMensualAux*meiTrabajador)));
                    }else{
                        nomina.setMeiTrabajador(0.0);
                        nomina.setImporteMeiTrabajador(0.0); 
                    }
                    nomina.setDesempleoTrabajador(desempleoTrabajador);
                    nomina.setImporteDesempleoTrabajador(nominaMensualAux*desempleoTrabajador);
                    

                                      
                    
                    float liquidoMensual = nominaMensualAux - Float.parseFloat(""+(nomina.getImporteSeguridadSocialTrabajador()
                            +nomina.getImporteFormacionTrabajador()
                            +nomina.getImporteMeiTrabajador()
                            +nomina.getImporteDesempleoTrabajador()

                    ));
                    
                    System.out.println("bruto anual "+ brutoAnual);
                    System.out.println("bruto mensual "+ nominaMensualAux);
                    System.out.println("contingen ss "+ nomina.getImporteSeguridadSocialTrabajador()+" % "+nomina.getSeguridadSocialTrabajador());
                    System.out.println("desempleo "+nomina.getImporteDesempleoTrabajador()+" % "+nomina.getDesempleoTrabajador());
                    System.out.println("formacion "+ nomina.getImporteFormacionTrabajador()+" % "+nomina.getFormacionTrabajador());

                    
                    
                    //Costes empresario
                    
                    nomina.setSeguridadSocialEmpresario(""+seguridadSocialEmpresario);              //CUIDADO ES UNA STRING
                    nomina.setImporteSeguridadSocialTrabajador(nominaMensual*seguridadSocialEmpresario);
                    
                    nomina.setFormacionEmpresario(formacionEmpresario);
                    nomina.setImporteFormacionEmpresario(nominaMensual*formacionEmpresario);
                    
                    nomina.setMeiEmpresario(Double.parseDouble(""+meiEmpresario));
                    nomina.setImporteMeiEmpresario(Double.parseDouble(""+(nominaMensual*meiEmpresario)));
                    
                    nomina.setDesempleoEmpresario(desempleoEmpresario);
                    nomina.setImporteDesempleoEmpresario(nominaMensual*desempleoEmpresario);
                    
                    nomina.setAccidentesTrabajoEmpresario(accidentesTrabajo);
                    nomina.setImporteAccidentesTrabajoEmpresario(nominaMensual*accidentesTrabajo);
                    
                    nomina.setFogasaempresario(fogasa);
                    nomina.setImporteFogasaempresario(nominaMensual*fogasa);
                            
                    
                    float costeEmpresa = nominaMensual + Float.parseFloat(""+(nomina.getImporteSeguridadSocialEmpresario()
                            +nomina.getImporteFormacionEmpresario()
                            +nomina.getImporteMeiEmpresario()
                            +nomina.getImporteDesempleoEmpresario()
                            +nomina.getImporteAccidentesTrabajoEmpresario()
                            +nomina.getImporteFogasaempresario()
                            
                    ));
                    
                    nomina.setBrutoNomina(nominaMensual);
                    nomina.setLiquidoNomina(liquidoMensual);
                    nomina.setCosteTotalEmpresario(costeEmpresa);
                    nomina.setIdTrabajador(trabajadoresHoja1.get(i).getIdTrabajador());
                    


       

                }else{  // no tiene prorrata, cobra vito, ha entrado a la empresa antes de este año
                    
                    float nominaExtra=0;
                    nominaMensual = brutoAnual / 14; //--------------------------------
                    
                    
                    if(fechaActual.getMonth()== 5 && fechaActual.getMonth()==11){
                        //tenemos nomina extra
                        
                        nominaExtra=nominaMensual;
                        
                        Nomina nominaEX = new Nomina();
                        
                        nominaEX.setSeguridadSocialTrabajador(seguridadSocialTrabajador);
                        nominaEX.setImporteSeguridadSocialTrabajador(nominaExtra*seguridadSocialTrabajador);

                        nominaEX.setFormacionTrabajador(formacionTrabajador);
                        nominaEX.setImporteFormacionTrabajador(nominaExtra*formacionTrabajador);

                        nominaEX.setMeiTrabajador(Double.parseDouble(""+meiTrabajador));
                        nominaEX.setImporteMeiTrabajador(Double.parseDouble(""+(nominaExtra*meiTrabajador)));

                        nominaEX.setDesempleoTrabajador(desempleoTrabajador);
                        nominaEX.setImporteDesempleoTrabajador(nominaExtra*desempleoTrabajador);

                        nominaEX.setIrpf(irpf);
                        nominaEX.setImporteIrpf(nominaExtra*irpf);


                        float liquidoMensual = nominaExtra - Float.parseFloat(""+(nominaEX.getImporteSeguridadSocialTrabajador()
                                -nominaEX.getImporteFormacionTrabajador()
                                -nominaEX.getImporteMeiTrabajador()
                                -nominaEX.getImporteDesempleoTrabajador()
                                -nominaEX.getImporteIrpf()
                        ));
                    
                        nominaEX.setIdTrabajador(trabajadoresHoja1.get(i).getIdTrabajador());
                        nominaEX.esExtra=true;
                        nominasTrabajadores.add(nominaEX);
                        

                        
                        //fin de paga extra  
                    }
                    
                    
                    //Impuestos
                    
                    nomina.setSeguridadSocialTrabajador(seguridadSocialTrabajador);
                    nomina.setImporteSeguridadSocialTrabajador((brutoAnual/12)*seguridadSocialTrabajador);

                    
                    nomina.setFormacionTrabajador(formacionTrabajador);
                    nomina.setImporteFormacionTrabajador((brutoAnual/12)*formacionTrabajador);
             
                    
                    if(fechaActual.getYear()>=123){
                        nomina.setMeiTrabajador(Double.parseDouble(""+meiTrabajador));
                        nomina.setImporteMeiTrabajador(Double.parseDouble(""+((brutoAnual/12)*meiTrabajador)));

                    }else{
                        nomina.setMeiTrabajador(0.0);
                        nomina.setImporteMeiTrabajador(0.0);
                    }
                    
                    
                    nomina.setDesempleoTrabajador(desempleoTrabajador);
                    nomina.setImporteDesempleoTrabajador((brutoAnual/12)*desempleoTrabajador);

                                      
                    
                    float liquidoMensual = nominaMensual - Float.parseFloat(""+(nomina.getImporteSeguridadSocialTrabajador()
                            +nomina.getImporteFormacionTrabajador()
                            +nomina.getImporteMeiTrabajador()
                            +nomina.getImporteDesempleoTrabajador()

                    ));
                    

                    //Costes empresario
                    
                    nomina.setSeguridadSocialEmpresario(""+seguridadSocialEmpresario);              //CUIDADO ES UNA STRING
                    nomina.setImporteSeguridadSocialTrabajador(nominaMensual*seguridadSocialEmpresario);
                    
                    nomina.setFormacionEmpresario(formacionEmpresario);
                    nomina.setImporteFormacionEmpresario(nominaMensual*formacionEmpresario);
           
                    if(fechaActual.getYear()>=123){
                        nomina.setMeiEmpresario(Double.parseDouble(""+meiEmpresario));
                        nomina.setImporteMeiEmpresario(Double.parseDouble(""+(nominaMensual*meiEmpresario))); 
                    }else{
                        nomina.setMeiEmpresario(0.0);
                        nomina.setImporteMeiEmpresario(0.0);                         
                    }
                    

                    
                    nomina.setDesempleoEmpresario(desempleoEmpresario);
                    nomina.setImporteDesempleoEmpresario(nominaMensual*desempleoEmpresario);
                    
                    nomina.setAccidentesTrabajoEmpresario(accidentesTrabajo);
                    nomina.setImporteAccidentesTrabajoEmpresario(nominaMensual*accidentesTrabajo);
                    
                    nomina.setFogasaempresario(fogasa);
                    nomina.setImporteFogasaempresario(nominaMensual*fogasa);
                            
                    
                    float costeEmpresa = nominaMensual + Float.parseFloat(""+(nomina.getImporteSeguridadSocialEmpresario()
                            +nomina.getImporteFormacionEmpresario()
                            +nomina.getImporteMeiEmpresario()
                            +nomina.getImporteDesempleoEmpresario()
                            +nomina.getImporteAccidentesTrabajoEmpresario()
                            +nomina.getImporteFogasaempresario()
                            
                    ));

                    nomina.setBrutoNomina(nominaMensual);
                    nomina.setLiquidoNomina(liquidoMensual);
                    nomina.setCosteTotalEmpresario(costeEmpresa);
                    nomina.setIdTrabajador(trabajadoresHoja1.get(i).getIdTrabajador());
                    //fin
                }


                
            }else if(fechaAltaTrabajador.compareTo(fechaActual)>0){

                // no se genera nada
                
            }
            
            
            //CUANDO HAY BAJA LABORAL
            
            if(trabajadoresHoja1.get(i).getBajaLaboral()!=null){
                
                float descuentoBaja=0;
                int diasDeBaja=0;
                
                if(trabajadoresHoja1.get(i).getAltaLaboral()!=null){        //si ha terminado su baja
                    
                    if(trabajadoresHoja1.get(i).getBajaLaboral().compareTo(trabajadoresHoja1.get(i).getAltaLaboral())<0 || ( (trabajadoresHoja1.get(i).getAltaLaboral().getMonth()==fechaActual.getMonth() && trabajadoresHoja1.get(i).getAltaLaboral().getYear()==fechaActual.getYear() ))){
                        // se comprueba que la fecha de baja laboral es posterior a la de alta, esta mal?
                        //nos incumbe la baja laboral y tiene fecha de alta
                        
                       diasDeBaja = calcularDiasEntreFechas(trabajadoresHoja1.get(i).getBajaLaboral(), trabajadoresHoja1.get(i).getAltaLaboral());
                        System.out.println("aaa dias de baja lol "+diasDeBaja);
                       Date auxFecha=(Date)trabajadoresHoja1.get(i).getBajaLaboral().clone();
                       
                       for(int f=0; f<diasDeBaja; f++){
                           
                           if(auxFecha.getMonth()==fechaActual.getMonth() && auxFecha.getYear()==fechaActual.getYear() ){
                               
                                if(f<=3){
                                    descuentoBaja= descuentoBaja+ (nominaMensual/30);
                                }

                                if(f>3 && f<=21){
                                    descuentoBaja= descuentoBaja+ ((nominaMensual/30)*(float)0.40);                                       
                                }

                                if(f>=22){
                                    descuentoBaja= descuentoBaja+ ((nominaMensual/30)*(float)0.25);                                        
                                }

                           }    
                           
                           auxFecha=aumentarDia(auxFecha);
                           
                       }
                       
                       
                    }
                }else{ //no tenemos alta laboral -- su baja no ha terminado aun 
                   
                   
                   if((trabajadoresHoja1.get(i).getBajaLaboral().getMonth()<= fechaActual.getMonth() && trabajadoresHoja1.get(i).getBajaLaboral().getYear()==fechaActual.getYear() )){
                       //la baja se calcula porque es anterior a la fecha actual - nos incumbe
                       
                  
                       if(trabajadoresHoja1.get(i).getBajaLaboral().getMonth()!= fechaActual.getMonth()){
                           //la baja comenzó antes de nuestro mes
                           diasDeBaja = calcularDiasEntreFechas(trabajadoresHoja1.get(i).getBajaLaboral(), fechaActual);

                           for(int h=1; h<diasDeBaja+31; h++){
                               
                               if(h<=diasDeBaja){
                                   
                                   if(h<=3){
                                      descuentoBaja= descuentoBaja+ (nominaMensual/30);
                                   }
                                   
                                   if(h>3 && h<=21){
                                      descuentoBaja= descuentoBaja+ ((nominaMensual/30)*(float)0.40);                                       
                                   }
                                   
                                   if(h>=22){
                                      descuentoBaja= descuentoBaja+ ((nominaMensual/30)*(float)0.25);                                        
                                   }
                               }
                           }

                       }else{
                           //la baja comenzó en nuestro mes
                           int diasBaja= obtenerDiasMes(fechaActual) - trabajadoresHoja1.get(i).getBajaLaboral().getDay();
                           System.out.println("me llamo "+trabajadoresHoja1.get(i).getNombre()+ "dias baja loko "+diasBaja);
                            for(int h=1; h<diasBaja; h++){
                               
                               if(h<=diasBaja){
                                   
                                   if(h<=3){
                                      descuentoBaja= descuentoBaja+ (nominaMensual/30);
                                   }
                                   
                                   if(h>3 && h<=21){
                                      descuentoBaja= descuentoBaja+ ((nominaMensual/30)*(float)0.40);                                       
                                   }
                                   
                                   if(h>=22){
                                      descuentoBaja= descuentoBaja+ ((nominaMensual/30)*(float)0.25);                                        
                                   }
                               }
                           }
                       }
                       
                   }

                }
                
                if((trabajadoresHoja1.get(i).getBajaLaboral().compareTo(fechaActual)<=0 ) ){
                   //si la baja se produce antes de la fecha
                   float irpf=calcularIRPF(brutoAnual);
                   nomina.setDiasBaja(diasDeBaja);
                   nomina.setImporteDescuentoBaja(descuentoBaja);
                   nomina.setIrpf(irpf);
                   nomina.setImporteIrpf((nominaMensual-descuentoBaja)*irpf);
                   nomina.setLiquidoNomina(nomina.getLiquidoNomina()-nomina.getImporteIrpf()-descuentoBaja);
                    System.out.println("cataplau  "+ nomina.getImporteDescuentoBaja());
                   System.out.println("Liquido nomina contando la baja  "+nomina.getLiquidoNomina());
                    System.out.println("");
                }
                
            }else{
                //no tiene baja laboral
                
                if(prorrata){
                    float irpf=calcularIRPF(brutoAnual);
                    nomina.setIrpf(irpf);         
                    nomina.setImporteIrpf((brutoAnual/12)*irpf);
                    nomina.setLiquidoNomina(nomina.getLiquidoNomina()-nomina.getImporteIrpf());                    
                    
                }else{
                    float irpf=calcularIRPF(brutoAnual);
                    nomina.setIrpf(irpf);         
                    nomina.setImporteIrpf((brutoAnual/14)*irpf);
                    nomina.setLiquidoNomina(nomina.getLiquidoNomina()-nomina.getImporteIrpf());
                }

                                                                                
               
 
            }
            
            
            
            
            
            if(fechaAltaTrabajador.compareTo(fechaActual)<0){
                nomina.toString();  
                
                
                
                System.out.println("nombre "+trabajadoresHoja1.get(i).getNombre() + " apellido " +trabajadoresHoja1.get(i).getApellido1());
                System.out.println("liquido nomina "+nomina.getLiquidoNomina());
                System.out.println("irpf "+nomina.getImporteIrpf()+ " % "+ nomina.getImporteIrpf());
                
                nominasTrabajadores.add(nomina);
                
            }
                         
            
            
            


            // El valor de comparacion será:
            // 0 si las fechas son iguales
            // un número negativo si fecha1 es anterior a fecha2
            // un número positivo si fecha1 es posterior a fecha2
            
            
            
            //TENIENDO TODOS ESTOS DATOS SE CALCULA EL BRUTO ANUAL
            // HAY QUE TENER EN CUENTA TODO LO QUE VA A GANAR ESTE AÑO, TENIENDO EN CUENTA TODO
            // SI ES SU PRIMER AÑO, SI CAMBIA JUSTO DE TRIENIO EN ESTE AÑO
            // SI ES UNA NOMINA NORMAL SIN CAMBIO DE TRIENIO, SI TIENE BAJAS
            
            // se puede hacer con un switch y hacer 4 apartados diferentes

        }
        
        generarNominasXML(trabajadoresHoja1, nominasTrabajadores);
        
        
    }
    
    public static int obtenerDiasMes(Date fecha) {
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(fecha);
       return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public static Date aumentarDia(Date fecha) {
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(fecha);
       calendar.add(Calendar.DAY_OF_YEAR, 1);
       return calendar.getTime();
    }
    public  int calcularDiasEntreFechas(Date fechaInicio, Date fechaFin) {
        long diferenciaEnMilisegundos = fechaFin.getTime() - fechaInicio.getTime();
        long milisegundosPorDia = 24 * 60 * 60 * 1000;
        return (int) (diferenciaEnMilisegundos / milisegundosPorDia);
    }
    
    
    //no vale para nada - eliminar 
    
    public boolean trabajadorYaHaEntradoEnLaEmpresa(Date fechaInicio, Date fechaActual) {

        boolean haEntrado = false;

        if (fechaInicio.getYear() < fechaActual.getYear()) {

            haEntrado = true;

        } else if (fechaInicio.getYear() == fechaActual.getYear()) {

            if (fechaInicio.getMonth() <= fechaActual.getMonth()) {

                haEntrado = true;
            }

        }

        return haEntrado;
    }

    public float calcularIRPF(float brutoAnual){
        
        float irpf=0;

        if(brutoAnual<12000){
           irpf=0;
        }else if(brutoAnual>60000){
           irpf=Float.parseFloat("26,22");
        }else{
          int aux = (int)(brutoAnual / 1000) * 1000; 
          aux=aux+1000;

          irpf= retencion.get(Float.parseFloat(aux+""));
        }

        return irpf;

    }    
    
    
    
    
    public float calcularNumeroTrienios(Date fechaInicio, Date fechaActual) {
    
        float numeroTrienios = 0;        
        int aniosDiferencia;
        
        aniosDiferencia = fechaActual.getYear() - fechaInicio.getYear();
        numeroTrienios = aniosDiferencia/3;
        
        return numeroTrienios;
    }
    
    public Trabajador encontrarTrabajadorPorID(int id){
        
        boolean encontrado = false;
        Trabajador trabajador=null;
        int i=0;
        
        while(!encontrado){
            
            if(trabajadoresHoja1.get(i).getIdTrabajador()==id){               
                trabajador=trabajadoresHoja1.get(i);
                encontrado = true; 
            }         
            i++;    
        }
    
        return trabajador;
    }
 
    
    
    
    public void generarNominasXML(List<Trabajador> trabajadores, List<Nomina> nominas){
        
            try{
            // cargamos el archivo XML existente en un objeto Document

            // String rutaXML = "C:/Users/w10/Documents/GitHub/Practica_SI/NominasSI/src/resources/Nominas.xml";
            // portatil String rutaXML = "C:/Users/valen/Documents/git/Practica_SI/NominasSI/src/resources/Nominas.xml";
            String rutaXML = "C:/Users/Torre/Documents/GitHub/Proyecto_NominasSI/src/resources/Nominas.xml";

            File archivoXML = new File(rutaXML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.newDocument();
            Element rootElement = doc.createElement("Nominas");
            doc.appendChild(rootElement);

            // obtenemos la raíz del documento existente
            Element eRaiz = doc.getDocumentElement();
            
                System.out.println("la longiytud es : "+trabajadores.size());

            // creamos un nuevo elemento para cada trabajador
            for (int i = 0; i < nominas.size(); i++) { 
                
                //obtengo id del trabajador de la nomina
                
                int idTrabajador = nominas.get(i).getIdTrabajador();
                               
                Element xmlTrabajador = doc.createElement("Nomina");
          
                Attr atributoID = doc.createAttribute("idNomina");
                atributoID.setValue(""+nominas.get(i).getIdNomina());
                xmlTrabajador.setAttributeNode(atributoID); 
               
                Element idFilaExcel = doc.createElement("idFilaExcel");
                idFilaExcel.appendChild(doc.createTextNode(""+encontrarTrabajadorPorID(idTrabajador).getIdTrabajador()));
                xmlTrabajador.appendChild(idFilaExcel);

                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(encontrarTrabajadorPorID(idTrabajador).getNombre()));
                xmlTrabajador.appendChild(nombre);

                Element nif = doc.createElement("NIF");
                nif.appendChild(doc.createTextNode(encontrarTrabajadorPorID(idTrabajador).getNifnie() ));
                xmlTrabajador.appendChild(nif);

                Element iban = doc.createElement("IBAN");
                iban.appendChild(doc.createTextNode(encontrarTrabajadorPorID(idTrabajador).getIban()));
                xmlTrabajador.appendChild(iban);

                Element categoria = doc.createElement("Categoria");
                categoria.appendChild(doc.createTextNode(encontrarTrabajadorPorID(idTrabajador).getCategoria().getNombreCategoria()));
                xmlTrabajador.appendChild(categoria);

                Element brutoAnual = doc.createElement("BrutoAnual");
                brutoAnual.appendChild(doc.createTextNode(Double.toString(nominas.get(i).getBrutoAnual()))); 
                xmlTrabajador.appendChild(brutoAnual);
                
                Element importeIrpf = doc.createElement("ImporteIrpf");
                importeIrpf.appendChild(doc.createTextNode(Double.toString(nominas.get(i).getImporteIrpf())));
                xmlTrabajador.appendChild(importeIrpf);
                
                Element baseEmpresario = doc.createElement("BaseEmpresario");
                baseEmpresario.appendChild(doc.createTextNode(Double.toString(nominas.get(i).getBaseEmpresario())));
                xmlTrabajador.appendChild(baseEmpresario);
                
                Element brutoNomina = doc.createElement("BrutoNomina");
                brutoNomina.appendChild(doc.createTextNode(Double.toString(nominas.get(i).getBrutoNomina())));
                xmlTrabajador.appendChild(brutoNomina);
                
                Element liquidoNomina = doc.createElement("LiquidoNomina");
                liquidoNomina.appendChild(doc.createTextNode(Double.toString(nominas.get(i).getLiquidoNomina())));
                xmlTrabajador.appendChild(liquidoNomina);
                
                Element costeTotal = doc.createElement("CosteTotalEmpresario");
                costeTotal.appendChild(doc.createTextNode(Double.toString(nominas.get(i).getCosteTotalEmpresario())));
                xmlTrabajador.appendChild(costeTotal);

                // añadimos el elemento de la nomina a la raíz del documento
                eRaiz.appendChild(xmlTrabajador);
            }

            // actualizamos el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // configuramos la propiedad para que se escriba en varias líneas
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivoXML);
            transformer.transform(source, result);

            }catch(Exception e){
                e.printStackTrace();
            }     
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
}
