package com.alejandro.banco.config;

import com.alejandro.banco.mapper.ClienteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class AppConfiguration {

    @Bean
    public ClienteMapper clientMapper() {
        return new ClienteMapper();
    }
}
