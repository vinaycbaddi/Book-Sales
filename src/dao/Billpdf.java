package dao;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Billpdf {
	public void billpdf(String custPhone, String custName, String custEmail, long m, String custBook, int q,
			double total) throws Exception, DocumentException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("bill.pdf"));
		document.open();
		Paragraph t = new Paragraph("*BOOK STORE*\n");
		t.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(t);

		Paragraph h = new Paragraph("Order Bill\n");
		h.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(h);

		Paragraph l = new Paragraph("____________________________________________________________\n");
		l.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(l);

		document.add(new Paragraph("Mobileno           : " + custPhone));
		document.add(new Paragraph("Customer Name      : " + custName));
		document.add(new Paragraph("Customer Email     : " + custEmail));
		document.add(new Paragraph("Book_Id            : " + m));
		document.add(new Paragraph("Book_Name          : " + custBook));
		document.add(new Paragraph("Book_Quantity      : " + q));
		document.add(new Paragraph("Book_Price         : " + total));
		document.close();
		writer.close();
		System.out.println("PDF Bill is generated please check inbox\n");
	}

}
