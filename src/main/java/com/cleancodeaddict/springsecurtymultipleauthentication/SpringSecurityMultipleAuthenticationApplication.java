package com.cleancodeaddict.springsecurtymultipleauthentication;

import com.cleancodeaddict.springsecurtymultipleauthentication.entities.AppUser;
import com.cleancodeaddict.springsecurtymultipleauthentication.entities.Authority;
import com.cleancodeaddict.springsecurtymultipleauthentication.services.AppDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityMultipleAuthenticationApplication {
    private  final AppDaoService daoService;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityMultipleAuthenticationApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
            AppUser user1= AppUser.builder().id(null).username("user1").password("1234").build();
            AppUser user2= AppUser.builder().id(null).username("user2").password("2345").build();
            daoService.addNewUser(user1);
            daoService.addNewUser(user2);

            daoService.addNewAuth("read");
            daoService.addNewAuth("write");
            daoService.addNewAuth("delete");
            daoService.addNewAuth("execute");

            daoService.addAuthToUser("user1","read");
            daoService.addAuthToUser("user1","write");
        };
    }
}
