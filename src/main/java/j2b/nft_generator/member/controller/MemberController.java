package j2b.nft_generator.member.controller;

import j2b.nft_generator.member.dto.AddMemberReqDTO;
import j2b.nft_generator.member.dto.AddMemberResDTO;
import j2b.nft_generator.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    public String signUpForm(Model model) {
        model.addAttribute("memberForm", new AddMemberReqDTO());
        return "signUpForm";
    }

    /**
     * 회원가입 로직 처리 메서드입니다.
     * @param dto 회원가입 시 넘겨받은 DTO
     * @return 회원가입 완료 페이지
     */
    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute("memberForm") AddMemberReqDTO dto, BindingResult result) {

        if (result.hasErrors()) {
            return "signUpForm";
        }

        AddMemberResDTO member = memberService.createMember(dto);
        if (member == null) {
            return "signUpFail";
        }

        return "redirect:/signUpSuccess";

    }

    /**
     * 회원가입 성공시 리다이렉트 되는 페이지입니다.
     * @return 회원가입 성공 페이지
     */
    @GetMapping("/signUpSuccess")
    public String signUpSuccess() {
        return "signUpSuccess";
    }

    /**
     * 로그아웃 메서드입니다.
     * @param request HTTP Request
     * @param response HTTP Response
     * @return 홈페이지
     */
    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

}
