package com.api.test.user.h2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.client.RestTemplate;


@Configuration
public class BeansFactory {
	
	@Autowired
	private DataSource dataSource;
	
//	@Autowired
//	private IUserRepository userRepo;
	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	TokenService tokenService;
	
	@Bean
	//@Scope(scopeName = "SINGLETON")
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	public TokenStore tokenStore() {
//		return new JwtTokenStore(accessTokenConverter());
		return new JdbcTokenStore(this.dataSource);
	}
	
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);	
		defaultTokenServices.setReuseRefreshToken(false);	
		return defaultTokenServices;
	}
	
//	@Bean
//	public TokenService tokenService() {
//		return new TokenService(); 
//	}

	
//    @Bean
//    public FilterRegistrationBean<TokenRequestFilter> tokenRequestFilter() {
//        FilterRegistrationBean<TokenRequestFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new TokenRequestFilter(userService, tokenService));
//        registrationBean.addUrlPatterns("/oauth/token"); // Agrega aquí las URL que deseas interceptar
//        return registrationBean;
//    }
	

}
