package com.vtkty.vtktyacc.utils;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
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

        //Top Cells with Logo and our Address
        pdfDocument.add(getAddressTable(address));
        pdfDocument.setBottomMargin(10);

        p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.LEFT)
                .add(new Text("Invoice"));

        pdfDocument.add(p);

        p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .setTextAlignment(TextAlignment.RIGHT)
                .add(new Text("Invoice Date:")).add(NEWLINE)
                .add(new Text("Invoice Number:"));

        pdfDocument.add(p);


        pdfDocument.setBottomMargin(10);

        //Party Details
        pdfDocument.add(getAgencyDetailsTable(invoice));

        pdfDocument.close(); //close document
    }

    //Our Address Table
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


    //Get Party Details
    private Cell getPartyDetailsLeft(String agencyName, String gstNo, String passengerName,
                                 String contactPerson, String countryOfPassport, String destination) {
        p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .add(new Text("To:")).add(NEWLINE)
                .add(new Text(agencyName)).add(NEWLINE)
                .add(new Text(gstNo)).add(NEWLINE)
                .add(new Text("Passenger Name: " +passengerName)).add(NEWLINE)
                .add(new Text("Contact Person: " +contactPerson)).add(NEWLINE)
                .add(new Text("Country of Passport: " +countryOfPassport)).add(NEWLINE)
                .add(new Text("Destination: " +destination));

        cell = new Cell()
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)
                .add(p);

        return cell;
    }

    //Get Party Details
    private Cell getPartyDetailsRight( String contactNumber, String mealPlan, String destination) {
        p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .add(NEWLINE)
                .add(NEWLINE)
                .add(NEWLINE)
                .add(NEWLINE)
                .add(new Text("Contact No: " +contactNumber)).add(NEWLINE)
                .add(new Text("Meal Plan: " +mealPlan)).add(NEWLINE)
                .add(new Text("Destination: " +destination));

        cell = new Cell()
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT)
                .add(p);

        return cell;
    }

    //Agency Details Table
    private Table getAgencyDetailsTable(Invoice invoice) {
        //From Text
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 50),
                new UnitValue(UnitValue.PERCENT, 50)})
                .setWidthPercent(100);

        table.addCell(getPartyDetailsLeft(invoice.getAgencyName(), invoice.getGstNumber(),
                invoice.getPassengerName(), invoice.getContactPerson(), invoice.getCountryOfPassport(),
                invoice.getDestination()));

        table.addCell(getPartyDetailsRight("6396667854", invoice.getMealPlan(), invoice.getDestination()));

        return table;
    }


}
