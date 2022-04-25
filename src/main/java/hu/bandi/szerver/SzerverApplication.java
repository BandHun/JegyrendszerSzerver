package hu.bandi.szerver;

import hu.bandi.szerver.services.implementations.DocumentServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.File;
import java.util.Arrays;

@SpringBootApplication
public class SzerverApplication {

    public static void main(final String[] args) {
        new File(DocumentServiceImpl.folderLocation).mkdir();
        SpringApplication.run(SzerverApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(
                Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept", "Authorization",
                              "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
                              "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(
                Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin",
                              "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
