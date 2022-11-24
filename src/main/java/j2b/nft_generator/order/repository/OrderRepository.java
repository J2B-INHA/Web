package j2b.nft_generator.order.repository;

import j2b.nft_generator.member.entity.Member;
import j2b.nft_generator.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Order 엔티티 관련 repository 클래스입니다.
 * @version 1.0.0
 * @author CHO Min HO
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByMember(Member member);
}
