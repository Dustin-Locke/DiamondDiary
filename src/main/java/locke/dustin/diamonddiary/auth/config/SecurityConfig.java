package locke.dustin.diamonddiary.auth.config;

import locke.dustin.diamonddiary.auth.component.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder    passwordEncoder;
    private final JwtFilter          jwtFilter;


    @Bean
    public SecurityFilterChain filterChain ( HttpSecurity http ) throws
                                                                 Exception {

        return http
                .csrf( csrf -> csrf.disable( ) )
                .authorizeHttpRequests( auth -> auth
                                                .requestMatchers(
                                                        "/auth/**",
                                                        "/swagger-ui/**",
                                                        "/swagger-ui.html",
                                                        "/api-docs/**",
                                                        "/v3/api-docs/**")
                                                .permitAll( )
                                                .anyRequest( ).authenticated( )
                                      )
                .authenticationProvider( authenticationProvider( ) )
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class )
                .build( );
    }

    @Bean
    public AuthenticationProvider authenticationProvider ( ) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider( );

        provider.setUserDetailsService( userDetailsService );
        provider.setPasswordEncoder( passwordEncoder );

        return provider;
    }

}
