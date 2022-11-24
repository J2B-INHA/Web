package j2b.nft_generator.order.service;

import j2b.nft_generator.member.service.MemberService;
import j2b.nft_generator.nft.entity.Nft;
import j2b.nft_generator.nft.repository.NftRepository;
import j2b.nft_generator.order.dto.AddOrderResDTO;
import j2b.nft_generator.order.entity.Order;
import j2b.nft_generator.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Order 엔티티 관련 서비스 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final NftRepository nftRepository;
    private final MemberService memberService;

    /**
     * 주문을 하는 메서드입니다.
     * @param nftId 구매할 NFT의 ID
     * @return 생성된 Order 엔티티의 ID
     */
    public AddOrderResDTO createOrder(Long nftId) {
        Optional<Nft> findNft = nftRepository.findById(nftId);

        if (findNft.isEmpty()) {
            // TODO : 공통 예외 처리 로직 필요
        }

        Order order = orderRepository.save(Order.createOrder(memberService.getLoginMember(), findNft.get()));

        return new AddOrderResDTO(order.getId(), findNft.get().getMainImageUrl(), findNft.get().getPreviewImageUrl(),
                findNft.get().getNftMetaDataUrl());
    }

}
