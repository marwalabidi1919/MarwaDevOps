package org.example.tpcafe_marwalabidi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Marwa Labidi")
                        .version("1.0.0")
                        .description("""
                                📘 **Projet  Café – Gestion complète du système**
                                
                                👨‍💻 *Développé par :* **Marwa LABIDI**
                                
                                🔗 Liens utiles :
                                
                                - 💼 [Profil LinkedIn](https://www.linkedin.com/in/marwa-labidi-4a1590269/)
                                
                                📧 Pour toute question : [marwa.labidi@esprit.tn](mailto:marwa.labidi@esprit.tn)
                                """)
                        .contact(new Contact()
                                .name("Marwa LABIDI")
                                .email("marwa.labidi@esprit.tn")
                                .url("https://www.linkedin.com/in/marwa-labidi-4a1590269/")
                        )
                        .license(new License()
                                .name("Documentation & Démonstration du Projet")
                                .url("https://www.linkedin.com/in/marwa-labidi-4a1590269/")
                        )
                );
    }

    // ========================
    // 🔹 FILTRAGE PAR VOS CONTROLEURS
    // ========================

    // 📚 1. TOUTES LES APIs
    @Bean
    public GroupedOpenApi allApis() {
        return GroupedOpenApi.builder()
                .group("all-apis")
                .displayName("📚 Toutes les APIs")
                .pathsToMatch("/**")
                .build();
    }

    // 👥 2. GESTION DES CLIENTS
    @Bean
    public GroupedOpenApi clientApis() {
        return GroupedOpenApi.builder()
                .group("clients")
                .displayName("👥 Gestion des Clients")
                .pathsToMatch("/client/**")
                .build();
    }

    // 🏠 3. GESTION DES ADRESSES
    @Bean
    public GroupedOpenApi adressApis() {
        return GroupedOpenApi.builder()
                .group("adresses")
                .displayName("🏠 Gestion des Adresses")
                .pathsToMatch("/adresses/**")
                .build();
    }

    // ☕ 4. GESTION DES ARTICLES
    @Bean
    public GroupedOpenApi articleApis() {
        return GroupedOpenApi.builder()
                .group("articles")
                .displayName("☕ Gestion des Articles")
                .pathsToMatch("/articles/**")
                .build();
    }

    // 💳 5. CARTES DE FIDÉLITÉ
    @Bean
    public GroupedOpenApi carteFideliteApis() {
        return GroupedOpenApi.builder()
                .group("cartes-fidelite")
                .displayName("💳 Cartes de Fidélité")
                .pathsToMatch("/Cartefidelite/**")
                .build();
    }

    // 📦 6. GESTION DES COMMANDES
    @Bean
    public GroupedOpenApi commandeApis() {
        return GroupedOpenApi.builder()
                .group("commandes")
                .displayName("📦 Gestion des Commandes")
                .pathsToMatch("/commande/**")
                .build();
    }

    // 📋 7. DÉTAILS DES COMMANDES
    @Bean
    public GroupedOpenApi detailCommandeApis() {
        return GroupedOpenApi.builder()
                .group("details-commande")
                .displayName("📋 Détails des Commandes")
                .pathsToMatch("/dc/**")
                .build();
    }

    // 🎯 8. PROMOTIONS
    @Bean
    public GroupedOpenApi promotionApis() {
        return GroupedOpenApi.builder()
                .group("promotions")
                .displayName("🎯 Gestion des Promotions")
                .pathsToMatch("/promotion/**")
                .build();
    }


}