package j2b.nft_generator.member.controller;

import j2b.nft_generator.member.dto.AddMemberReqDTO;
import j2b.nft_generator.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Member 엔티티 관련 컨트롤러 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인 페이지를 보여주는 메서드입니다.
     * @return 로그인 페이지
     */
    @GetMapping("/loginForm")
    public String login() {
        return "loginForm";
    }

    /**
     * 회원가입 페이지를 보여주는 메서드입니다.
     * @return 회원가입 페이지
     */
    @GetMapping("/signUpForm")
    public String signUpForm() {
        return "signUpForm";
    }

    /**
     * 회원가입 로직 처리 메서드입니다.
     * @param dto 회원가입 시 넘겨받은 DTO
     * @return 회원가입 완료 페이지
     */
    @PostMapping("/signUp")
    public String signUp(@ModelAttribute AddMemberReqDTO dto) {
        memberService.createMember(dto);

        // TODO : 회원가입 실패 시 처리 로직
        // TODO : validation 로직

        return "signUpSuccess";

    }
}
