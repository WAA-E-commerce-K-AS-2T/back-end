package com.spa.ecommerce.security;

import com.spa.ecommerce.common.Constant;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/login", "/signup", "/logout", "/reset-password/**", "/**").permitAll()

                        .requestMatchers(HttpMethod.GET, Constant.PRODUCT_URL_PREFIX )
                        .hasAnyAuthority("product.read")
                        .requestMatchers(HttpMethod.POST, Constant.PRODUCT_URL_PREFIX )
                        .hasAnyAuthority("product.write")
                        .requestMatchers(HttpMethod.PUT, Constant.PRODUCT_URL_PREFIX+ "/*" )
                        .hasAnyAuthority("product.write")
                        .requestMatchers(HttpMethod.DELETE, Constant.PRODUCT_URL_PREFIX+ "/*" )
                        .hasAnyAuthority("product.delete")

                        .requestMatchers(HttpMethod.GET, Constant.PRODUCT_URL_PREFIX+ "/*/reviews" )
                        .hasAnyAuthority("review.read")
                        .requestMatchers(HttpMethod.GET, Constant.PRODUCT_URL_PREFIX+ "/*/reviews/*" )
                        .hasAnyAuthority("review.read")
                        .requestMatchers(HttpMethod.POST, Constant.PRODUCT_URL_PREFIX+ "/*/reviews" )
                        .hasAnyAuthority("review.write")
                        .requestMatchers(HttpMethod.PUT, Constant.PRODUCT_URL_PREFIX+ "/*/reviews/*" )
                        .hasAnyAuthority("review.write")
                        .requestMatchers(HttpMethod.DELETE, Constant.PRODUCT_URL_PREFIX+ "/*/reviews/*" )
                        .hasAnyAuthority("review.delete")

                        .requestMatchers(HttpMethod.GET, Constant.SHOPPINGCART_URL_PREFIX )
                        .hasAnyAuthority("shoppingcart.read")
                        .requestMatchers(HttpMethod.POST, Constant.SHOPPINGCART_URL_PREFIX )
                        .hasAnyAuthority("shoppingcart.write")
                        .requestMatchers(HttpMethod.PUT, Constant.SHOPPINGCART_URL_PREFIX+ "/*" )
                        .hasAnyAuthority("shoppingcart.write")
                        .requestMatchers(HttpMethod.DELETE, Constant.SHOPPINGCART_URL_PREFIX+ "/*" )
                        .hasAnyAuthority("shoppingcart.delete")

                        .requestMatchers(HttpMethod.GET, Constant.ADMIN_USER_URL_PREFIX )
                        .hasAnyAuthority("users.read")
                        .requestMatchers(HttpMethod.POST, Constant.ADMIN_USER_URL_PREFIX )
                        .hasAnyAuthority("users.write")
                        .requestMatchers(HttpMethod.PUT, Constant.ADMIN_USER_URL_PREFIX )
                        .hasAnyAuthority("users.write")
                        .requestMatchers(HttpMethod.PUT, Constant.ADMIN_USER_URL_PREFIX +"/*" )
                        .hasAnyAuthority("users.write")
                        .requestMatchers(HttpMethod.DELETE, Constant.ADMIN_USER_URL_PREFIX +"/*" )
                        .hasAnyAuthority("users.delete")

                        .requestMatchers(HttpMethod.GET, Constant.ADMIN_ROLE_URL_PREFIX )
                        .hasAnyAuthority("roles.read")
                        .requestMatchers(HttpMethod.POST, Constant.ADMIN_ROLE_URL_PREFIX )
                        .hasAnyAuthority("roles.write")
                        .requestMatchers(HttpMethod.PUT, Constant.ADMIN_ROLE_URL_PREFIX+"/*" )
                        .hasAnyAuthority("roles.write")
                        .requestMatchers(HttpMethod.DELETE, Constant.ADMIN_ROLE_URL_PREFIX+"/*" )
                        .hasAnyAuthority("roles.delete")

                        .requestMatchers(HttpMethod.GET, Constant.ADMIN_PTIVILEGE_URL_PREFIX )
                        .hasAnyAuthority("privileges.read")
                        .requestMatchers(HttpMethod.POST, Constant.ADMIN_PTIVILEGE_URL_PREFIX )
                        .hasAnyAuthority("privileges.write")
                        .requestMatchers(HttpMethod.PUT, Constant.ADMIN_PTIVILEGE_URL_PREFIX+"/*" )
                        .hasAnyAuthority("privileges.write")
                        .requestMatchers(HttpMethod.DELETE, Constant.ADMIN_PTIVILEGE_URL_PREFIX+"/*" )
                        .hasAnyAuthority("privileges.delete")

                        .requestMatchers(HttpMethod.GET, "/api/v1/profile" )
                        .hasAnyAuthority("profile.read")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/profile" )
                        .hasAnyAuthority("profile.write")

                        // If the seller registers to the web site, he/she needs to get approval from Admin in order to post products.
                        .requestMatchers(HttpMethod.PUT, "/api/v1/products/*/set-status" )
                        .hasAnyAuthority("product.approval")



                        .anyRequest().authenticated())
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                        .logoutSuccessHandler(logoutSuccessHandler())
                        .addLogoutHandler(logoutHandler()))
        ;

        return http.build();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
        };
    }

    @Bean
    public LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            SecurityContextHolder.clearContext();
            // Here you can add token to a blacklist or perform other necessary actions
        };
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET","POST","PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
}
