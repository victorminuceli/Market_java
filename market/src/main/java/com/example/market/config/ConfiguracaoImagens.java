package com.example.market.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;

@Configuration
public class ConfiguracaoImagens implements WebMvcConfigurer {

    private final String diretorioImagens;

    public ConfiguracaoImagens(
            @Value("${market.imagens.diretorio}")
            String diretorioImagens) {

        this.diretorioImagens = diretorioImagens;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registro) {
        Path pastaImagens = Path.of(diretorioImagens)
                .toAbsolutePath()
                .normalize();

        String enderecoPasta = pastaImagens.toUri().toString();

        if (!enderecoPasta.endsWith("/")) {
            enderecoPasta += "/";
        }

        registro
                .addResourceHandler("/imagens/produtos/**")
                .addResourceLocations(enderecoPasta);
    }
}