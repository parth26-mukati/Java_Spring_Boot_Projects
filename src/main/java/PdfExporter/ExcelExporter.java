package PdfExporter;

import java.io.IOException;
import java.util.List;

 

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

 


import com.example.entity.EmployeeEntity;

public class ExcelExporter {
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<EmployeeEntity>employee;

    public ExcelExporter(List<EmployeeEntity> employee) {
        this.employee = employee;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Employee");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Employee_ID", style);      
        createCell(row, 1, "Name", style);       
        createCell(row, 2, "Salary", style);    
        createCell(row, 3, "Address", style);
        createCell(row, 4, "Designation", style);
        createCell(row, 5, "Primary_skill", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (EmployeeEntity user : employee) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, user.getEmployeeId(), style);
            createCell(row, columnCount++, user.getName(), style);
            createCell(row, columnCount++, user.getSalary(), style);
            createCell(row, columnCount++, user.getAddress().toString(), style);
            createCell(row, columnCount++, user.getDesignation(), style);
            createCell(row, columnCount++, user.getPrimarySkill(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }




}


