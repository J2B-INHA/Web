package j2b.nft_generator.nft.entity;

import j2b.nft_generator.member.entity.Member;
import j2b.nft_generator.nft.dto.AddNftReqDTO;
import lombok.Getter;
import javax.persistence.*;

/**
 * NFT 엔티티입니다.
 */
@Entity
@Getter
@Table(name = "NFT")
public class Nft {

    @Id
    @GeneratedValue
    @Column(name = "nft_id")
    private Long id;

    @Column(name = "nft_name")
    private String name;

    @Column(name = "nft_des", columnDefinition = "TEXT")
    private String description;

    @Column(name = "nft_price")
    private int price;

    @Column(name = "nft_royalty")
    private double royalty;

    @Column(name = "nft_privilege", columnDefinition = "TEXT")
    private String privilege;

    @Column(name = "nft_main_img", columnDefinition = "TEXT")
    private String mainImageUrl;

    @Column(name = "nft_preview_img", columnDefinition = "TEXT")
    private String previewImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbr_id")
    private Member member;

    protected Nft() { }

    private Nft(String name, String description, int price, double royalty,
                String privilege, String mainImageUrl, String previewImageUrl, Member member) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.royalty = royalty;
        this.privilege = privilege;
        this.mainImageUrl = mainImageUrl;
        this.previewImageUrl = previewImageUrl;
        this.member = member;
    }

    /**
     * NFT 엔티티를 생성하는 메서드입니다.
     * NFT 엔티티는 해당 메서드로만 생성됩니다.
     * @param dto NFT 생성시 넘겨받은 DTO
     * @param mainImageUrl NFT 메인 이미지 URL
     * @param previewImageUrl NFT 미리보기 이미지 URL
     * @param member NFT를 생성한 사용자
     * @return 생성된 NFT 엔티티
     */
    public static Nft createNft(AddNftReqDTO dto, String mainImageUrl, String previewImageUrl, Member member) {
        return new Nft(dto.getNameInput(), dto.getDescriptionInput(), dto.getPriceInput(),
                dto.getRoyaltyInput(), dto.getPrivilegeInput(), mainImageUrl, previewImageUrl, member);
    }
}
