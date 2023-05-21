package br.com.undefined.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthorizationFilter authorizationFilter;

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests()
                //.requestMatchers(HttpMethod.POST, "/api/registrar").permitAll()
                //.requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                .anyRequest()
                //.authenticated()
                .permitAll()
                .and()
                .csrf().disable()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                //.formLogin().disable()
                //.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }*/

    /*@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/

}
