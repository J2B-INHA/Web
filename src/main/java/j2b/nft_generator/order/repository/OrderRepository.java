package j2b.nft_generator.order.repository;

import j2b.nft_generator.member.entity.Member;
import j2b.nft_generator.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Order 엔티티 관련 repository 클래스입니다.
 * @version 1.0.0
 * @author CHO Min HO
 */
public interface OrderRepository extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findByMember(Member member);
}
