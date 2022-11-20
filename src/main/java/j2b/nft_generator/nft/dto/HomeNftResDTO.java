package j2b.nft_generator.nft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * index.html 파일 및 related 상품에서 보여지는 상품 블럭에 담기는 상품의 데이터를 가지고 있는 DTO입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeNftResDTO {
    private Long id;
    private String name;
    private int price;
    private String previewImageUrl;
}
