package j2b.nft_generator.nft.service;

import j2b.nft_generator.file.FileUploadUtil;
import j2b.nft_generator.file.dto.FileUploadResDTO;
import j2b.nft_generator.member.entity.Member;
import j2b.nft_generator.nft.dto.AddNftReqDTO;
import j2b.nft_generator.nft.dto.AddNftResDTO;
import j2b.nft_generator.nft.dto.ViewNftResDTO;
import j2b.nft_generator.nft.entity.Nft;
import j2b.nft_generator.nft.repository.NftRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;

import java.util.Optional;

import static j2b.nft_generator.file.FileUploadUtil.NFT_CATEGORY;

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
    private final FileUploadUtil fileUploadUtil;

    /**
     * NFT 엔티티를 생성하고, 넘겨받은 파일에 대한 파일업로드를 진행합니다.
     * @param dto NFT 생성시 넘겨받은 DTO
     * @param member NFT를 생성하는 사용자
     * @return 생성된 NFT 엔티티의 ID를 담고 있는 DTO
     */
    public AddNftResDTO createNft(AddNftReqDTO dto, MultipartFile mainImage, MultipartFile previewImage, Member member) {
        FileUploadResDTO mainFile = fileUploadUtil.uploadSingleFile(NFT_CATEGORY, mainImage);
        FileUploadResDTO previewFile = fileUploadUtil.uploadSingleFile(NFT_CATEGORY, previewImage);

        Nft createdNft = nftRepository.save(Nft.createNft(dto, mainFile.getFileUrl(),
                previewFile.getFileUrl(), mainFile.getFileName(), previewFile.getFileName(), member));

        return new AddNftResDTO(createdNft.getId());
    }

    /**
     * 특정 ID를 가지는 상품의 정보를 조회하는 메서드입니다.
     * @param id 해당 상품의 ID
     * @return 해당 NFT 상품의 정보를 담고 있는 DTO
     */
    public ViewNftResDTO viewSingleNft(Long id) {
        Optional<Nft> findNft = nftRepository.findById(id);

        if (findNft.isEmpty()) {
            // TODO : 예외 처리 로직 필요
            return null;
        }
        return new ViewNftResDTO(findNft.get().getName(), findNft.get().getPrice(), findNft.get().getDescription(),
                findNft.get().getMainImageUrl());
    }

}
