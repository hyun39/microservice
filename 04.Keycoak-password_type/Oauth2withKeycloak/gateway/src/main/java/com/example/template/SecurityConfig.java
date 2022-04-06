package com.example.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {

        http
                .cors().and()       // cross site http requets 허용
                .csrf().disable()   // cross site reqeust forgery  
                .authorizeExchange() //  인가 받을것과 받지 않을 것을 지정  
                .pathMatchers("/test/permitAll", "/products/**", "/login/**").permitAll()
                .pathMatchers("/orders/**").authenticated()                 // Check Keycloak RBAC
                .pathMatchers("/test/user").hasAuthority("ROLE_USER")       // Check Keycloak RBAC
                .pathMatchers("/test/admin").hasAuthority("ADMIN")          // Check Keycloak RBAC
                .pathMatchers("/test/authenticated").authenticated()        // Check Keycloak RBAC
                .anyExchange().authenticated()
                .and()
                .oauth2Login() //  default login이 설정
                .and()
                .oauth2ResourceServer() // resource serer로 부여 
                .jwt()
                .jwtAuthenticationConverter(grantedAuthoritiesExtractor());

        return http.build();
    }

    Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter =
                new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
                (new GrantedAuthoritiesExtractor());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    static class GrantedAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            final Map<String, Object> resourceAccess = (Map<String, Object>)jwt.getClaims().get("resource_access");
            final Map<String, List<String>> clientId = (Map<String, List<String>>)resourceAccess.get("my_client");

            return  clientId.get("roles").stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
    }

}
