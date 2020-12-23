package world.ucode.cashflow.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonAuthenticationFilter   extends UsernamePasswordAuthenticationFilter {
    protected AuthenticationManager authenticationManager;
    @SneakyThrows
    JsonAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        Authentication authentication = null;
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        else if (request.getHeader("Content-Type").equals(MediaType.APPLICATION_JSON.toString())) {
        UsernamePasswordAuthenticationToken authRequest = getUsernamePasswordToken(request);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        this.setAuthenticationManager(authenticationManager);
//        try {
            authentication = super.attemptAuthentication(request, response);
//            response.sendRedirect("/");
//        }
//            catch (Exception e) {
//            response.sendError(401,"INVALID LOGIN OR PASSWORD");
//            response.sendRedirect("/error");
//            }
        } else {
            authentication = super.attemptAuthentication(request, response);
        }

        return authentication;
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordToken(
            HttpServletRequest request) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObj = mapper.readTree(request.getInputStream());
        String username = jsonObj.get("login").asText();
        String password = jsonObj.get("password").asText();

        System.out.println(username);
        System.out.println(password);
        return new UsernamePasswordAuthenticationToken(
                username, password);
    }

    // other methods
}

