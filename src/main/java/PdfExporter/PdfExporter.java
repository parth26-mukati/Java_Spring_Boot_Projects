package PdfExporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

 

import com.example.entity.EmployeeEntity;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class PdfExporter {
	
	private List<EmployeeEntity> employee ; 

	public PdfExporter(List<EmployeeEntity> employee) {
        this.employee = employee;
    }

	public void writeTableHeader (PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.GRAY);
		cell.setPadding(6);
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		cell.setPhrase(new Phrase("Employee ID",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("NAME",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("SALARY",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("ADDRESS",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("DESIGNATION",font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PRIMARY SKILL",font));
		table.addCell(cell);



	}

	public void writeTableData(PdfPTable table ) {
		for(EmployeeEntity emp : employee) {
			table.addCell(String.valueOf(emp.getEmployeeId()));
			table.addCell(emp.getName());
			table.addCell(String.valueOf(emp.getSalary()));
			table.addCell(emp.getAddress());
			table.addCell(emp.getDesignation());
			table.addCell(emp.getPrimarySkill());				
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.RED);

        Paragraph p = new Paragraph("Employee Details", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3.5f, 2.5f,4.0f, 4.0f,4.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }










 

}


