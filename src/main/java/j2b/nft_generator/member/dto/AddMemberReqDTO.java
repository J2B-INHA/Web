package j2b.nft_generator.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Member 엔티티를 생성할 때 사용되는 DTO입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMemberReqDTO {
    private String name;
    private String loginId;
    private String password;
}
