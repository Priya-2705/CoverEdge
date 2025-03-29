package com.humber.controller;

import com.humber.service.ReportService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReportController", urlPatterns = {"/report"})
public class ReportController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReportService reportService;

    @Override
    public void init() {
        this.reportService = new ReportService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=insurance-report.pdf");
        
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            
            document.open();
            addTitle(document);
            addCustomerStats(document);
            addPolicyStats(document);
            addClaimStats(document);
            document.close();
            
        } catch (DocumentException e) {
            throw new ServletException("Error generating PDF", e);
        }
    }

    private void addTitle(Document document) throws DocumentException {
        Paragraph title = new Paragraph("Insurance Broker Management Report",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);
    }

    private void addCustomerStats(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        addTableHeader(table, "Customer Statistics");
        
        addTableCell(table, "Total Customers", 
                    String.valueOf(reportService.getTotalCustomers()));
        
        document.add(table);
        document.add(Chunk.NEWLINE);
    }

    private void addPolicyStats(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        addTableHeader(table, "Policy Statistics");
        
        addTableCell(table, "Total Policies", 
                    String.valueOf(reportService.getTotalPolicies()));
        
        document.add(table);
        document.add(Chunk.NEWLINE);
    }

    private void addClaimStats(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        addTableHeader(table, "Claim Statistics");
        
        addTableCell(table, "Total Claims", 
                    String.valueOf(reportService.getTotalClaims()));
        addTableCell(table, "Approval Rate", 
                    String.format("%.2f%%", reportService.getApprovalRate()));
        
        document.add(table);
    }

    private void addTableHeader(PdfPTable table, String header) {
        PdfPCell headerCell = new PdfPCell(new Phrase(header));
        headerCell.setColspan(2);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(headerCell);
    }

    private void addTableCell(PdfPTable table, String label, String value) {
        table.addCell(createCell(label, true));
        table.addCell(createCell(value, false));
    }

    private PdfPCell createCell(String content, boolean isHeader) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setHorizontalAlignment(isHeader ? 
            Element.ALIGN_LEFT : Element.ALIGN_RIGHT);
        cell.setPadding(5);
        return cell;
    }
}