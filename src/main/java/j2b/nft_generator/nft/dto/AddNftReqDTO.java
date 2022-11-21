package j2b.nft_generator.nft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * NFT 추가 폼에서 사용되는 DTO입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNftReqDTO {
    @NotBlank(message = "이름을 입력해주세요!")
    private String nameInput;
    @NotBlank(message = "설명을 입력해주세요!")
    private String descriptionInput;
    @NotNull(message = "가격을 입력해주세요!")
    private Integer priceInput;
    @NotNull(message = "로열티를 입력해주세요!")
    private Double royaltyInput;
    private String privilegeInput;
}
