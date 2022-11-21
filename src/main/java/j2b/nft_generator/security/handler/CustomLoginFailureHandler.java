package j2b.nft_generator.security.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 로그인에 실패했을 때의 로직을 담당하는 핸들러 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String message = "Invalid userId or Password";

        // exception 관련 메시지 처리
        if (exception instanceof DisabledException) {
            message = "DisabledException account";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "CredentialsExpiredException account";
        } else if (exception instanceof BadCredentialsException) {
            message = "BadCredentialsException account";
        }

        setDefaultFailureUrl("/loginForm?error=true&exception=" + message);

        super.onAuthenticationFailure(request, response, exception);
    }
}
