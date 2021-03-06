package springboot.books.BookList.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import springboot.books.BookList.service.UserService;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/registerForm").permitAll().antMatchers("/saveBook").permitAll()
				.antMatchers("/confirm").permitAll().antMatchers("/").permitAll()
				// .antMatchers("/home").hasAnyRole("USER", "ADMIN")
				// .antMatchers("/list").hasAnyRole("USER", "ADMIN")
				.antMatchers("/list").permitAll().antMatchers("/addBook").permitAll().antMatchers("/search").permitAll()
				.antMatchers("/deleteBook").permitAll().antMatchers("/showUpdateForm").permitAll().and().formLogin()
				.usernameParameter("email").loginPage("/login").defaultSuccessUrl("/").permitAll().and().logout()
				.logoutSuccessUrl("/login").permitAll();

		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**", "/static/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
		super.configure(auth);
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(userService);
		return provider;
	}
}
