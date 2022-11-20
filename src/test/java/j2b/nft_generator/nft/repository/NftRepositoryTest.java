package j2b.nft_generator.nft.repository;

import j2b.nft_generator.member.entity.Member;
import j2b.nft_generator.member.repository.MemberRepository;
import j2b.nft_generator.nft.dto.AddNftReqDTO;
import j2b.nft_generator.nft.entity.Nft;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.Optional;

import static j2b.nft_generator.member.entity.Member.createMember;
import static j2b.nft_generator.nft.entity.Nft.createNft;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * NFT repository 관련 클래스입니다.
 * @version 1.0.0
 * @author CHO Min HO
 */
@SpringBootTest
@Transactional
class NftRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private NftRepository nftRepository;

    private Long id;

    @BeforeEach
    public void init() {
        Member member = memberRepository.save(createMember("홍길동", "abcde12345", "password12345"));
        Nft nft = nftRepository.save(createNft(new AddNftReqDTO("이름1", "설명1",
                10000, 0.2, "특전"), null, null,
                null, null, member));
        nftRepository.save(createNft(new AddNftReqDTO("이름2", "설명2",
                20000, 0.2, "특전"), null, null,
                null, null, member));
        nftRepository.save(createNft(new AddNftReqDTO("이름3", "설명3",
                30000, 0.2, "특전"), null, null,
                null, null, member));
        id = nft.getId();
    }

    /**
     * findById 메서드 테스트
     */
    @Test
    void findByIdTest() {
        Optional<Nft> findNft = nftRepository.findById(id);
        assertThat(findNft).isNotEmpty();
        assertThat(findNft.get().getName()).isEqualTo("이름1");
    }

    /**
     * findByName 메서드 테스트
     */
    @Test
    void findByNameTest() {
        Optional<Nft> findNft = nftRepository.findByName("이름2");
        assertThat(findNft).isNotEmpty();
        assertThat(findNft.get().getDescription()).isEqualTo("설명2");
    }
}