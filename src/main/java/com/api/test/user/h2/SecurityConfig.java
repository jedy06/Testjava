package com.api.test.user.h2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.api.test.user.h2.service.UserService;
import com.api.test.user.h2.token.TokenRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Value("${security.signing-key}")
	private String signingKey;

	@Value("${security.encoding-strength}")
	private Integer encodingStrength;

	@Value("${security.security-realm}")
	private String securityRealm;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired	
	private UserDetailsService userDetailsService;
	
//	@Autowired
//	private DataSource dataSource;
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	TokenService tokenService;
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsService> au = auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http		
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .httpBasic()
        .realmName(securityRealm)
        .and()
        .csrf()
        .disable();

	}
	
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers("/h2-console/**");
    }
		
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signingKey);		
		return converter;
	}
	
//	@Bean
//	public TokenStore tokenStore() {
////		return new JwtTokenStore(accessTokenConverter());
//		return new JdbcTokenStore(this.dataSource);
//	}
	
//	@Bean
//	@Primary
//	public DefaultTokenServices tokenServices() {
//		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//		defaultTokenServices.setTokenStore(tokenStore());
//		defaultTokenServices.setSupportRefreshToken(true);	
//		defaultTokenServices.setReuseRefreshToken(false);	
//		return defaultTokenServices;
//	}
	
    @Bean
    public FilterRegistrationBean<TokenRequestFilter> tokenRequestFilter() {
        FilterRegistrationBean<TokenRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenRequestFilter(userService));
        registrationBean.addUrlPatterns("/oauth/token"); // Agrega aquí las URL que deseas interceptar
        return registrationBean;
    }
	

}