/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.GeradorPDF;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HeaderFooterPageEvent extends PdfPageEventHelper {
    private final GeradorPDF view;

    public HeaderFooterPageEvent(GeradorPDF view) {
        this.view = view;
    }
    public void onStartPage(PdfWriter writer,Document document) {
    	String traco = "______________________________________________________";
        Font font = new Font(Font.HELVETICA,20,Font.ITALIC,new Color(105,105,105));
        String nome_emp = (String) view.getJempresas().getSelectedItem();
        Paragraph cabecalhor = new Paragraph(nome_emp,font);
        cabecalhor.setAlignment(Element.ALIGN_CENTER);
        try {
            document.add(cabecalhor);
        } catch (DocumentException ex) {
            Logger.getLogger(HeaderFooterPageEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            document.add(new Paragraph(traco));
        } catch (DocumentException ex) {
            Logger.getLogger(HeaderFooterPageEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void onEndPage(PdfWriter writer,Document document) {
        String nomeEmp = (String) view.getJempresas().getSelectedItem();
        String bairro = view.getJbairro().getText();
        String uf = view.getJuf().getText();
        String cid = view.getJcid().getText();
        String tel = view.getJtel().getText();
        String insc = view.getJinsc().getText();
        String cnpj = view.getJcnpj().getText();
        String email = view.getJemail().getText();
        String num = view.getJnum().getText();
        String end = view.getJtxtend().getText();
        Font font = new Font(Font.HELVETICA,10,Font.NORMAL,new Color(105,105,105));
        String traco = "______________________________________________________";
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(traco,font), 5, 30, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(end + ","+ num +"-"+bairro+"-"+ cid +"("+ uf+") - "+"CNPJ n°"+ cnpj +" - "+"Inscr.Estadual n°" +insc,font), 5, 18, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("TEL" +tel+ " - Email:" + email,font), 5, 7, 0);
    }
} 
