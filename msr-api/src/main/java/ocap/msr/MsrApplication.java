package ocap.msr;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import ocap.msr.entity.User;
import ocap.msr.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "ocap.msr.api", "ocap.msr", "ocap.msr.service", "ocap.msr.util" })
@EnableJpaRepositories("ocap.msr.repository")
@EntityScan("ocap.msr.entity")
public class MsrApplication  {
	@Autowired
	private UserRepository userRepository;
	
    public static void main(String[] args) throws Exception {
        new SpringApplication(MsrApplication.class).run(args);
    }
    
    @Bean
    public ModelMapper modelMapper() {
    		return new ModelMapper(); 
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    		return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    UserDetailsService userDetailsService() {
    		return (email) -> {
    			System.out.println("userDetailsService: " + email);
    			User user = userRepository.findByEmail(email);
    			
    			if(user == null) {
    				throw new UsernameNotFoundException("could not find the user: " + email);
    			}
    			
    			System.out.println("user:" + user);
    			org.springframework.security.core.userdetails.User credential = 
    					new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), 
						true, true, true, true,
						AuthorityUtils.createAuthorityList("USER"));

    			return credential;
    		};
    }
    
    @Bean
    public DaoAuthenticationProvider authProvider() {
    		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    		
    		authProvider.setUserDetailsService(userDetailsService());
    		authProvider.setPasswordEncoder(passwordEncoder());
    		
    		return authProvider;
    }
    
/*    
    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    		System.out.println("configureGlobal...");
    		auth.authenticationProvider(authProvider());
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//		
//		http.httpBasic().and()
//			.authorizeRequests()
//				.antMatchers("/v1/**").hasRole("USER");
	}    
*/	
	
}

