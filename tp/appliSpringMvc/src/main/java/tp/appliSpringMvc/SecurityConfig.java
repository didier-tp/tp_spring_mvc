package tp.appliSpringMvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("withSecurity")
public class SecurityConfig {

	//future autre @Bean @Order(1) pour future partie API-REST
	
	@Bean
	@Order(2)
	protected SecurityFilterChain restFilterChain(HttpSecurity http) throws Exception {
		
		
		
		return http.securityMatcher("/site/**")
		    .authorizeHttpRequests(
				auth -> auth.requestMatchers("/site/app/**").permitAll()
				            .requestMatchers("/site/basic/**").permitAll()
				            /*.requestMatchers("/site/bank/**").authenticated()*/
				            .requestMatchers("/site/bank/**").hasRole("CUSTOMER")
				)
		  .csrf( Customizer.withDefaults() )
		  /*  .csrf(csrf -> {
		    	var  requestHandler = new CsrfTokenRequestAttributeHandler ();
		    	var tokenRepository =new HttpSessionCsrfTokenRepository();
		    	// set the name of the attribute the CsrfToken will be populated on
		    	requestHandler.setCsrfRequestAttributeName("_csrf");
		    	csrf
	            .csrfTokenRepository(tokenRepository)
	            .csrfTokenRequestHandler(requestHandler)
	            .sessionAuthenticationStrategy(new CsrfAuthenticationStrategy(tokenRepository));
		          }
	            )*/
		  //.formLogin( formLogin -> formLogin.permitAll() )
		  .formLogin( formLogin -> formLogin.loginPage("/site/app/login")
				  							.failureUrl("/site/app/login-error")
				  							.defaultSuccessUrl("/site/app/toWelcome", false)
				  							.permitAll())
		  .sessionManagement(session -> session
		            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		  .build();
		//NB: /site/app/login et /site/app/login-error redigirent tous les deux vers templates/login.html
	}

		
	@Bean
	@Order(3)
	protected SecurityFilterChain otherFilterChain(HttpSecurity http,PasswordEncoder passwordEncoder) throws Exception {
		return http.securityMatcher("/**")
		     .authorizeHttpRequests(
				// pour image , html , css , js
				auth -> auth.requestMatchers("/**").permitAll())
		     .build();
	}
	
	/*
	//V1 (version basique, sans lien avec les clients de la banque)
	//la V2 en codée dans la classe MyUserDetailsService
	@Bean
	public UserDetailsService users(PasswordEncoder passwordEncoder) {
		UserDetails user1 = User.builder()
			.username("user1").password(passwordEncoder.encode("pwd1")).roles("USER").build();
		UserDetails user2 = User.builder()
			.username("user2").password(passwordEncoder.encode("pwd2")).roles("USER").build();
		UserDetails admin1 = User.builder()
			.username("admin1")
			.password(passwordEncoder.encode("pwd1")).roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user1, user2,admin1);
	}
	*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		//or new BCryptPasswordEncoder(int strength) with strength between 4 and 31
	}

}
