package world.ucode.cashflow.controllers.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import world.ucode.cashflow.models.dao.Currency;
import world.ucode.cashflow.models.dao.Users;
import world.ucode.cashflow.repositories.CurrencyRepo;
import world.ucode.cashflow.repositories.UserRepo;
import world.ucode.cashflow.repositories.WalletRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/currency")
public class CurrencyControllerApi {
    private final UserRepo userRepo;
    private final CurrencyRepo currencyRepo;
    @Autowired
    public CurrencyControllerApi(UserRepo userRepo,
                                CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
        this.userRepo = userRepo;
    }

//    @GetMapping("/get")
//    public void getAllCurrencies(HttpServletRequest request) {
//        Users user = userRepo.findByLogin(request.getUserPrincipal().getName());
//        Iterable<Currency> currencies = currencyRepo.findAll();
//        for (Currency currency:currencies) {
//            System.out.println(currency.getId());
//            System.out.println(currency.getName());
//            System.out.println(currency.getTicker());
//        }
////        getMonoCurrencies();
//    }

//    private static void getMonoCurrencies()
//    {
////        final String uri = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
//        final String uri = "https://api.monobank.ua/bank/currency";
//
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
//
//        System.out.println(result);
//    }

    @PostMapping("/create")
    public void createTransaction(@RequestBody Currency currency, HttpServletResponse response) throws IOException {
        log.info("CREATE CURRENCY");
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
        log.info("UPDATE CURRENCY");
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
        log.info("DELETE CURRENCY");
        try {
            currencyRepo.delete(currency);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }
}
