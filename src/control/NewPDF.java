package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class NewPDF {

	//public static void main(String[] args) throws MalformedURLException, DocumentException, IOException {
	//	createPDF("C:\\Users\\AUX ADMIN IKDATA\\Desktop\\sebashoy", "MYNUMSERIE", "MSFJDKL", "TGARANTIA", "madicional", "AnosGrt", "User", "Direccione", "citi", "OCITI", "CONTACTEME", "MAIL@MAIL", "5435434543", "DEPOT");
	//}
    static void createPDF(String carpeta, String fechaGarantia, String noserie, String mScanner, String tGarantia, String madicional,
                          String anosGrt, String usuario, String direccion, String ciudad, String oCiudad, String contacto,
                          String email, String telefono, String departamento)
            throws DocumentException, MalformedURLException, IOException {

        File file = new File(carpeta + File.separator + noserie + "-Registro de Garantia IKNO.pdf");
        file.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(file);

        //Getting Servlet Context
       ServletContext context = (ServletContext) FacesContext
               .getCurrentInstance().getExternalContext().getContext();

       Image imagen = Image.getInstance(context.getResource("/resources/images/iknoimage.png"));
       //Image imagen = Image.getInstance("C:\\Users\\dev1\\eclipse-workspace\\IknoLine\\WebContent\\resources\\images\\iknoimage.png");
       // Image imagen = Image.getInstance("C:\\Users\\AUX ADMIN IKDATA\\Desktop\\iknoimage.png");
        Document document = new Document();
        document.setMargins(90, 90, 90, 90);
        imagen.scaleAbsolute(document.getPageSize().getWidth(), imagen.getHeight() - 35);
        imagen.setAlignment(Image.TEXTWRAP);
        imagen.setAbsolutePosition(0, document.getPageSize().getHeight() - imagen.getHeight() + 35);
        PdfWriter.getInstance(document, fos);
        document.open();
        Paragraph ltters;
        // Chunk pagina1 = new Chunk();

        ltters = new Paragraph();
        ltters.add(imagen);
        document.add(ltters);

        Font fuente = new Font();
        fuente.setSize(24);
        fuente.setStyle(Font.BOLD);
        ltters = new Paragraph("\nCertificado de Garantia \n Scanner Kodak", fuente);
        ltters.setAlignment(Element.ALIGN_CENTER);
        document.add(ltters);

//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
//        String formatted = format1.format(c.getTime());
        Font fuente2 = new Font();
        fuente2.setSize(11);
        Font fuente6 = new Font();
        fuente6.setSize(11);
        fuente6.setStyle(Font.BOLD);
        ltters = new Paragraph("\n\nFelicitaciones, a partir del\n\n", fuente2);
        document.add(ltters);
        ltters = new Paragraph(fechaGarantia, fuente6);
        document.add(ltters);
        ltters = new Paragraph(
                "\n" + "El siguiente equipo se encuentra cubierto por la garantía Kodak bajo la siguiente "
                        + "información:\n\n\n",
                fuente2);
        document.add(ltters);

        Font fuente5 = new Font();
        fuente5.setSize(18);
        fuente5.setStyle(Font.BOLD);

        ltters = new Paragraph("Datos del Equipo\n\n", fuente5);
        document.add(ltters);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.getDefaultCell().setFixedHeight(30);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setPaddingLeft(20);
        Font fuente3 = new Font();
        fuente3.setSize(10);
        fuente3.setStyle(Font.BOLD);
        Font fuente4 = new Font();
        fuente4.setSize(10);
        table.addCell(new Paragraph("Numero de Serie del Equipo", fuente3));
        table.addCell(new Paragraph(noserie, fuente4));
        table.addCell(new Paragraph("Modelo de Scanner", fuente3));
        table.addCell(new Paragraph(mScanner, fuente4));
        table.addCell(new Paragraph("Tipo de Garantia", fuente3));
        table.addCell(new Paragraph(tGarantia, fuente4));
        table.addCell(new Paragraph("Matenimientos Adicionales", fuente3));
        table.addCell(new Paragraph(madicional, fuente4));
        table.addCell(new Paragraph("Tiempo de Garantia (años)", fuente3));
        table.addCell(new Paragraph(anosGrt, fuente4));
        document.add(table);

        ltters = new Paragraph("\nDatos de Contacto\n\n", fuente5);
        document.add(ltters);

        table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.getDefaultCell().setFixedHeight(30);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setPaddingLeft(20);
        table.addCell(new Paragraph("Nombre de Usuario", fuente3));
        table.addCell(new Paragraph(usuario, fuente4));
        table.addCell(new Paragraph("Direccion", fuente3));
        table.addCell(new Paragraph(direccion, fuente4));
        table.addCell(new Paragraph("Ciudad", fuente3));
        table.addCell(new Paragraph(ciudad, fuente4));

        document.add(table);

        document.newPage();
        document.add(imagen);

        table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.getDefaultCell().setFixedHeight(30);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setPaddingLeft(20);
        table.addCell(new Paragraph("Otra Ciudad", fuente3));
        table.addCell(new Paragraph(oCiudad, fuente4));
        table.addCell(new Paragraph("Contacto", fuente3));
        table.addCell(new Paragraph(contacto, fuente4));
        table.addCell(new Paragraph("Correo Electronico", fuente3));
        table.addCell(new Paragraph(email, fuente4));
        table.addCell(new Paragraph("Telefono", fuente3));
        table.addCell(new Paragraph(telefono, fuente4));
        table.addCell(new Paragraph("Departamento", fuente3));
        table.addCell(new Paragraph(departamento, fuente4));
        document.add(table);

        ltters = new Paragraph("\n\nEn caso de requerir soporte técnico por favor hacerlo "
                + "por alguno de los siguientes canales de comunicación:\n\n", fuente2);
        document.add(ltters);

        Font fuente7 = new Font();
        fuente7.setColor(BaseColor.BLUE);
        fuente7.setStyle(Font.UNDERLINE);
        Anchor enlace = null;
        enlace = new Anchor("servicio@ikno.com.co", fuente7);
        enlace.setReference("mailto:servicio@ikno.com.co");
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Phrase("Enviar un correo a: ", fuente2));
        paragraph.add(enlace);
        paragraph.add(new Phrase(" con los datos del equipo, Modelo del scanner, Serial y los datos de contacto.\n\n",
                fuente2));
        paragraph.setFont(fuente2);
        List list = new List(false, 15);
        // list.setListSymbol("●");
        list.add(new ListItem(paragraph));
        paragraph = new Paragraph(
                "Comunicarse al teléfono fijo en Bogotá (+571) 702-6681, en el horario de Lunes a Viernes de 8AM a 5PM.\n\n",
                fuente2);
        list.add(new ListItem(paragraph));
        enlace = new Anchor("http://www.ikno.com.co/index.php/requerimiento-servicio-tecnico/", fuente7);
        enlace.setReference("http://www.ikno.com.co/index.php/requerimiento-servicio-tecnico/");
        paragraph = new Paragraph();
        paragraph.add(new Phrase("En la pagina de IKNO:\n", fuente2));
        paragraph.add(enlace);
        paragraph.setFont(fuente2);
        list.add(new ListItem(paragraph));
        document.add(list);

        ltters = new Paragraph(new Phrase("\n\nCondiciones en las garantías:\n\n", fuente2));
        ltters.add(new Phrase("Las garantías aquí contempladas son para el hardware exclusivamente "
        		+ "y en ningún caso se dará soporte sobre software tales como reinstalación de drivers,"
        		+ " configuraciones de software, entre otros. Estos soportes son responsabilidad del"
        		+ " canal vendedor. Si requiere soporte para Software, IKNO podrá dar el servicio "
        		+ "a las tarifas preestablecidas con el fabricante y mayorista\n\n",fuente2));
        ltters.add(new Phrase("Garantía Carry In (Depot) ", fuente3));
        ltters.add(new Phrase(": El usuario del equipo entrega equipo en las oficinas de IKNO"
                + " y después es responsable de recuperarlos una vez reparado. Los costos de "
                + "transporte y embalaje son de responsabilidad del usuario del equipo.\n\n", fuente2));
        ltters.add(new Phrase("On Site Ciudad Principal: ", fuente3));
        ltters.add(new Phrase(" El representante de Servicio IKNO debe "
                + "llegar a la oficina del cliente antes del fin del día siguiente en que se "
                + "registro la solicitud de atención.\n"
                + "Ciudades consideradas Sede: Bogotá, Medellín, Barranquilla, Cali.\n", fuente2));
        document.add(ltters);
        document.newPage();
        document.add(imagen);
        ltters = new Paragraph();
        ltters.add(new Phrase("On Site Ciudad Secundiaria: ", fuente3));
        ltters.add(new Phrase("El representante de Servicio IKNO "
                + "debe llegar a la oficina del cliente antes Veinticuatro (24) horas "
                + "hábiles posteriores al registro de la solicitud de atención.\n\n", fuente2));
        document.add(ltters);

        ltters = new Paragraph("Dependiendo de la Zona Geográfica donde se ubica el equipo se "
                + "hará un cargo básico por desplazamiento, que deberá ser cubierto" + "por el cliente. "
                + "Las garantias de fabrica no incluyen mantenimientos preventivos.\n\n", fuente2);
        document.add(ltters);

        ltters = new Paragraph("Kodak, recomienda para cada modelo, un numero limitado de paginas por dia, no atender "
                + "esta recomendacion conlleva a un desgaste prematuro del equipo. En caso de superar el numero de paginas "
                + "a los valores recomendados por el fabricante para dicho modelo y proporcional al tiempo de uso, "
                + "la garantia no se hara efectiva." + "\n\n\n",fuente2);
                
                ltters.add(new Phrase("La garantia no sera cubierta por IKNO si no se cumple alguna de las siguientes condiciones:\n\n",
                fuente2));
        document.add(ltters);

        list = new List(false, 15);
        // list.setListSymbol("●");
        list.setListSymbol("\u2022");

        list.add(new ListItem("La garantía se activó, en un tiempo superior a cinco (5) días hábiles de la venta.",
                fuente2));
        list.add(new ListItem("El equipo ha superado la producción recomendada por el fabricante para el tiempo "
                + "máximo del año de garantía.", fuente2));
        list.add(new ListItem("Se hayan utilizado partes o consumibles no originales.", fuente2));
        list.add(new ListItem(
                "Reparaciones efectuadas o intentos de reparación por terceros no pertenecientes a IKNO.",
                fuente2));
        list.add(new ListItem("Accidentes, incendios, líquidos, productos químicos, otras sustancias, "
                + "inundaciones, vibraciones, calor excesivo, ventilación inadecuada, sobrecargas "
                + "eléctricas, tensión o suministro de voltaje excesivo o incorrecto, radiación, "
                + "descargas electrostáticas incluyendo rayos, otras fuerzas externas e impactos, o "
                + "negligencia por parte del usuario final.", fuente2));
        list.add(new ListItem("Si el nombre del modelo o el número de serie del producto ha sido alterado,\r\n"
                + "borrado, ha desaparecido o resulta ilegible.", fuente2));
        document.add(list);

        document.close();

    }


}
