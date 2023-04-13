// Inicializar um projeto Spring Boot atraves de: https://start.spring.io/
// Converter JSON para Java atraves de: https://www.jsonschema2pojo.org/
// Pegar dados do CEP atraves de: https://viacep.com.br

package one.digitalinnovation.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients 
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}