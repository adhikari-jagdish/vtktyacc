package com.vtkty.vtktyacc.utils;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.vtkty.vtktyacc.service.model.Address;
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

    private Paragraph p;
    private Cell cell;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Invoice invoice = (Invoice) model.get("invoice");
        Address address = (Address) model.get("address");
        response.setHeader("Content-Disposition", "attachment; filename=VT.pdf");


        //IText API
        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document pdfDocument = new Document(pdf);
        pdfDocument.setFontSize(12);
        //PdfFont bold = PdfFontFactory.createFont(BOLD, true);

        //Top Cells with Logo and our Address
        pdfDocument.add(getAddressTable(address));
        pdfDocument.setBottomMargin(10);




        pdfDocument.close(); //close document
    }

    private Table getAddressTable(Address address) throws MalformedURLException {
        //From Text
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 50),
                new UnitValue(UnitValue.PERCENT, 50)})
                .setWidthPercent(100);


        table.addCell(ktyLogo());
        table.addCell(getOurAddress(address.getAgencyName(), address.getAddress(), address.getContactNo(), address.getEmail(), address.getUrl()));

        return table;
    }

    //Get Our Address
    private Cell getOurAddress(String name, String address, String contactNo, String email, String url) {
        p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .add(new Text(name)).add(NEWLINE)
                .add(new Text(address)).add(NEWLINE)
                .add(new Text("Office: " + contactNo)).add(NEWLINE)
                .add(new Text("Email: " + email)).add(NEWLINE)
                .add(new Text("URL: " + url));

        cell = new Cell()
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT)
                .add(p);

        return cell;
    }

    //Method for Logo
    private Cell ktyLogo() throws MalformedURLException {
        // Creating an ImageData object
        String imFile = "http://kailashtirthayatra.org/images/logo.png";
        ImageData data = ImageDataFactory.create(imFile);

        // Creating an Image object
        Image image = new Image(data);

        cell = new Cell()
                .setBorder(Border.NO_BORDER)
                .add(image);
        return cell;
    }
}
