package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación de ecommerce para la venta de ordenadores.
 * Esta clase contiene el método {@code main} que se encarga de iniciar la aplicación
 * Spring Boot.
 * 
 * @author [Dmp]
 */
@SpringBootApplication
public class EcommerceApplication {

    /**
     * Método principal que arranca la aplicación Spring Boot.
     * 
     * @param args Argumentos de la línea de comandos, no utilizados en esta aplicación.
     */
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
  