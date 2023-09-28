package com.example.demo.config;

import org.springframework.context.annotation.Bean;



import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
	
	
	/*
	
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("username")
            .password("{noop}password") // Password is "password"
            .roles("USER");
    }*/

	
	//@Autowired
//    private DataSource dataSource;
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.jdbcAuthentication()
//            .dataSource(dataSource)
//            .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
//            .authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?")
//            .passwordEncoder(passwordEncoder());
//    }
	
	 @Bean
	   public PasswordEncoder passwordEncoder() {



	        return new BCryptPasswordEncoder();


	    }
		/*
		 * @Bean UserDetailsService userDetailsService() { return new
		 * UserDetailsServiceHelper(); //UserDetails admin = User.with }
		 */
	
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity ) throws Exception
	{
		
//		httpSecurity.authorizeHttpRequests(authorizeHttpRequests-> authorizeHttpRequests
//				//.requestMatchers(HttpMethod.GET, "/api/users/showAllUsers").permitAll()
//				.requestMatchers(HttpMethod.GET,"/signup").permitAll());
//				
//				//.requestMatchers(HttpMethod.POST, "/api/users/createNewUser").hasRole("ADMIN"));
//		
//		
//		httpSecurity.csrf(csrf -> csrf.disable());
//		         return httpSecurity.build();

        httpSecurity.httpBasic();
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll()
               // .requestMatchers("/signup").permitAll()
                //.requestMatchers("/api/users/showAllUsers").hasAuthority("ADMIN")


              //  .requestMatchers("/signup").hasAuthority("USER")

                .requestMatchers("/api/users/showAllUsers").hasAuthority("ADMIN")
                .anyRequest().permitAll()
//                .and()
//                .authorizeHttpRequests()
             
                .and().formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/analyticsPage");
                //.and().build();
		httpSecurity.csrf(csrf -> csrf.disable());

        // httpSecurity.csrf().ignoringRequestMatchers("/api/users/createNewUser");
         return httpSecurity.build();
        
        //httpSecurity.csrf().disable();
        //  httpSecurity.csrf().ignoringRequestMatchers("/api/users/createNewUser");
/*
        httpSecurity.cors().and()
        .csrf()
        .disable()
                .authorizeHttpRequests()
                .requestMatchers("api/users/showAllUsers").permitAll()
//                .and().authorizeHttpRequests()
//                .requestMatchers("/api/users/showAllUsers").hasRole("USER").anyRequest().authenticated()
                .and().formLogin(withDefaults());
*/
//                //.hasRole("ADMIN")
              //  .anyRequest().denyAll().and().formLogin();
				
			//httpSecurity.httpBasic();
        // httpSecurity.authorizeHttpRequests().requestMatchers("/api/orders/showall").hasRole("USER").and().formLogin();

       //return httpSecurity.build();
	}

//	
//	@Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//   
	public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .requestMatchers("/custom-login.css"); // Allow access to CSS resources
    }
    
}
