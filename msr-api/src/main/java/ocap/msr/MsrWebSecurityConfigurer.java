package ocap.msr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MsrWebSecurityConfigurer extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("MsrWebSecurityConfigurer.configure(HttpSecurity)");

		http.httpBasic().and()
		.authorizeRequests()
			.antMatchers("/v1/**").hasRole("USER").and()
		.csrf().disable();;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		System.out.println("MsrWebSecurityConfigurer.configure(AuthenticationManagerBuilder)");
		auth.authenticationProvider(authProvider);
	}

	
}
