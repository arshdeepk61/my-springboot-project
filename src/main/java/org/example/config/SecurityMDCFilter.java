package org.example.config;

import jakarta.servlet.*;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityMDCFilter implements Filter {

    private static final String USERNAME_KEY = "username";

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated()) {
                MDC.put(USERNAME_KEY, auth.getName()); // principal username
            }
            chain.doFilter(request, response);
        } finally {
            MDC.remove(USERNAME_KEY); // don't clear everything here, only username
        }
    }
}
