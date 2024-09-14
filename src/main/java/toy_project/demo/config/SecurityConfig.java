package toy_project.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers("/h2-console/**").permitAll() // H2 콘솔에 대한 접근 허용
                                        .anyRequest().permitAll()  // 모든 요청에 대해 접근 허용
//                                .anyRequest().authenticated()  // 그 외의 요청은 인증 필요
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/users/**", "/posts/**")  // H2 콘솔 및 /users/**, /posts/**에 대한 CSRF 비활성화
                )
                .headers(headersConfigurer ->
                        headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)  // 최신 메서드로 프레임 옵션 설정
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}