package j2b.nft_generator.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order 엔티티 생성 관련 response DTO입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderResDTO {
    private Long orderId;
    private String mainImageUrl;
    private String previewImageUrl;
    private String metadataUrl;
    private String metadataName;
}
