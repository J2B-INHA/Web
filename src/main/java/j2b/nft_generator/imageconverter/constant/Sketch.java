package j2b.nft_generator.imageconverter.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * Sketch의 종류에 해당하는 enum 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
public enum Sketch {
    GRAY("gray"),
    COLOR("color");

    @Getter
    private final String value;

    Sketch(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Sketch from(String value) {
        for (Sketch sketch : Sketch.values()) {
            if (sketch.getValue().equals(value)) {
                return sketch;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
