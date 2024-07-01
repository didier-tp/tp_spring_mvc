package tp.appliSpringMvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppliSpringMvcApplication {

	public static void main(String[] args) {
		//activation ou pas au démarrage du profil spring pour vues en version ".jsp"
		System.setProperty("spring.profiles.default", "dev,jsp");

		SpringApplication.run(AppliSpringMvcApplication.class, args);
		System.out.println("http://localhost:8080/appliSpringMvc");
	}
    
}
