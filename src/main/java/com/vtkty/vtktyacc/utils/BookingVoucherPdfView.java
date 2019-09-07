package com.vtkty.vtktyacc.utils;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.vtkty.vtktyacc.service.model.Address;
import com.vtkty.vtktyacc.service.model.BookingVoucher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.util.Map;

@Component("bookingVoucherView")
public class BookingVoucherPdfView extends AbstractView {

    private Paragraph p;
    private Cell cell;
    private Address address;
    private BookingVoucher bookingVoucher;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        address = (Address) model.get("address");
        bookingVoucher = (BookingVoucher) model.get("bookingVoucher");
        final String DEST = "attachment; filename=" + "Booking Voucher" + bookingVoucher.getClientName() + "/" + bookingVoucher.getVoucherNo() + ".pdf";
        response.setHeader("Content-Disposition", DEST);

        //IText API
        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document pdfDocument = new Document(pdf);
        pdfDocument.setFontSize(11);

        pdfDocument.add(getTopLogoAndTitle(bookingVoucher));


        pdfDocument.close(); //close document
    }


    //Top Table with Logo and Title
    private Table getTopLogoAndTitle(BookingVoucher bookingVoucher) throws MalformedURLException {
        //From Text
        Table table = new Table(new UnitValue[]{
                new UnitValue(UnitValue.PERCENT, 50),
                new UnitValue(UnitValue.PERCENT, 50)})
                .setMarginBottom(30)
                .setWidthPercent(100);


        table.addCell(ktyLogo());
        table.addCell("Booking Confirmation");

        return table;
    }

    //Method for Logo
    private Cell ktyLogo() throws MalformedURLException {
        // Creating an ImageData object
        ImageData data = ImageDataFactory.create("https://i.ibb.co/6r8ygjQ/Screenshot-from-2019-07-28-18-18-09.png");
        // Creating an Image object
        Image image = new Image(data);
        cell = new Cell()
                .setBorder(Border.NO_BORDER)
                .add(image);
        return cell;
    }
}
