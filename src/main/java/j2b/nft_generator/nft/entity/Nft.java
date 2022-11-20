package j2b.nft_generator.nft.entity;

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

    protected Nft() { }

    private Nft(String name, String description, int price, double royalty,
                String privilege, String mainImageUrl, String previewImageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.royalty = royalty;
        this.privilege = privilege;
        this.mainImageUrl = mainImageUrl;
        this.previewImageUrl = previewImageUrl;
    }

    /**
     * NFT 엔티티를 생성하는 메서드입니다.
     * NFT 엔티티는 해당 메서드로만 생성됩니다.
     * @param name NFT 이름
     * @param description NFT 설명
     * @param price NFT 가격
     * @param royalty NFT 로옅리
     * @param privilege NFT 특전
     * @param mainImageUrl NFT 메인 이미지 URL
     * @param previewImageUrl NFT 미리보기 이미지 URL
     * @return
     */
    public static Nft createNft(String name, String description, int price, double royalty,
                                String privilege, String mainImageUrl, String previewImageUrl) {
        return new Nft(name, description, price, royalty, privilege, mainImageUrl, previewImageUrl);
    }
}
