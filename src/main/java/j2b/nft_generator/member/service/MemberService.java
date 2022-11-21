package j2b.nft_generator.member.service;

import j2b.nft_generator.member.dto.AddMemberReqDTO;
import j2b.nft_generator.member.dto.AddMemberResDTO;
import j2b.nft_generator.member.entity.Member;
import j2b.nft_generator.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Member 관련 서비스 클래스입니다.
 * @version 1.0.1
 * @author CHO Min Ho
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * Member 엔티티를 생성하는 클래스입니다.
     * @param dto Member 엔티티 생성 시 넘겨받은 DTO
     * @return 생성된 Member의 ID를 담고 있는 DTO
     */
    public AddMemberResDTO createMember(AddMemberReqDTO dto) {
        // 중복된 로그인 Id인 경우
        if (memberRepository.findByLoginId(dto.getLoginId()).isPresent()) {
            return null;
        }
        Member member = memberRepository.save(Member.createMember(dto));
        return new AddMemberResDTO(member.getId());
    }

    /**
     * 현재 로그인된 사용자 정보를 반환합니다.
     * @return 현재 로그인된 사용자 정보
     */
    public Member getLoginMember() {
        return (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        return memberRepository.findByLoginId(loginId).orElseThrow(() -> new UsernameNotFoundException(loginId));
    }
}
