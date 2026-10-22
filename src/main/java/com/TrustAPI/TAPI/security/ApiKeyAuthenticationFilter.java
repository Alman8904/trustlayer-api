package com.TrustAPI.TAPI.security;

import com.TrustAPI.TAPI.service.ApiKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private final ApiKeyService apiKeyService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String rawKey = request.getHeader("X-API-KEY");

        if (rawKey == null || rawKey.isBlank()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Missing API Key");
            return;
        }

        var apiKeyResult = apiKeyService.findActiveApiKey(rawKey);

        if (apiKeyResult.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid or revoked API Key");
            return;
        }

        var apiKey = apiKeyResult.get();

        Long clientId = apiKey.getClient().getId();

        var authentication = new UsernamePasswordAuthenticationToken(clientId, null, List.of());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        try {
            filterChain.doFilter(request, response);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().startsWith("/v1/");
    }
}
