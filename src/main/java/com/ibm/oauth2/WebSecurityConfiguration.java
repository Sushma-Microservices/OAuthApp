package com.ibm.oauth2;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	       return super.authenticationManagerBean();
	}
	
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    	UserDetails user=User.builder().username("user").password(passwordEncoder().encode("secret")).
    			roles("USER").build();
    	UserDetails userAdmin=User.builder().username("admin").password(passwordEncoder().encode("secret")).
    			roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,userAdmin);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
    }
   

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
      
    	http
    	.csrf().disable()
    	.exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        	.and()
        	.authorizeRequests()
        	.antMatchers(HttpMethod.POST, "/oauth/token").permitAll()
        	//.antMatchers(HttpMethod.GET,"/api/orders/**").permitAll()
        	.anyRequest().authenticated(); 
    	
    }

}
