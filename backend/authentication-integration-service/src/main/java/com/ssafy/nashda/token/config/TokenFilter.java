package com.ssafy.nashda.token.config;

import com.ssafy.nashda.token.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;

    public TokenFilter(TokenProvider tokenProvider, RedisUtil redisUtil) {
        this.tokenProvider = tokenProvider;
        this.redisUtil = redisUtil;
    }

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);

        // Remove prefix from the retrieved value
        String token = getAccessToken(authorizationHeader);

        if (token != null && StringUtils.hasText(token)) {
            // Token exists
            if (tokenProvider.validToken(token)) {

                if(redisUtil.hasAccessToken(token)) {
                    Authentication authentication = tokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not found in Redis");
                    return;
                }
            } else {
                SecurityContextHolder.clearContext();  // Clear SecurityContext if the token is invalid
            }
        }

        filterChain.doFilter(request, response);
    }




    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) return authorizationHeader.substring(TOKEN_PREFIX.length());
        return null;
    }



}
