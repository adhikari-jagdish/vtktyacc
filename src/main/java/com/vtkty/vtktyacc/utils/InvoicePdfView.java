package com.vtkty.vtktyacc.utils;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.UnitValue;
import com.vtkty.vtktyacc.service.model.Invoice;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.util.Map;

import static sun.security.pkcs11.wrapper.Constants.NEWLINE;

@Component("invoiceView")
public class InvoicePdfView extends AbstractView {


    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=VTInvoice.pdf");
        Invoice invoice = (Invoice) model.get("invoice");


        //IText API
        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document pdfDocument = new Document(pdf);
        pdfDocument.setFontSize(12);
        //PdfFont bold = PdfFontFactory.createFont(BOLD, true);


        //Get from and to paties name and address
        pdfDocument.add(getAddressTable(invoice));

        pdfDocument.add(ktyLogo());


        pdfDocument.close();
    }


    private Table getAddressTable(Invoice invoice){
        //From Text
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 50),
                new UnitValue(UnitValue.PERCENT, 50)})
                .setWidthPercent(100);

        table.addCell(getPartyAddress(invoice.getName(), invoice.getEmail()));
        return table;
    }

    //Get the Partyname From/To
    private Cell getPartyAddress(String name, String email) {
        Paragraph p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .add(new Text(name)).add(NEWLINE)
                .add(new Text(email));

        Cell cell = new Cell()
                .setBorder(Border.NO_BORDER)
                .add(p);

        return cell;
    }

    private Image ktyLogo() throws MalformedURLException {

        // Creating an ImageData object
        String imFile = "http://kailashtirthayatra.org/images/logo.png";
        ImageData data = ImageDataFactory.create(imFile);

        // Creating an Image object
        Image image = new Image(data);

        return image;
    }
}
