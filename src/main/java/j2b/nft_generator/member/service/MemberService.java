package j2b.nft_generator.member.service;

import j2b.nft_generator.member.dto.AddMemberReqDTO;
import j2b.nft_generator.member.dto.AddMemberResDTO;
import j2b.nft_generator.member.entity.Member;
import j2b.nft_generator.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Member 관련 서비스 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * Member 엔티티를 생성하는 클래스입니다.
     * @param dto Member 엔티티 생성 시 넘겨받은 DTO
     * @return 생성된 Member의 ID를 담고 있는 DTO
     */
    public AddMemberResDTO createMember(AddMemberReqDTO dto) {
        Member member = memberRepository.save(Member.createMember(dto));
        return new AddMemberResDTO(member.getId());
    }

    /**
     * 현재 로그인된 사용자 정보를 반환합니다.
     * TODO : 현재 로그인을 임시로 구현했기 때문에 Mockup 사용자를 저장하고, 이를 반환하도록 설계하였습니다.
     * TODO : 사용자 관련 로직을 전부 구현되어 있으며, 추후 로그인 로직을 보완할 예정입니다.
     * @return 현재 로그인된 사용자 정보
     */
    public Member getLoginMember() {
        AddMemberResDTO member = createMember(new AddMemberReqDTO("조민호", "abcde12345", "password12345"));
        return memberRepository.findById(member.getId()).get();
    }

}
