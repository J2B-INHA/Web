package j2b.nft_generator.imageconverter.dto;

import j2b.nft_generator.imageconverter.constant.Effect;
import j2b.nft_generator.imageconverter.constant.Sketch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 어떻게 이미지를 변환할지를 저장하는 DTO입니다.
 * TODO : Sketch를 사용자에게 입력받는 로직
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertImageReqDTO {
    private Effect effect;
    private Integer sigmaS;
    private Double sigmaR;
    private String nftDescription;

    // private Sketch sketch;
    // private Double shadeFactor;
}
