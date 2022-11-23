package j2b.nft_generator.imageconverter.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 이미지 변환 이펙트에 해당하는 enum 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Getter
@RequiredArgsConstructor
public enum Effect {
    CARTOON("cartoon", "만화화"),
    SKETCH("sketch", "흑백 스케치"),
    DETAIL("detail", "디테일 향상");

    private final String key;
    private final String value;

}
