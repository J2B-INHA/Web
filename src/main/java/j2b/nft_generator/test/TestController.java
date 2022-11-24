package j2b.nft_generator.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Test Controller입니다.
 * 각 페이지 로의 접근을 테스트합니다.
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {

    @GetMapping("/test/order")
    public String order() {
        return "orderSuccess";
    }

}
