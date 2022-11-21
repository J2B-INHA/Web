package j2b.nft_generator.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationProvider을 이용해 인증이 성공할 경우 처리되는 로직을 담고 있는 클래스입니다.
 * @version 1.0.0
 * @author CHO Min HO
 */
@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // 인증 정보는 SecurityContextHolder이 들고 있습니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Home으로 리다이렉트
        response.sendRedirect("/");

        // TODO : 요청한 URL로 리다이렉트
        // response.sendRedirect("/" + request.getParameter("redirectURL"));
    }
}
