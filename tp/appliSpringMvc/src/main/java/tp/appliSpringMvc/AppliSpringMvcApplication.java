package tp.appliSpringMvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppliSpringMvcApplication {

	public static void main(String[] args) {
		
		/*
		  selon la phase du Tp , commenter et decommenter les dépendances "maven"
		  d'une des 2 parties "thymeleaf" ou "JSP" au sein de pom.xml
		 */
		
		//activation ou pas au démarrage du profil spring pour vues en version ".jsp"
		//System.setProperty("spring.profiles.default", "dev,jsp");
		System.setProperty("spring.profiles.default", "dev");
		
		
		SpringApplication.run(AppliSpringMvcApplication.class, args);
		System.out.println("http://localhost:8080/appliSpringMvc");
	}

}
