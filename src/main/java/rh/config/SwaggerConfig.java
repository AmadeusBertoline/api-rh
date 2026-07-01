package rh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("API RH - gerenciamento de funcionários")
                        .version("1.0.0")
                        .description("API REST para gerenciamento de funcionários e suas avaliações")
                        .contact(
                                new Contact()
                                        .name("Amadeus Silva")
                                        .email("amadeusbertoline123@gmail.com"))

                );

    }

}
