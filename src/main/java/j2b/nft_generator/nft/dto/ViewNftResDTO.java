package j2b.nft_generator.nft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 특정 NFT 상품 조회 페이지에서 사용되는 DTO 클래스입니다.
 * TODO : 포함되지 않은 정보 (로열티 등) 추가
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewNftResDTO {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String mainImageUrl;
    private double royalty;
    private String privilege;
    private String memberName;
}
