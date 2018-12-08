package br.com.luciano.moneyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author luciano
 * 
 * Apartir daqui as demais classes são escaneadas pelo spring
 *
 */
@SpringBootApplication
public class MoneyapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyapiApplication.class, args);
	}
}
