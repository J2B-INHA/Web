package j2b.nft_generator.security.provider;

import j2b.nft_generator.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 실제 인증 로직을 담당하는 Provider 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 실제 인증을 담당하는 부분입니다.
     * @param authentication the authentication request object.
     * @return 인증 후 생성된 토큰
     * @throws AuthenticationException 인증 실패 관련 예외
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        // AuthenticationFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회
        String userId = token.getName();
        String password = (String) token.getCredentials();
        Member member = (Member) userDetailsService.loadUserByUsername(userId);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new BadCredentialsException(member.getLoginId() + " Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(member, password, member.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
