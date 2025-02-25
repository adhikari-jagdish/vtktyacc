package com.vtkty.vtktyacc.utils;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.vtkty.vtktyacc.service.model.Address;
import com.vtkty.vtktyacc.service.model.Invoice;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.Map;

import static sun.security.pkcs11.wrapper.Constants.NEWLINE;

@Component("invoiceView")
public class InvoicePdfView extends AbstractView {

    private Paragraph p;
    private Cell cell;
    private Invoice invoice;
    private Address address;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        invoice = (Invoice) model.get("invoice");
        address = (Address) model.get("address");
        final String DEST = "attachment; filename=" + "Invoice " + invoice.getAgencyName() + "/" + invoice.getInvoiceNo() + ".pdf";
        response.setHeader("Content-Disposition", DEST);


        //IText API
        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document pdfDocument = new Document(pdf);
        pdfDocument.setFontSize(11);

        //Top Cells with Logo and our Address
        pdfDocument.add(getTopTable(invoice));
        pdfDocument.setBottomMargin(10);

        //Party Details
        pdfDocument.add(getAgencyDetailsTable(invoice));

        pdfDocument.add(getTourDetailsTable(invoice));

        pdfDocument.add(getHeaderTable());
        pdfDocument.add(getBodyTable(invoice));

        pdfDocument.add(getAmtandDisountTable(invoice));

        p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .setTextAlignment(TextAlignment.LEFT)
                .add(new Text("Amount in words : ").setBold()).add(new Text(Utils.convertToIndianCurrency(String.valueOf(invoice.getGrandTotal()))));
        pdfDocument.add(p);

        pdfDocument.add(getBankDetailsTable());

        //pdfDocument.add(getEndSignTable());
        final SolidLine lineDrawer = new SolidLine(1f);
        lineDrawer.setColor(Color.GRAY);
        pdfDocument.add(new LineSeparator(lineDrawer));

        p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .setTextAlignment(TextAlignment.CENTER)
                .add(new Text(Constants.NOTE));
        pdfDocument.add(p);

        pdfDocument.add(new LineSeparator(lineDrawer));

        pdfDocument.close(); //close document
    }

    //Our Address Table
    private Table getTopTable(Invoice invoice) throws MalformedURLException {
        //From Text
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 50),
                new UnitValue(UnitValue.PERCENT, 50)})
                .setMarginBottom(30)
                .setWidthPercent(100);


        table.addCell(ktyLogo("https://i.ibb.co/6r8ygjQ/Screenshot-from-2019-07-28-18-18-09.png"));
        table.addCell(getInvoiceDetails(invoice));

        return table;
    }

    //Get Our Address
    private Cell getInvoiceDetails(Invoice invoice) {
        p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .setTextAlignment(TextAlignment.RIGHT)
                .add(new Text("Invoice").setBold().setFontSize(22)).add(NEWLINE)
                .add(new Text("Invoice Number: " + invoice.getInvoiceNo() + "/" + address.getAgencyCode() + "/" + LocalDate.now().getYear())).add(NEWLINE)
                .add(new Text("Invoice Date: " + LocalDate.now())).add(NEWLINE)
                .add(new Text("GSTIN No.: " + Constants.GST_NUMBER));

        cell = new Cell()
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT)
                .add(p);

        return cell;
    }

    //Method for Logo
    private Cell ktyLogo(String imFile) throws MalformedURLException {
        // Creating an ImageData object
        ImageData data = ImageDataFactory.create(imFile);

        // Creating an Image object
        Image image = new Image(data);

        cell = new Cell()
                .setBorder(Border.NO_BORDER)
                .add(image);
        return cell;
    }


    //Get Party Details //
    private Cell getPartyDetailsLeftWithAgency(String agencyName, String gstNo, String passengerName,
                                               String contactPerson, String contactNumber) {
        p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .add(new Text("To:")).add(NEWLINE)
                .add(new Text(agencyName).setBold()).add(NEWLINE)
                .add(new Text(gstNo)).add(NEWLINE)
                .add(new Text("Passenger Name: ")).add(new Text(passengerName))
                .add(NEWLINE)
                .add(new Text("Contact Person: ")).add(new Text(contactPerson))
                .add(NEWLINE)
                .add(new Text("Contact No: ")).add(new Text(contactNumber))
                .setMarginBottom(20);

        cell = new Cell()
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)
                .add(p);

        return cell;
    }


    private Cell getPartyDetailsLeftWithoutAgency(String passengerName, String contactNumber) {
        p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .add(new Text("To:")).add(NEWLINE)
                .add(new Text("Passenger Name: ")).add(new Text(passengerName))
                .add(NEWLINE)
                .add(new Text("Contact No: ")).add(new Text(contactNumber))
                .setMarginBottom(20);

        cell = new Cell()
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.LEFT)
                .add(p);

        return cell;
    }

    //Agency Details Table
    private Table getAgencyDetailsTable(Invoice invoice) {
        //From Text
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 100)})
                .setWidthPercent(100);

        if (!invoice.getAgencyName().equalsIgnoreCase(""))
            table.addCell(getPartyDetailsLeftWithAgency(invoice.getAgencyName(), invoice.getGstNumber(),
                    invoice.getPassengerName(), invoice.getContactPerson(), invoice.getContactNumber()));
        else
            table.addCell(getPartyDetailsLeftWithoutAgency(invoice.getPassengerName(), invoice.getContactNumber()));


        return table;
    }

    //Destination/Nationality/Flight Table (Upper of SN/Particulars)
    private Table getTourDetailsTable(Invoice invoice) {
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 33),
                new UnitValue(UnitValue.PERCENT, 33),
                new UnitValue(UnitValue.PERCENT, 33)
        })
                .setMarginBottom(15)
                .setWidthPercent(100);

        table.addCell(getParticularsDataCell("Destination : " + invoice.getDestination()));
        table.addCell(getParticularsDataCell("Nationality : " + invoice.getNationality()));
        table.addCell(getParticularsDataCell("Flight Details : " + invoice.getFlightDetails()));
        table.startNewRow();
        table.addCell(getParticularsDataCell("Check In Date : " + invoice.getCheckInDate()));
        table.addCell(getParticularsDataCell("Check Out Date : " + invoice.getCheckOutDate()));
        table.addCell(getParticularsDataCell("Meals Plan : " + invoice.getMealPlan()));
        table.startNewRow();
        table.addCell(getParticularsDataCell("No of Adult : " + invoice.getNoOfAdult()));
        table.addCell(getParticularsDataCell("No of Infant : " + invoice.getNoOfInfant()));
        table.startNewRow();
        table.addCell(getParticularsDataCell("No of Rooms (SNG) : " + invoice.getNoOfRoomsSng()));
        table.addCell(getParticularsDataCell("No of Rooms (DBL) : " + invoice.getNoOfRoomsDbl()));
        table.addCell(getParticularsDataCell("No of Rooms (TRP) : " + invoice.getNoOfRoomsTrp()));
        table.startNewRow();
        table.addCell(getParticularsDataCell("Hotel Category : " + invoice.getHotelCategory()));
        table.addCell(getParticularsDataCell("No of Rooms (QUAD) : " + invoice.getNoofRoomsQuad()));
        return table;

    }

    //Particulars Amount and Rate Table
    private Table getHeaderTable() {

        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 5),
                new UnitValue(UnitValue.PERCENT, 50),
                new UnitValue(UnitValue.PERCENT, 15),
                new UnitValue(UnitValue.PERCENT, 15),
                new UnitValue(UnitValue.PERCENT, 15)})
                .setWidthPercent(100);
        table.addCell("SN");
        table.addCell("Particulars").setTextAlignment(TextAlignment.CENTER).setBackgroundColor(Color.ORANGE);
        table.addCell("Rate Adult");
        table.addCell("Rate Child");
        table.addCell("Amount");

        return table;
    }

    //Body Table
    private Table getBodyTable(Invoice invoice) {
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 5),
                new UnitValue(UnitValue.PERCENT, 50),
                new UnitValue(UnitValue.PERCENT, 15),
                new UnitValue(UnitValue.PERCENT, 15),
                new UnitValue(UnitValue.PERCENT, 15)})
                .setWidthPercent(100);

        table.addCell(getSN());
        table.addCell(getParticularsCell(invoice));
        table.addCell(getRate(invoice));
        table.addCell("");
        table.addCell(getAmount(invoice));

        return table;

    }

    //Amount and Discount Table
    private Table getAmtandDisountTable(Invoice invoice) {
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 85),
                new UnitValue(UnitValue.PERCENT, 15)})
                .setWidthPercent(100);

        table.addCell(getTotalDiscountTexts());
        table.addCell(getGrandTotal(invoice));

        return table;

    }


    private Cell getSN() {
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 60)})
                .setWidthPercent(100);

        table.addCell(getParticularsDataCell("1"));
        table.startNewRow();
        table.addCell(getParticularsDataCell("2"));
        table.startNewRow();
        table.addCell(getParticularsDataCell("3"));

        cell = new Cell()
                .setTextAlignment(TextAlignment.CENTER)
                .add(table);


        return cell;
    }

    //Get rate
    private Cell getRate(Invoice invoice) {
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 100)})
                .setWidthPercent(100);

        table.addCell(getParticularsDataCell(String.valueOf(invoice.getAmount())));

        cell = new Cell()
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(Border.NO_BORDER)
                .add(p);

        return cell;
    }

    //Get amount
    private Cell getTotalDiscountTexts() {

        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 100)})
                .setWidthPercent(100);

        table.addCell(getParticularsDataCell(Constants.Grand_Total));
        table.setMarginBottom(10);
        table.addCell(getParticularsDataCell(Constants.Less_Discounts));
        table.setMarginBottom(10);
        table.addCell(getParticularsDataCell(Constants.Initial_Booking_Amount_Received));
        table.setMarginBottom(10);
        table.addCell(getParticularsDataCell(Constants.Balance_Payment));

        cell = new Cell()
                .setTextAlignment(TextAlignment.RIGHT)
                .add(table);

        return cell;
    }

    //Get amount
    private Cell getAmount(Invoice invoice) {

        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 100)})
                .setWidthPercent(100);

        table.addCell(getParticularsDataCell(String.valueOf(invoice.getAmount())));
        table.setMarginBottom(10);
        table.addCell(getParticularsDataCell(String.valueOf(invoice.getGst())));
        table.setMarginBottom(10);
        table.addCell(getParticularsDataCell(String.valueOf(invoice.getNepalRemitCharges())));

        cell = new Cell()
                .setTextAlignment(TextAlignment.CENTER)
                .add(table);

        return cell;
    }

    //Get the grand Total/less discounts/initial amount/balance payment
    private Cell getGrandTotal(Invoice invoice) {

        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 100)})
                .setWidthPercent(100);

        table.addCell(getParticularsDataCell(String.valueOf(invoice.getGrandTotal())));
        table.setMarginBottom(10);
        table.addCell(getParticularsDataCell(String.valueOf(invoice.getGst())));
        table.setMarginBottom(10);
        table.addCell(getParticularsDataCell(String.valueOf(invoice.getNepalRemitCharges())));
        table.setMarginBottom(10);
        table.addCell(getParticularsDataCell(String.valueOf(invoice.getNepalRemitCharges())));

        cell = new Cell()
                .setTextAlignment(TextAlignment.CENTER)
                .add(table);

        return cell;
    }

    //Get Particulars table cells
    private Cell getParticularsCell(Invoice invoice) {

        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 100)})
                .setWidthPercent(100);

        table.addCell(getParticularsDataCell("05/06 Days Nepal Luxury Tour"));
        table.startNewRow();
        table.addCell(getParticularsDataCell("GST 5%"));
        table.startNewRow();
        table.addCell(getParticularsDataCell(Constants.INTERNATIONAL_BANK_TXF));
        table.startNewRow();
        table.addCell(getParticularsDataCell(Constants.PACKAGE_INCLUSIONS).setBold());
        table.addCell(getParticularsDataCell(invoice.getPackageInclusions()).setFontSize(9));


        cell = new Cell()
                .add(table);

        return cell;
    }

    //Method for Individual cell data for Particulars sections starts
    private Cell getParticularsDataCell(String data) {

        p = new Paragraph()
                .setMultipliedLeading(1.0f)
                .add(new Text(String.valueOf(data)));

        cell = new Cell()
                .setBorder(Border.NO_BORDER);

        cell.add(p);

        return cell;
    }

    //Bank Details Table
    private Table getBankDetailsTable() {

        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 60),
                new UnitValue(UnitValue.PERCENT, 20),
                new UnitValue(UnitValue.PERCENT, 20)})
                .setMarginTop(30)
                .setMarginBottom(30)
                .setWidthPercent(100);

        table.addCell(getBankBodyCell());
        table.addCell(getBlankCell());
        table.addCell(getBlankCell());

        return table;
    }


    //Get Bank Details Body table cells
    private Cell getBankBodyCell() {

        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 50),
                new UnitValue(UnitValue.PERCENT, 50)})
                .setWidthPercent(100);

        table.addCell(getParticularsDataCell("Name of Bank: "));
        table.addCell(getParticularsDataCell("Axis Bank Ltd"));
        table.startNewRow();
        table.addCell(getParticularsDataCell("Bank Account No: "));
        table.addCell(getParticularsDataCell("918020111107337"));
        table.startNewRow();
        table.addCell(getParticularsDataCell("Account Type: "));
        table.addCell(getParticularsDataCell("Current Account"));
        table.startNewRow();
        table.addCell(getParticularsDataCell("Bank Branch: "));
        table.addCell(getParticularsDataCell("Old Rajinder Nagar, West Delhi, New Delhi 110060"));
        table.startNewRow();
        table.addCell(getParticularsDataCell("IFSC Code: "));
        table.addCell(getParticularsDataCell("UTIB0000224"));
        table.startNewRow();
        table.addCell(getParticularsDataCell("Beneficiary Name: "));
        table.addCell(getParticularsDataCell("Kailash Tirtha Yatra"));

        cell = new Cell()
                .setBorder(Border.NO_BORDER)
                .add(table);

        return cell;
    }

    //Get Bank Details Body table cells
    private Cell getBlankCell() {
        cell = new Cell()
                .setBorder(Border.NO_BORDER);

        return cell;
    }

    //Our Address Table
    private Table getEndSignTable() throws MalformedURLException {
        //From Text
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 70),
                new UnitValue(UnitValue.PERCENT, 30)})
                .setMarginTop(30)
                .setWidthPercent(100);


        table.addCell(ktyLogo("https://i.ibb.co/ZhtMY5B/Screenshot-from-2019-07-28-17-45-57.png"));
        table.addCell(ktyLogo("https://i.ibb.co/bvzN1JF/Screenshot-from-2019-07-28-17-45-19.png"));
        table.startNewRow();
        table.addCell(getParticularsDataCell("Aruna Adhikari (BDD)"));
        table.addCell(getParticularsDataCell("Puru Dahal (Accounts)"));

        return table;
    }


}
