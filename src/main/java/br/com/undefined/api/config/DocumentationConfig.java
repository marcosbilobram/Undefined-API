package br.com.undefined.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentationConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Undefined API")
                        .version("v1")
                        .description("API de comércio de restaurantes em shopping")
                        .contact(new Contact().name("Marcos Bilobram")
                                .email("marcosbilobram@hotmail.com")
                                .url("https://www.linkedin.com/in/marcosbilobram/")));
    }

}