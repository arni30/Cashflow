package world.ucode.cashflow.controllers.view;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import world.ucode.cashflow.models.dao.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/hallo")
public class HalloController {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    @PostMapping
    public void postHallo(@RequestBody Users user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setStatus(111);
        System.out.println("zbs");

    }
    @GetMapping
    public String getHallo(HttpServletRequest request) throws Exception {
//        System.out.println(request.getUserPrincipal().getName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        sendGet();
        return "main";
    }
    private static void getEmployees()
    {
//        final String uri = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
       final String uri = "https://api.monobank.ua/personal/client-info";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }
    private void sendGet() throws Exception {

        HttpGet request = new HttpGet("https://api.monobank.ua/personal/client-info");
//        HttpGet request = new HttpGet("https://api.monobank.ua/personal/statement/0/1606815166");

        // add request headers
        request.addHeader("X-Token", "uz3kv-byILc8kL6cvQwIfqw7ChzJVp0w1tN6JbNHmrRU");
//        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }

    }

}
