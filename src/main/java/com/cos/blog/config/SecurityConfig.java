package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;


@Configuration      //빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@EnableWebSecurity //스프링 시큐리티를 활성하고 웹 보안 설정을 구성하느데 사용 , websecurityConfiguereApdapter 클래스를 상속한 구성 클래스에서 사용됨
public class SecurityConfig    {

    // 3. principalDetailService 제거
    // @Autowired
    // private PrincipalDetailService principalDetailService;

    @Bean  //IoC가 됨
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder(); // <- Spring 이 관리
    }

      @Bean
      public WebSecurityCustomizer ignore() {
          return w -> w.ignoring().requestMatchers("/static/**");
      }

      @Bean
      public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
          return authenticationConfiguration.getAuthenticationManager();
      }
    // 시큐리티가 대신 로그인해주는데 password 를 가로채기를 하는데
    // 해당 password 가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(principalDetailService).passwordEncoder(encode());
    // }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf 토큰 비활성화 (테스트 시 걸어두는게 좋음)
        http.csrf(c -> c.disable());
        http.authorizeHttpRequests(a -> {
            a.requestMatchers(RegexRequestMatcher.regexMatcher("/board/\\d+"+"/dummy/\\d+")).permitAll()
                .requestMatchers("/users/**", "/board/**").authenticated()
                .anyRequest().permitAll();
        });
        http.formLogin(
                f -> {
                    f.loginPage("/auth/loginForm").loginProcessingUrl("/auth/loginProc").defaultSuccessUrl("/").failureUrl("/auth/loginForm");

                });
        //인증 주소 설정
        return http.build();
    }
}