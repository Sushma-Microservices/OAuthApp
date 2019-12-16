package com.ibm.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizacionServerConfiguration  extends AuthorizationServerConfigurerAdapter  {

	  @Autowired
	  @Qualifier("authenticationManagerBean")
	  private AuthenticationManager authenticationManager;
	  
	  @Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
		    .withClient("client")
            .authorizedGrantTypes("password", "authorization_code", "refresh_token")
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT","USER")
            .scopes("read","write")
            .autoApprove(true)	        
            .secret(new BCryptPasswordEncoder().encode("password"));          

	}
	/*
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }*/
	  @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(authaccessTokenConverter());
	    }
	  
	 @Override
	 public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	     endpoints
	     		.accessTokenConverter(authaccessTokenConverter())
	             .authenticationManager(authenticationManager)	        
	             .tokenStore(tokenStore());
	 }
	 
	 @Bean
	  public JwtAccessTokenConverter authaccessTokenConverter() {
		  JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		  converter.setSigningKey("123");
	       return converter;
	}
	 
	 	

	
}
