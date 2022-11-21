package j2b.nft_generator.config.security;

import j2b.nft_generator.member.service.MemberService;
import j2b.nft_generator.security.filter.CustomAuthenticationFilter;
import j2b.nft_generator.security.handler.CustomLoginFailureHandler;
import j2b.nft_generator.security.handler.CustomLoginSuccessHandler;
import j2b.nft_generator.security.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 관련 Configuration 파일입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@EnableWebSecurity
@Configuration
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;
    private final AuthenticationConfiguration configuration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/item/**").permitAll()
                .antMatchers("/login/**").anonymous()
                .antMatchers("/signUpForm/**").permitAll()
                .antMatchers("/signUp/**").permitAll()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/signUpSuccess/**").permitAll()
                .antMatchers("/signUpFail/**").permitAll()
                .anyRequest().authenticated();

        http
                .formLogin()
                .usernameParameter("loginId")
                .passwordParameter("password")
                .loginPage("/loginForm").permitAll()
                .and()
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/css/**", "/assets/**",
                "/fonts/**", "/libs/**", "/scss/**", "/tasks/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity)
        throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception  {
        CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter(getAuthenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/loginProcess");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
        customAuthenticationFilter.setAuthenticationFailureHandler(customLoginFailureHandler());
        customAuthenticationFilter.afterPropertiesSet();
        return  customAuthenticationFilter;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 인증 처리의 핵심을 담당하는 메서드입니다.
     * @return 생성된 Bean
     */
    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(memberService, bCryptPasswordEncoder());
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public CustomLoginFailureHandler customLoginFailureHandler() {
        return new CustomLoginFailureHandler();
    }


}
