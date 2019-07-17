package com.vtkty.vtktyacc.utils;


import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.vtkty.vtktyacc.service.model.Invoice;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("invoiceView")
public class InvoicePdfView extends AbstractView {


    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=myReport.pdf");
        Invoice invoice = (Invoice) model.get("invoice");


        //IText API
        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document pdfDocument = new Document(pdf);


        //title
        Paragraph title = new Paragraph(invoice.getName());
        title.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
        title.setFontSize(18f);
        title.setItalic();
        pdfDocument.add(title);


        //content
        Paragraph content = new Paragraph(invoice.getEmail());
        pdfDocument.add(content);


        pdfDocument.close();
    }
}
