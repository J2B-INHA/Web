package j2b.nft_generator.nft.service;

import j2b.nft_generator.member.entity.Member;
import j2b.nft_generator.nft.dto.AddNftReqDTO;
import j2b.nft_generator.nft.dto.AddNftResDTO;
import j2b.nft_generator.nft.entity.Nft;
import j2b.nft_generator.nft.repository.NftRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;

/**
 * NFT 서비스 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NftService {

    private final NftRepository nftRepository;

    /**
     * NFT 엔티티를 생성하고, 넘겨받은 파일에 대한 파일업로드를 진행합니다.
     * @param dto NFT 생성시 넘겨받은 DTO
     * @param member NFT를 생성하는 사용자
     * @return 생성된 NFT 엔티티의 ID를 담고 있는 DTO
     */
    public AddNftResDTO createNft(AddNftReqDTO dto, MultipartFile mainImage, MultipartFile previewImage, Member member) {
        // TODO : 이미지 업로드

        Nft createdNft = nftRepository.save(Nft.createNft(dto, null, null, member));
        return new AddNftResDTO(createdNft.getId());
    }

}
