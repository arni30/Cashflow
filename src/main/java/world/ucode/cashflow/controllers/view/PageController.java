package world.ucode.cashflow.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {

    @GetMapping
    public String getMain() {
        return "main";
    }

    @RequestMapping("/wallets")
    @GetMapping
    public String getWallets(HttpServletRequest request) {
        return "wallets";
    }

    @RequestMapping("/transactions")
    @GetMapping
    public String getTransactions() {
        return "transactions";
    }

    @RequestMapping("/categories")
    @GetMapping
    public String getCategories() {
        return "categories";
    }

    @RequestMapping("/statistics")
    @GetMapping
    public String getStatistics() {
        return "statistics";
    }

    @RequestMapping("/registrationEmailConfirm")
    @GetMapping
    public String getRegConfirm() {
        return "registrationEmailConfirm";
    }

}