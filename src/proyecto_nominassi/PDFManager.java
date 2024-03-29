/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import controlador.Nomina;
import controlador.Trabajador;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

/**
 *
 * @author Torre
 */
public class PDFManager {
    
        public Trabajador encontrarTrabajadorPorID(List<Trabajador> trabajadores, int id){
        
            boolean encontrado = false;
            Trabajador trabajador=null;
            int i=0;

            while(!encontrado){

                if(trabajadores.get(i).getIdTrabajador()==id){               
                    trabajador=trabajadores.get(i);
                    encontrado = true; 
                }         
                i++;    
            }
    
        return trabajador;
    }
        
    private String getMesString(int month){
        switch(month){
          case 1:
              return "Enero";
          case 2:
              return "Febrero";
          case 3:
              return "Marzo";
          case 4:    
              return "Abril";
          case 5:
              return "Mayo";
          case 6:
              return "Junio";
          case 7:
              return "Julio";
          case 8:
              return "Agosto";
          case 9:
              return "Septiembre";
          case 10:
              return "Octubre";
          case 11:
              return "Noviembre";
          case 12:
              return "Diciembre";
        }
        return "";
    }

  

    
    public void crear(List<Trabajador> trabajadores, List<Nomina> nominas) throws FileNotFoundException, MalformedURLException {
        
        
        for(int i=0; i<nominas.size(); i++){
            
            Trabajador trabajador = encontrarTrabajadorPorID(trabajadores, nominas.get(i).getIdTrabajador());


            String ruta="C:/Users/Torre/Documents/GitHub/Proyecto_NominasSI/src/resources/nominas/"+trabajador.getNifnie()+trabajador.getNombre()+trabajador.getApellido1()+trabajador.getApellido2()+getMesString(nominas.get(i).getMes())+nominas.get(i).getAnio()+".pdf";
            
            //String ruta ="C:/Users/valen/Documents/git/Proyecto_NominasSI/src/resources/nominas/archivo.pdf";

            PdfWriter writer = new PdfWriter(ruta); 
            PdfDocument pdfDoc = new PdfDocument(writer); 
            Document doc = new Document(pdfDoc, PageSize.LETTER);


            Paragraph empty = new Paragraph(""); 

            //PRIMERA TABLA 

            Table tabla1 = new Table(2); 
            tabla1.setWidth(500);

            //datos de la empresa

            Paragraph nom = new Paragraph("NOMBRE");
            Paragraph cif = new Paragraph("CIF: ");
            Paragraph dir1 = new Paragraph("Avenida de la facultad - 6");
            Paragraph dir2 = new Paragraph("24001 León");

            Cell cell1 = new Cell();
            cell1.setBorder(new SolidBorder(1));
            cell1.setWidth(160);
            cell1.setTextAlignment(TextAlignment.CENTER);

            cell1.add(nom);
            cell1.add(cif);
            cell1.add(dir1);
            cell1.add(dir2);
            tabla1.addCell(cell1);

            Cell cell2 = new Cell();
            cell2.setBorder(null);
            cell2.setFontSize(10f);
            cell2.setPadding(10);
            cell2.setTextAlignment(TextAlignment.RIGHT);
            cell2.add(new Paragraph("IBAN: "));
            cell2.add(new Paragraph("Bruto anual: "));
            cell2.add(new Paragraph("Categoría: "));
            cell2.add(new Paragraph("Fecha de alta: "));
            tabla1.addCell(cell2);

            //SEGUNDA TABLA

            Table tabla2 = new Table(2);
            tabla2.setWidth(500);
            //Image img = new Image(ImageDataFactory.create(imagen));
            //img.setBorder(null);
            //img.setPadding(10);

            Cell cell3 = new Cell();
            //cell3.add(img);
            cell3.setBorder(null);

            cell3.setPaddingLeft(23);
            cell3.setPaddingTop(20);

            cell3.setWidth(250);
            tabla2.addCell(cell3);

            Cell cellD = new Cell();
            cellD.setBorder(null);  //no poner borde 
            cellD.setFontSize(10f);

            cellD.setTextAlignment(TextAlignment.RIGHT);
            cellD.setPadding(10);
            cellD.add(nom);
            cellD.add(new Paragraph("nombre completo  "));
            cellD.add(new Paragraph("sni  "));
            cellD.add(new Paragraph("avenida  "));
            cellD.add(new Paragraph("codigo postal  "));
            cellD.setBorder(new SolidBorder(1));

            cellD.setWidth(250);
            tabla2.addCell(cellD);



            //Tabla de datos del trabajador 

            Table tablaDatosTrabajador = new Table(5); 


            Cell cellT = new Cell();
            Cell cellC = new Cell();
            Cell cellI = new Cell();
            Cell cellDe = new Cell();
            Cell cellDeduccion = new Cell();


            cellT.setBorder(com.itextpdf.layout.borders.Border.NO_BORDER);
            cellT.setPadding(10);
            cellT.setWidth(250);
            cellT.setTextAlignment(TextAlignment.CENTER);
            cellT.setFontSize(8f);

            cellC.setBorder(com.itextpdf.layout.borders.Border.NO_BORDER);
            cellC.setPadding(10);
            cellC.setWidth(250);
            cellC.setTextAlignment(TextAlignment.CENTER);
            cellC.setFontSize(8f);

            cellI.setBorder(com.itextpdf.layout.borders.Border.NO_BORDER);
            cellI.setPadding(10);
            cellI.setWidth(250);
            cellI.setTextAlignment(TextAlignment.CENTER);
            cellI.setFontSize(8f);

            cellDe.setBorder(com.itextpdf.layout.borders.Border.NO_BORDER);
            cellDe.setPadding(10);
            cellDe.setWidth(250);
            cellDe.setTextAlignment(TextAlignment.CENTER);
            cellDe.setFontSize(8f);

            cellDeduccion.setBorder(com.itextpdf.layout.borders.Border.NO_BORDER);
            cellDeduccion.setPadding(10);
            cellDeduccion.setWidth(250);
            cellDeduccion.setTextAlignment(TextAlignment.CENTER);
            cellDeduccion.setFontSize(8f);


            cellT.add(new Paragraph("CONCEPTOS ").setFontSize(8f));
            cellC.add(new Paragraph("CANTIDAD "));
            cellI.add(new Paragraph("IMP.UNITARIO "));
            cellDe.add(new Paragraph("DEVENGO "));
            cellDeduccion.add(new Paragraph("DEDUCCION "));


            cellT.add(new Paragraph("Salario base: "));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));

            cellT.add(new Paragraph("Prorrateo: "));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));


            cellT.add(new Paragraph("Complemento: "));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));



            cellT.add(new Paragraph("Antigüedad: "));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));


            cellT.add(new Paragraph("Seguridad social: "));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));


            cellT.add(new Paragraph("Desempleo: " ));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));


            cellT.add(new Paragraph("Cuota de formación: "));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));


            cellT.add(new Paragraph("MEI: "));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));


            cellT.add(new Paragraph("IRPF: "));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));


            cellT.add(new Paragraph("Descuento baja: "));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));


            cellT.add(new Paragraph("Total deducciones: "));
            cellC.add(new Paragraph("valor ").setFontSize(8f));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));


            cellT.add(new Paragraph("Total devengos: " ));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));
            cellDeduccion.add(new Paragraph("valor "));


            cellT.add(new Paragraph("Liquido a percibir: "));
            cellC.add(new Paragraph("valor "));
            cellI.add(new Paragraph("valor "));
            cellDe.add(new Paragraph("valor "));  
            cellDeduccion.add(new Paragraph("valor "));

            //RESUMEN

            Table tablaResumen = new Table(1); 

            Cell cellTotalDeducciones = new Cell();

            cellTotalDeducciones.setBorder(new SolidBorder(1));
            cellTotalDeducciones.setPadding(10);
            cellTotalDeducciones.setWidth(250);
            cellTotalDeducciones.setTextAlignment(TextAlignment.LEFT);


            cellTotalDeducciones.add(new Paragraph("Total deducciones: ").setFontSize(8f));
            cellTotalDeducciones.add(new Paragraph("Total devengos: ").setFontSize(8f));
            cellTotalDeducciones.add(new Paragraph("Liquido a percibir: ").setFontSize(8f));




            tablaDatosTrabajador.addCell(cellT);
            tablaDatosTrabajador.addCell(cellC);
            tablaDatosTrabajador.addCell(cellI);
            tablaDatosTrabajador.addCell(cellDe);
            tablaDatosTrabajador.addCell(cellDeduccion);
            //tablaResumen.addCell(cellDeduccion);
            //tablaCantidad.addCell(cellC);


            //TABLA DE COSTES DE EMRESARIO 


            Table tablaCostesEmpresario = new Table(2); 


            Cell cellConceptos = new Cell();
            Cell cellValores = new Cell();

            cellConceptos.setBorder(null);      
            cellConceptos.setFontSize(8f);
            cellConceptos.setPadding(10);

            cellConceptos.add(new Paragraph("Calculo empresario: BASE " ));
            cellConceptos.add(new Paragraph("Contingencias comunes empreasario " ));
            cellConceptos.add(new Paragraph("MEI empresario: " ));
            cellConceptos.add(new Paragraph("Desempleo: " ));
            cellConceptos.add(new Paragraph("Formación: " ));
            cellConceptos.add(new Paragraph("Accidentes de trabajo: "));
            cellConceptos.add(new Paragraph("FOGASA: " ));
            cellConceptos.add(new Paragraph("Total empresario: "  ));
            cellConceptos.add(new Paragraph("COSTE TOTAL TRABAJADOR: " ));


            cellValores.setBorder(null);
            cellValores.setWidth(330);
            cellValores.setTextAlignment(TextAlignment.RIGHT);
            cellValores.setPadding(10);
            cellValores.setFontSize(8f);

            cellValores.add(new Paragraph("valor" ));
            cellValores.add(new Paragraph("valor" ));
            cellValores.add(new Paragraph("valor" ));
            cellValores.add(new Paragraph("valor" ));
            cellValores.add(new Paragraph("valor" ));
            cellValores.add(new Paragraph("valor" ));
            cellValores.add(new Paragraph("valor" ));
            cellValores.add(new Paragraph("valor" ));
            cellValores.add(new Paragraph("valor" ));






            tablaCostesEmpresario.addCell(cellConceptos);
            tablaCostesEmpresario.addCell(cellValores);





            doc.add(tabla1);
            doc.add(tabla2);
            doc.add(tablaDatosTrabajador);
            doc.add(tablaResumen);
            doc.add(tablaCostesEmpresario);

            doc.close();
            
            
            
        }
 


        
        
        
    }
    
}
