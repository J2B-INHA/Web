package j2b.nft_generator.order.entity;

import j2b.nft_generator.member.entity.Member;
import j2b.nft_generator.nft.entity.Nft;
import lombok.Getter;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 주문과 관련된 엔티티 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Entity
@Getter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "odr_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbr_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_id")
    private Nft nft;

    @Column(name = "ord_created_date")
    private LocalDateTime orderedDate;

    protected Order() { }

    private Order(Member member, Nft nft) {
        this.member = member;
        this.nft = nft;
        this.orderedDate = LocalDateTime.now();
    }

    /**
     * Order 엔티티를 생성하는 메서드입니다.
     * Order 엔티티는 해당 메서드로만 생성됩니다.
     * @param member 구매한 사용자
     * @param nft 구매할 NFT
     * @return Order 엔티티
     */
    public static Order createOrder(Member member, Nft nft) {
        return new Order(member, nft);
    }
}
