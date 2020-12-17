package world.ucode.cashflow.configuration;//package world.ucode.cashflow.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import java.security.AuthProvider;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().disable()
//                .csrf().disable();
//        http.authorizeRequests().antMatchers("/", "/main", "/authorization").permitAll();
//        http.authorizeRequests().antMatchers("/profile/**").hasAnyAuthority("BIDDER", "SELLER");
//        http.authorizeRequests().antMatchers("/addLot/**").hasAnyAuthority("SELLER");
//        http.authorizeRequests().antMatchers("/addFeedback/**").hasAnyAuthority("BIDDER");
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/accessDenied").accessDeniedHandler(new AccessDeniedExceptions());
//        http.authorizeRequests().and().formLogin()//
//                // Submit URL of login page.
//                .loginProcessingUrl("/authorization").permitAll()
//                .loginPage("/authorization")
//                .failureUrl("/accessDenied")
//                .defaultSuccessUrl("/main")
//                .usernameParameter("login")
//                .passwordParameter("password");
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                // Config for Logout Page
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/main");
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    };
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) {
////        auth.authenticationProvider(new AuthProvider());
////    }
//@Bean
//@Override
//public UserDetailsService userDetailsService() {
//    UserDetails user =
//            User.withDefaultPasswordEncoder()
//                    .username("user")
//                    .password("password")
////                    .roles("USER")
////                    .build();
////
////    return new InMemoryUserDetailsManager(user);
////}
////}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import world.ucode.cashflow.service.UserDetailsServiceImpl;

import java.security.AuthProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/sign_up", "/main").permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
//        http.authorizeRequests().antMatchers("/", "/home", "/sign_up", "/main").permitAll();
//        http
//                .cors().disable()
//                .csrf().disable();
//        http.authorizeRequests().and().formLogin()//
//                // Submit URL of login page.
//                .loginProcessingUrl("/login").permitAll()
//                .failureUrl("/accessDenied")
//                .defaultSuccessUrl("/aaa")
//                .usernameParameter("login")
//                .passwordParameter("password");
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                // Config for Logout Page
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/main");
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Autowired
    private DaoAuthenticationProvider authenticationProvider;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
////                User.withDefaultPasswordEncoder()
//                       User.withUsername("a")
//                        .password("a")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}