package com.api.test.user.h2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
    private ResourceServerTokenServices tokenServices;
	
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
                http
                .exceptionHandling().authenticationEntryPoint(new AuthException())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/v2/api-docs/**" ).permitAll()
//                .antMatchers("/consultas/**" ).authenticated()                
//                .antMatchers("/especialidades/**" ).authenticated()
//                .antMatchers("/examenes/**" ).authenticated()
//                .antMatchers("/medicos/**" ).authenticated()
//                .antMatchers("/menus/**" ).authenticated()
//                .antMatchers("/tokens/**" ).permitAll()
//                .antMatchers("/consultaexamenes/**" ).authenticated()
//                .antMatchers("/pacientes/**" ).authenticated();   
//                .antMatchers("/users/**" ).authenticated()
                .antMatchers("/h2-console/**" ).permitAll()
                .antMatchers("/users/**" ).permitAll();
    }    


 
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

//		configuration.setAllowedOrigins(allowedOrigins);
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", configuration);
		
		return source;
		}   
}