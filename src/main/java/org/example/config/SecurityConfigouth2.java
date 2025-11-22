//package org.example.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
//public class SecurityConfigouth2 {
//
//    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri:}")
//    private String jwtIssuerURI;
//
//    @Value("${spring.security.oauth2.resourceserver.jwt.jwkset-uri:}")
//    private String jwkSetIssuerURI;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(sessin->sessin.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
//                .authorizeHttpRequests(auth->auth
//                        . requestMatchers("/api/public/**","/h2-console/**","/actuator/health","/api/sign/**").permitAll()
//                        .requestMatchers("/api/tasks/**").hasRole("STUDENT")
//                                .requestMatchers("/api/admin/**")
//                                .hasAnyRole("USER","ADMIN")
////                                .requestMatchers("/api/task/**")
////                                .hasAnyRole("TASK","ADMIN")
//                                .requestMatchers("/actuator/**")
//                                .hasRole("ADMIN")
//                                .anyRequest().authenticated()
//
//                );
//                //config auth2 resource server (JWT)
//        // spring security automatiucally use JWT decoder bean if its exsits
//        //JWT decoders is condtionally created only when outh 2 configured
//        //
//
//        httpSecurity.oauth2ResourceServer(auth2->auth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
////configure ouath2 client for outh2 login
//
//        httpSecurity.oauth2Login(httpSecurityOAuth2LoginConfigurer -> httpSecurityOAuth2LoginConfigurer
//                .defaultSuccessUrl("/api/authicated/profile",true)
//                .failureUrl("/public/oautherror"));
//
//        httpSecurity.httpBasic(httpbasic->{
//
//        }).headers(headers->headers.frameOptions( frameOptions->frameOptions.sameOrigin()));
//// H2 console
///// headers.frameOptions  manage framing, i frame ,secuirty headers control
//        return httpSecurity.build();
//    }
//    //  decodes and validates jwt token rom ouath2 authorization server
//    //  supports both issuers URI and JWTK set url
//    //  this bean is only created when otuh 2 resoruce server is configured
//    //
//    @Bean
//    @ConditionalOnExpression("!'${spring.security.oauth2.resourceserver.jwt.issuer-uri:}'.isEmpty() || "+
//            "!'${spring.security.oauth2.resourceserver.jwt.jwkset-uri:}'.isEmpty()")
//    public JwtDecoder jwtDecoder()
//    {
//        if(jwtIssuerURI!=null && !jwtIssuerURI.isEmpty())
//        {
//            return NimbusJwtDecoder.withIssuerLocation(jwtIssuerURI).build();
//        }
//
//        else if(jwkSetIssuerURI!=null && !jwkSetIssuerURI.isEmpty()){
//            return NimbusJwtDecoder.withJwkSetUri(jwkSetIssuerURI).build();
//        }
//
//        throw new IllegalArgumentException("Auth2 resource server config is missing");
//      }
//
//      @Bean
//      public JwtAuthenticationConverter jwtAuthenticationConverter()
//      {
//          JwtAuthenticationConverter authenticationConverter= new JwtAuthenticationConverter();
//          JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter= new JwtGrantedAuthoritiesConverter();
//
//          jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
//          jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
//
//          authenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//
//          return authenticationConverter;
//      }
//
//        @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
//    {
//        UserDetails admin= User.builder().username("ADMIN").password(passwordEncoder.encode("admin")).roles("ADMIN").build();
//        UserDetails student= User.builder().username("student").password(passwordEncoder.encode("student")).roles("STUDENT").build();
//        UserDetails user= User.builder().username("USER").password(passwordEncoder.encode("user")).roles("USER").build();
//
//        return new InMemoryUserDetailsManager(admin,student,user);
//    }
//
//
//   @Bean
//    public PasswordEncoder passwordEncoder()
//   {
//     return  new BCryptPasswordEncoder();
//   }
//}
