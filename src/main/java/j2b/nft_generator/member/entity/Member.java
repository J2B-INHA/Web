package j2b.nft_generator.member.entity;

import lombok.Getter;

import javax.persistence.*;

/**
 * 사용자 엔티티 클래스입니다.
 */
@Entity
@Getter
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "mbr_id")
    private Long id;

    @Column(name = "mbr_name")
    private String name;

    @Column(name = "mbr_login_id")
    private String loginId;

    @Column(name = "mbr_pwd", columnDefinition = "TEXT")
    private String password;

    protected Member() { }

    private Member(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }

    /**
     * 사용자 엔티티를 생성하는 함수입니다.
     * createMember 함수로만 사용자는 생성될 수 있습니다.
     * @param name 사용자 이름
     * @param loginId 사용자 로그인 ID
     * @param password 사용자 비밀번호
     * @return 생성된 사용자 entity
     */
    public static Member createMember(String name, String loginId, String password) {
        return new Member(name, loginId, password);
    }

}
