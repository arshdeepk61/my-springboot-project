package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity, MDCFilter mdcFilter,
                                                    SecurityMDCFilter securityMDCFilter) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/public/**","/h2-console/**","/actuator/health","/api/sign/**").permitAll()
                        .requestMatchers("/api/api/tasks/**").hasRole("STUDENT")
                                .requestMatchers("/api/admin/**")
                                .hasAnyRole("USER","ADMIN")
//                                .requestMatchers("/api/task/**")
//                                .hasAnyRole("TASK","ADMIN")
                                .requestMatchers("/actuator/**")
                                .hasRole("ADMIN")
                                .anyRequest().authenticated()


                        ).httpBasic(httpbasic->{})
                .headers(headers->headers.frameOptions( frameOptions->frameOptions.sameOrigin())
                ) // === Add filters here ===
                // First: assign requestId for every request
                .addFilterBefore(mdcFilter, SecurityContextHolderFilter.class)
                // Then: once SecurityContext is set, add username to MDC
                .addFilterAfter(securityMDCFilter, AnonymousAuthenticationFilter.class);
        ;
/// headers.frameOptions  manage framing, i frame ,secuirty headers control
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
    {
        UserDetails admin= User.builder().username("ADMIN").password(passwordEncoder.encode("admin")).roles("ADMIN").build();
        UserDetails student= User.builder().username("student").password(passwordEncoder.encode("student")).roles("STUDENT").build();
        UserDetails user= User.builder().username("USER").password(passwordEncoder.encode("user")).roles("USER").build();

        return new InMemoryUserDetailsManager(admin,student,user);
    }


   @Bean
    public PasswordEncoder passwordEncoder()
   {
     return  new BCryptPasswordEncoder();
   }

}
