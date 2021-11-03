package com.example.population.config;

import com.example.population.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MemberService memberService;

    private final AuthenticationFailureHandler customFailureHandler;

    // BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체입니다.
    // Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록합니다.
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .and()
                .formLogin()
                    .loginPage("/member/login")
                    .defaultSuccessUrl("/")
                    .failureHandler(customFailureHandler)	// 실패 핸들러
                    .permitAll()
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .and()
                .exceptionHandling();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
