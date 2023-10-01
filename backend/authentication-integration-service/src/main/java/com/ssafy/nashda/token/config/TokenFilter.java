package com.ssafy.nashda.token.config;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.token.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    public TokenFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

  /*  @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //요청 헤더의 Authorization 값이 있는지 확인
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);

        //가져온 값에서 접두사 제거
        String token = getAccessToken(authorizationHeader);

        if (StringUtils.hasText(token)) {
            if (!tokenProvider.validToken(token)) {
                SecurityContextHolder.clearContext();
                throw new BadRequestException(ErrorCode.TOKEN_DENIED);

            } else {
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
*/
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

      try {

          //요청 헤더의 Authorization 값이 있는지 확인
          String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);

          //가져온 값에서 접두사 제거
          String token = getAccessToken(authorizationHeader);

          if (StringUtils.hasText(token)) {
              if (!tokenProvider.validToken(token)) {
                  SecurityContextHolder.clearContext();
                  throw new BadRequestException(ErrorCode.TOKEN_DENIED);

              } else {
                  Authentication authentication = tokenProvider.getAuthentication(token);
                  SecurityContextHolder.getContext().setAuthentication(authentication);
              }
          }

          filterChain.doFilter(request, response);
      } catch (BadRequestException bre) {
          // 직접 HTTP 응답 작성
          response.setStatus(HttpStatus.BAD_REQUEST.value());
          response.setContentType("application/json;charset=utf-8");
          response.getWriter().write("{\"errorCode\":500,\"message\":\"토큰이 유효하지 않습니다.\"}");
          response.getWriter().flush();
      }
  }
    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX))
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        return null;
    }

}
