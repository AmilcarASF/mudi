package br.com.alura.mvs.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource datasource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/home/**")
			.permitAll()		
		.anyRequest()		
			.authenticated()
		.and()
			.formLogin(form -> form
	            .loginPage("/login")
	            .defaultSuccessUrl("/usuario/pedido", true)
	            .permitAll()
	        )
			.logout(logout -> { 
				logout.logoutUrl("/logout")
				.logoutSuccessUrl("/home");
				})
			.csrf().disable();
			
			
			//.csrf().disable();
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
   	
    	
    	auth.jdbcAuthentication()
    	.dataSource(datasource)
    	.passwordEncoder(encoder);
    }
	
}
