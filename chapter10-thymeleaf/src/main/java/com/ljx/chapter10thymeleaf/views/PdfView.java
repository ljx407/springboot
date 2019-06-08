package com.ljx.chapter10thymeleaf.views;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ljx.chapter10thymeleaf.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class PdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users = (List<User>) model.get("users");
        document.addTitle("hello jasonliu");

        PdfPTable table = new PdfPTable(4);

        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);

        PdfPCell cellId = new PdfPCell();
        cellId.setPhrase(new Phrase("id",font));
        table.addCell(cellId);

        PdfPCell cellUserName = new PdfPCell();
        cellUserName.setPhrase(new Phrase("userName",font));
        table.addCell(cellUserName);

        PdfPCell cellSex = new PdfPCell();
        cellSex.setPhrase(new Phrase("sex",font));
        table.addCell(cellSex);

        PdfPCell cellMemo = new PdfPCell();
        cellMemo.setPhrase(new Phrase("memo",font));
        table.addCell(cellMemo);

        for (User user : users) {
            table.addCell(user.getId().toString());
            table.addCell(user.getUserName());
            table.addCell(user.getSex().getKey());
            table.addCell(user.getMemo());
        }

        document.add(table);
    }
}
