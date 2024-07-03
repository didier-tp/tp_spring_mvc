package tp.appliSpringMvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("!withSecurity")
public class WithoutSecurityConfig {
	
	@Bean
	protected SecurityFilterChain withoutSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher("/**")
		    .authorizeHttpRequests(
				auth -> auth.requestMatchers("/**").permitAll()
				)
		  .cors( Customizer.withDefaults() )
		  .csrf( csrf -> csrf.disable() )
		  .build();
	}

}
