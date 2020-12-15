package world.ucode.cashflow.utils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import world.ucode.cashflow.models.Message;
import world.ucode.cashflow.repositories.MessageRepo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

/**
 * The type Data export service.
 */
@Component
public class DataExportService {
    @Autowired
    private MessageRepo messageRepo;

    /**
     * Gets defendants.
     *
     * @throws Exception the exception
     */
    public void getDefendants() throws Exception {
        Workbook workbook = null;
        String fileName = "test.xlsx";
        workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Countries");
        Iterable<Message> a = messageRepo.findAll();
        Iterator<Message> iterator = a.iterator();

        int rowIndex = 0;
        while(iterator.hasNext()){
            Message message = iterator.next();
            Row row = sheet.createRow(rowIndex++);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(message.getTag());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(message.getText());
        }

        //lets write the excel data to file now
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        fos.close();
        System.out.println(fileName + " written successfully");
    }
}