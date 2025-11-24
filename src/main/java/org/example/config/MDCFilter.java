package org.example.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class MDCFilter implements Filter {

    private static final String REQUEST_ID_KEY = "requestId";
    private static final String USERNAME_KEY = "username";

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            // 1. Generate unique request id
            String requestId = UUID.randomUUID().toString();

            // 2. Put into MDC (ThreadLocal map)
            MDC.put(REQUEST_ID_KEY, requestId);

            // optional: add client IP / VPN info etc.
            String clientIp = httpRequest.getRemoteAddr();
            MDC.put("clientIp", clientIp);

            // continue filter chain
            chain.doFilter(request, response);
        } finally {
            // 3. Clean after request is done
            MDC.clear();
        }
    }
}