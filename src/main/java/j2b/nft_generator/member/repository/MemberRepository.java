package j2b.nft_generator.member.repository;

import j2b.nft_generator.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Member 엔티티에 관한 JPA repository입니다.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    Optional<Member> findByLoginId(String loginId);
}
