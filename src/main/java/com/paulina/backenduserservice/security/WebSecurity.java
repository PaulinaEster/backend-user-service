package com.paulina.backenduserservice.security;

import com.paulina.backenduserservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users")  // esta dizendo que o metodo post é publico
                .permitAll()
                .anyRequest().authenticated()
                .and().addFilter(getAuthenticationFilter());
      
    }

    @Override
    protected void configure(@Autowired AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
    
    public AuthenticationFilter getAuthenticationFilter() throws Exception{
    	final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
    	
    	filter.setFilterProcessesUrl("/users/login");
    	
    	return filter;
    	
    }

}
/*{
        "userId": "9bbccecf-3745-4c72-a419-a72957889b1f",
        "firstName": "pablo",
        "lastName": "camila",
        "email": "catapimbas@gmail.com",
        "encryptedPassword": "$2a$10$7DFmQPjUoW3H6t2gnzhjhOVrNYsTjgvvh7X/ETBOssm7017WR5vie"
    }
*/