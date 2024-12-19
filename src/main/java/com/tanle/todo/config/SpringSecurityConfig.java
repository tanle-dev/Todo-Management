package com.tanle.todo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig{
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf((csrf) -> csrf.disable());
		http.authorizeHttpRequests(authorization -> {
			authorization.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
			authorization.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
			authorization.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
			authorization.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
			authorization.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
			authorization.anyRequest().authenticated();});
		http.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails tanle = User.builder() 
				.username("tanle")
				.password(passwordEncoder().encode("tanle"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.builder()
				.username("Admin")
				.password(passwordEncoder().encode("admin"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(tanle, admin);
	}
}
