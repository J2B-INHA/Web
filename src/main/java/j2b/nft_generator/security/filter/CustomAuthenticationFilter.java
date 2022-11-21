package j2b.nft_generator.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * request로부터 넘겨받은 loginId와 password를 검증하고, 로그인을 처리하는 필터 클래스입니다.
 * @version 1.0.0
 * @author CHO Min HO
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    /**
     * userId와 password를 기반으로 토큰을 발급하는 과정을 담당하는 메서드입니다.
     * 원래는 username/password가 기본값이지만, 이를 userId/password 로 로그인하기 위해 오버라이딩합니다.
     * @param request from which to extract parameters and perform the authentication
     * @param response the response, which may be needed if the implementation has to do a
     * redirect as part of a multi-stage authentication process (such as OpenID).
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (request.getParameter("loginId") != null && request.getParameter("password") != null) {
            UsernamePasswordAuthenticationToken authRequestToken =
                    new UsernamePasswordAuthenticationToken(request.getParameter("loginId"), request.getParameter("password"));
            setDetails(request, authRequestToken);
            return this.getAuthenticationManager().authenticate(authRequestToken);
        }
        return null;
    }
}
