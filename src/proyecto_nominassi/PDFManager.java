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
import controlador.Nomina;
import controlador.Trabajador;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

/**
 *
 * @author Torre
 */
public class PDFManager {
    
            
    //public final static String imagen = "C:/Users/Torre/Documents/GitHub/Proyecto_NominasSI/src/resources/nominas/img.JPG";
    public final static String imagen  = "C:/Users/valen/Documents/git/Practica_SI/NominasSI/src/resources/nominas/img.JPG";

    
    public void crear() throws FileNotFoundException, MalformedURLException {
        
        //ruta torre String ruta="C:/Users/Torre/Documents/GitHub/Proyecto_NominasSI/src/resources/nominas/archivo.pdf";
        String ruta ="C:/Users/valen/Documents/git/Proyecto_NominasSI/src/resources/nominas/archivo.pdf";

        PdfWriter writer = new PdfWriter(ruta); 
        PdfDocument pdfDoc = new PdfDocument(writer); 
        Document doc = new Document(pdfDoc, PageSize.LETTER);

        
        Paragraph empty = new Paragraph(""); 
        
        //PRIMERA TABLA 
        
        Table tabla1 = new Table(2); 
        tabla1.setWidth(500);
        
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
        
        Table tablaDatosTrabajador = new Table(3); 
       
        
        Cell cellT = new Cell();
        Cell cellC = new Cell();
        Cell cellI = new Cell();
        
        
        cellT.setBorder(com.itextpdf.layout.borders.Border.NO_BORDER);
        cellT.setPadding(50);
        cellT.setWidth(100);
        cellT.setTextAlignment(TextAlignment.CENTER);
        
        cellC.setBorder(com.itextpdf.layout.borders.Border.NO_BORDER);
        cellC.setPadding(50);
        cellC.setWidth(100);
        cellC.setTextAlignment(TextAlignment.CENTER);
        
        cellI.setBorder(com.itextpdf.layout.borders.Border.NO_BORDER);
        cellI.setPadding(50);
        cellI.setWidth(100);
        cellI.setTextAlignment(TextAlignment.CENTER);
        
        
        
        cellT.add(new Paragraph("CONCEPTOS "));
        cellC.add(new Paragraph("CANTIDAD "));
        cellI.add(new Paragraph("Imp. Unitario "));
        
        
        cellT.add(new Paragraph("Salario base: "));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        cellT.add(new Paragraph("Prorrateo: "));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        cellT.add(new Paragraph("Complemento: "));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        
        cellT.add(new Paragraph("Antigüedad: "));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        cellT.add(new Paragraph("Seguridad social: "));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        cellT.add(new Paragraph("Desempleo: " ));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        cellT.add(new Paragraph("Cuota de formación: "));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        cellT.add(new Paragraph("MEI: "));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        cellT.add(new Paragraph("IRPF: "));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        cellT.add(new Paragraph("Descuento baja: "));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        cellT.add(new Paragraph("Total deducciones: "));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        cellT.add(new Paragraph("Total devengos: " ));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        cellT.add(new Paragraph("Liquido a percibir: "));
        cellC.add(new Paragraph("valor "));
        cellI.add(new Paragraph("valor "));
        
        
        tablaDatosTrabajador.addCell(cellT);
        tablaDatosTrabajador.addCell(cellC);
        tablaDatosTrabajador.addCell(cellI);
        //tablaCantidad.addCell(cellC);
        
        
        

        doc.add(tabla1);
        doc.add(tabla2);
        doc.add(tablaDatosTrabajador);

        doc.close();


        
        
        
    }
    
}
