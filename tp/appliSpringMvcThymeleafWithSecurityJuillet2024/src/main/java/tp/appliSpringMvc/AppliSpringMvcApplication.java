package tp.appliSpringMvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AppliSpringMvcApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		//activation ou pas au démarrage du profil spring pour vues en version ".jsp"
		//System.setProperty("spring.profiles.default", "dev,jsp");
		//System.setProperty("spring.profiles.default", "dev");
		System.setProperty("spring.profiles.default", "dev,withSecurity");

		SpringApplication.run(AppliSpringMvcApplication.class, args);
		System.out.println("http://localhost:8080/appliSpringMvc");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		//for starting inside JEE server (as tomat 10) with packaging war in pom.xml
		builder.profiles("dev");  //setting profiles here
		// or with system properties of the server (ex: tomcat)
		return builder.sources(AppliSpringMvcApplication.class);
	}
    
}

/*

NB: pour fabrication du .war ,
ajouter <packaging>war</packaging> dans pom.xml
puis maven package
---> target/appliSpringMvc-0.0.1-SNAPSHOT.war
ou   target/appliSpringMvcJuillet2024-0.0.1-SNAPSHOT.war

- Lancer un serveur "tomcat10" externe à vide (via bin/startup.bat ou autre)
- recopier appliSpringMvc-0.0.1-SNAPSHOT.war dans apache-tomcat-10.1.17\webapps
attendre nouveau répertoire
et tester via
http://localhost:8080/appliSpringMvc-0.0.1-SNAPSHOT/index-site.html
ou http://localhost:8080/appliSpringMvcJuillet2024-0.0.1-SNAPSHOT/index-site.html
 */
