package tp.appliSpringMvc;

import java.util.List;

//import org.mycontrib.mysecurity.jwt.util.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Profile("withSecurity")
@ComponentScan(basePackages = {"org.mycontrib.mysecurity"})
@EnableMethodSecurity()//pour que le test @PreAuthorize("hasRole('ADMIN')") puisse bien fonctionner
public class SecurityConfig {
/*
	@Bean
	@Order(1)
	protected SecurityFilterChain restFilterChain(HttpSecurity http,JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
		return http.securityMatcher("/rest/**")
		    .authorizeHttpRequests(
				auth -> auth.requestMatchers("/rest/api-login/public/login").permitAll()
				            .requestMatchers(HttpMethod.GET,"/rest/api-bank/compte/**").permitAll()
				            //.requestMatchers(HttpMethod.DELETE,"/rest/api-bank/compte/**").hasRole("ADMIN") ou bien @PreAuthorize("hasRole('ADMIN')") dans la classe CompteRestCtrl
				            .requestMatchers("/rest/**").authenticated()
				)
		    .cors( Customizer.withDefaults())
		    
		  //enable CORS (avec @CrossOrigin sur class @RestController)
		  .csrf( csrf -> csrf.disable() )
		  // If the user is not authenticated, returns 401
		  .exceptionHandling(eh ->  eh.authenticationEntryPoint(getRestAuthenticationEntryPoint() ))
			
		  // This is a stateless application, disable sessions
		  .sessionManagement(sM -> sM.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		
		  // Custom filter for authenticating users using tokens
		  .addFilterBefore(jwtAuthenticationFilter,  UsernamePasswordAuthenticationFilter.class)
		  .build();
	}
	
	private AuthenticationEntryPoint getRestAuthenticationEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
	}
	
	//explicit config of AuthenticationManager from ProviderManager and list of AuthenticationProvider
	@Bean
	public AuthenticationManager authenticationManager(List<AuthenticationProvider> authenticationProviders) {
	    return new ProviderManager(authenticationProviders);
	}
	
	//explicit config of (Dao)AuthenticationManager from UserDetailsService (in MyUserDetailsService)
	@Bean
	public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder encoder) {
	   DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	   authProvider.setUserDetailsService(userDetailsService);
	   authProvider.setPasswordEncoder(encoder);
	   return authProvider;
	}
	*/
	
	@Bean
	@Order(2)
	protected SecurityFilterChain siteFilterChain(HttpSecurity http) throws Exception {
		
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
