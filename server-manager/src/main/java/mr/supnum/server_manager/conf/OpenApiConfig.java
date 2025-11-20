package mr.supnum.server_manager.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI serverManagerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Server Manager API")
                        .description("Service de gestion des serveurs (création, listing, renommage, statut, démarrage, arrêt, suppression).")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Aïchetou")
                                .email("23021@supnum.mr")
                        )
                );
    }
}

