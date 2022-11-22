package j2b.nft_generator.imageconverter.dto;

import j2b.nft_generator.imageconverter.constant.Effect;
import j2b.nft_generator.imageconverter.constant.Sketch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 어떻게 이미지를 변환할지를 저장하는 DTO입니다.
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
    private String nftItemUrl;
    private Sketch sketch;
    private Double shadeFactor;
}
