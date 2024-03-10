package ewha_hackathon.security;

import ewha_hackathon.security.Handler.FailureHandler;
import ewha_hackathon.security.Handler.SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 정적 자원에 대한 보안 예외 처리
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/img/**", "/js/**");
    }

    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(
                                "/css/**",
                                "/images/**",
                                "/js/**",
                                "/header",
                                "/main",
                                "/checkEmail",
                                "/signup",
                                "/login"
                        ).permitAll()
                        .anyRequest().authenticated())
                // 스프링시큐리티 활용해서 로그인 하면 계속 에러 떠서
                // (로그인 하기 위한 findByEmail메서드 실행조차 안됨)
                // disable으로 해놨는데 수정이 필요할 것 같긴해요..
                .formLogin((formLogin) -> formLogin
                                .disable()
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/main")
//                        .permitAll()
//                        .successHandler(new SuccessHandler())
//                        .failureHandler(new FailureHandler())
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .sessionManagement((session) -> session
                        .invalidSessionUrl("/login"))
                .csrf((csrf) -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

        return http.build();
    }
}

