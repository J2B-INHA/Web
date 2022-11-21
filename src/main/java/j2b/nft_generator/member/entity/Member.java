package j2b.nft_generator.member.entity;

import j2b.nft_generator.member.dto.AddMemberReqDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 사용자 엔티티 클래스입니다.
 * @version 1.0.1
 * @author CHO Min Ho
 */
@Entity
@Getter
@Table(name = "MEMBER")
public class Member implements UserDetails {

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

    @Column(name = "mbr_role")
    private String role;

    protected Member() { }

    private Member(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.role = "USER";
    }

    /**
     * 사용자 엔티티를 생성하는 함수입니다.
     * createMember 함수로만 사용자는 생성될 수 있습니다.
     * @param dto 사용자 생성시 넘겨받은 DTO
     * @return 생성된 사용자 entity
     */
    public static Member createMember(AddMemberReqDTO dto) {
        return new Member(dto.getName(), dto.getLoginId(), dto.getPassword());
    }

    /**
     * 사용자의 권한을 반환하는 메서드입니다.
     * @return 사용자의 권한
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role));
        return roles;
    }

    /**
     * Spring Security에서 사용되는 username을 반환하는 메서드입니다.
     * @return username
     */
    @Override
    public String getUsername() {
        return loginId;
    }

    /**
     * 계정이 만료되어있지 않은지 여부를 반환하는 메서드입니다.
     * @return true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정이 잠금되어있지 않은지 여부를 반환하는 메서드입니다.
     * @return true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호가 만료되어있지 않은지 여부를 반환하는 메서드입니다.
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 게정이 사용 가능한지 여부를 반환하는 메서드입니다.
     * @return true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
