package j2b.nft_generator.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Member 엔티티 관련 컨트롤러 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    @GetMapping("/loginForm")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/signupForm")
    public String signUp(Model model) {
        return "signup";
    }
}
