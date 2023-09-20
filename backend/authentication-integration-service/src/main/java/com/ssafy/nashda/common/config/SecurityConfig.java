package com.ssafy.nashda.common.config;

import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.token.config.TokenFilter;
import com.ssafy.nashda.token.config.TokenProvider;
import com.ssafy.nashda.token.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final RedisUtil redisUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors
                        .disable())
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .antMatchers("api/user/signin", "api/users/signup").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new TokenFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .formLogin(formLogin -> formLogin
                        .disable())
                .logout(logout -> logout
                        .logoutSuccessUrl("/signin")
                        .permitAll()
                );
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/user/signin", "/api/user/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new TokenFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .formLogin().disable()
                .logout()
                .permitAll();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 출처 허용
        configuration.setAllowedOriginPatterns(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")); // 모든 HTTP 메서드 허용
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 헤더 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}