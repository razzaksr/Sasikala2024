package sasikala.explore.service.cardapiartifact.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class CardSecurity {

    @Autowired
    OfficialsService officialsService;

    AuthenticationManager authenticationManager;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        UserDetails userDetails1= User.withDefaultPasswordEncoder().username("sasikala").password("saravanan").roles("admin").build();
//        UserDetails userDetails2= User.withDefaultPasswordEncoder().username("zealous").password("techcorp").roles("manager").build();
//        List<UserDetails> userDetailsList = Stream.of(userDetails2,userDetails1).collect(Collectors.toList());
//        return new InMemoryUserDetailsManager(userDetailsList);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.formLogin();
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests().requestMatchers("/signup").permitAll();

        httpSecurity.authorizeRequests().anyRequest().authenticated();


        //level 2
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(officialsService);
        authenticationManager = builder.build();
        httpSecurity.authenticationManager(authenticationManager);


        return httpSecurity.build();
    }
}
