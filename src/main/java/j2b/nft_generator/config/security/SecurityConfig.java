package j2b.nft_generator.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 관련 Configuration 파일입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig {

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
                .antMatchers("/login/**").permitAll()
                .antMatchers("/signup/**").permitAll()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/error/**").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }

}
