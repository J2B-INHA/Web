package j2b.nft_generator.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Member 엔티티를 생성할 때 사용되는 DTO입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMemberReqDTO {

    @NotBlank(message = "이름을 입력해주세요!")
    private String name;
    @NotBlank(message = "ID를 입력해주세요!")
    private String loginId;
    @NotBlank(message = "비밀번호를 입력해주세요!")
    private String password;
}
