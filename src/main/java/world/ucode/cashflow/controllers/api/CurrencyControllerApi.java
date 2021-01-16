package world.ucode.cashflow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.ucode.cashflow.models.Currency;
import world.ucode.cashflow.models.Tag;
import world.ucode.cashflow.repositories.CurrencyRepo;
import world.ucode.cashflow.repositories.TagRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/currency")
public class CurrencyControllerApi {
    @Autowired
    private CurrencyRepo currencyRepo;

    @PostMapping("/create")
    public void createTransaction(@RequestBody Currency currency, HttpServletResponse response) throws IOException {
        try {
            currencyRepo.save(currency);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/update")
    public void updateTransaction(@RequestBody Currency newCurrency, HttpServletResponse response) throws IOException {
        try {
            Currency currency = currencyRepo.findById(newCurrency.getId());
            currency.setName(newCurrency.getName().equals("") ? currency.getName() : newCurrency.getName());
            currency.setTicker(newCurrency.getTicker() == 0 ? currency.getTicker() : newCurrency.getTicker());
            currencyRepo.save(currency);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/delete")
    public void deleteCurrency(@RequestBody Currency currency, HttpServletResponse response) throws IOException {
        try {
            currencyRepo.delete(currency);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }
}
