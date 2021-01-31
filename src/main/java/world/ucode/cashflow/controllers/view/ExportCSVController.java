package world.ucode.cashflow.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import world.ucode.cashflow.models.dao.Transaction;
import world.ucode.cashflow.repositories.TransactionRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ExportCSVController {
    @Autowired
    private TransactionRepo transactionRepo;

    @RequestMapping("/exportCSV")
    @GetMapping
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=transactions_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        /**
         * заменить на авторизированого юзера
         */
        List<Transaction> listTransactions = transactionRepo.findByWallet_User_Id(1);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Transaction ID", "Name", "Wallet", "Type", "Category", "Tag", "Date"};
        String[] nameMapping = {"id", "description", "wallet", "type", "category", "tag", "date"};

        csvWriter.writeHeader(csvHeader);

        for (Transaction t : listTransactions) {
            csvWriter.write(t, nameMapping);
        }

        csvWriter.close();
    }

}