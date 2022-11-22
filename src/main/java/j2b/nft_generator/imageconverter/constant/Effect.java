package j2b.nft_generator.imageconverter.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 이미지 변환 이펙트에 해당하는 enum 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
public enum Effect {
    CARTOON("cartoon"),
    SKETCH("sketch"),
    DETAIL("detail");

    @Getter
    private final String value;

    Effect(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Effect from(String value) {
        for (Effect effect : Effect.values()) {
            if (effect.getValue().equals(value)) {
                return effect;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
