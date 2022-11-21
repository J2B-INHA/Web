package j2b.nft_generator.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Test Controller입니다.
 * 각 페이지 로의 접근을 테스트합니다.
 */
@Controller
public class TestController {

    @GetMapping("/item")
    public String item(Model model) {
        return "item";
    }

    @GetMapping("/test")
    public String test(Model model) {
        return "forms-basic-inputs";
    }

    @GetMapping("/addItem")
    public String addItem(Model model) {
        return "addItemForm";
    }

    @GetMapping("/loginForm")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/signupForm")
    public String signUp(Model model) {
        return "signup";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        return "loginSuccess";
    }
}
