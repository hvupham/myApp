package org.project.myapp.configuration;

import lombok.RequiredArgsConstructor;
import org.project.myapp.filters.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
         http
                .csrf(AbstractHttpConfigurer::disable)
                 .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                 .authorizeHttpRequests(request -> {
                     request
                             .requestMatchers(
                                     String.format("%s/users/login", apiPrefix),
                                     //healthcheck
                                     String.format("%s/healthcheck/**", apiPrefix)

                             )
                             .permitAll()
                             .requestMatchers(GET,
                             String.format("%s/roles**", apiPrefix)).permitAll()

                             .requestMatchers(GET,
                                     String.format("%s/categories/**", apiPrefix)).permitAll()

                             .requestMatchers(GET,
                                     String.format("%s/products/**", apiPrefix)).permitAll()

                             .requestMatchers(GET,
                                     String.format("%s/products/images/*", apiPrefix)).permitAll()

                             .requestMatchers(GET,
                                     String.format("%s/orders/**", apiPrefix)).permitAll()

                             .requestMatchers(DELETE,
                                     String.format("%s/order_details/**", apiPrefix)).permitAll()

                             .anyRequest()
                             .authenticated();
                     //.anyRequest().permitAll();
                 });

         return http.build();
    }
}
