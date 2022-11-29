package j2b.nft_generator.nft.service;

import j2b.nft_generator.bash.BashService;
import j2b.nft_generator.file.FileUploadUtil;
import j2b.nft_generator.file.dto.FileUploadResDTO;
import j2b.nft_generator.file.dto.FileUploadToServerReqDTO;
import j2b.nft_generator.imageconverter.dto.ConvertImageReqDTO;
import j2b.nft_generator.imageconverter.service.ImageConverter;
import j2b.nft_generator.member.entity.Member;
import j2b.nft_generator.nft.dto.AddNftReqDTO;
import j2b.nft_generator.nft.dto.AddNftResDTO;
import j2b.nft_generator.nft.dto.HomeNftResDTO;
import j2b.nft_generator.nft.dto.ViewNftResDTO;
import j2b.nft_generator.nft.entity.Nft;
import j2b.nft_generator.nft.repository.NftRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static j2b.nft_generator.file.FileUploadUtil.NFT_CATEGORY;
import static j2b.nft_generator.file.FileUploadUtil.PREVIEW_CATEGORY;

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
    private final ImageConverter imageConverter;
    private final BashService bashService;
    private final String NFT_ITEM_URL = "https://j2b-inha.shop/item/";
    private final String NODE_COMMAND = "node";
    private final String NFT_MINT_DIRECTORY = "/home/ec2-user/nft-mint/";
    private final String NFT_MINT_FILE = "scripts/mint-nft.js";

    @Value("${user.profile}")
    private String CURRENT_PROFILE;


    /**
     * NFT 엔티티를 생성하고, 넘겨받은 파일에 대한 파일업로드를 진행합니다.
     * @param dto NFT 생성시 넘겨받은 DTO
     * @param member NFT를 생성하는 사용자
     * @return 생성된 NFT 엔티티의 ID를 담고 있는 DTO
     */
    public AddNftResDTO createNft(AddNftReqDTO dto, MultipartFile mainImage, Member member)
            throws IOException {
        // 1. 로컬일 경우
        if (CURRENT_PROFILE.equals("dev")) {
            return createNFTInLocal(dto, mainImage, member);
        }

        // 2. 실제 서버일 경우
        if (CURRENT_PROFILE.equals("prod")) {
            return createNFTInServer(dto, mainImage, member);
        }

        return null;
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
        return new ViewNftResDTO(findNft.get().getId(), findNft.get().getName(), findNft.get().getPrice(), findNft.get().getDescription(),
                findNft.get().getMainImageUrl(), findNft.get().getRoyalty(), findNft.get().getPrivilege(), findNft.get().getMember().getName());
    }

    /**
     * 특정 개수의 NFT 블럭을 반환하는 메서드입니다.
     * related items 등을 조회할 때 이용됩니다.
     * TODO : 페이징 로직 특정 필요
     * @param size 조회할 NFT의 개수
     * @return 조회할 만큼의 NFT를 담고 있는 리스트
     */
    public List<HomeNftResDTO> getMultipleNftBlocks(int size) {
        List<HomeNftResDTO> result = new ArrayList<>();
        Page<Nft> findNfts = nftRepository.findAll(PageRequest.of(0, size));
        for (Nft nft : findNfts) {
            result.add(new HomeNftResDTO(nft.getId(), nft.getName(), nft.getPrice(), nft.getPreviewImageUrl()));
        }
        return result;
    }

    /**
     * 등록된 모든 NFT 블럭을 반환하는 메서드입니다.
     * index.html에서 사용됩니다.
     * @return 모든 NFT를 담고 있는 리스트
     */
    public List<HomeNftResDTO> getAllNftBlocks() {
        List<HomeNftResDTO> result = new ArrayList<>();
        List<Nft> allNfts = nftRepository.findAll();
        for (Nft nft : allNfts) {
            result.add(new HomeNftResDTO(nft.getId(), nft.getName(), nft.getPrice(), nft.getPreviewImageUrl()));
        }
        return result;
    }

    /**
     * 메타데이터 URL을 이용해서 NFT Minting을 진행하는 메서드입니다.
     * 현재 버전에서는 테스트 계정으로 Minting이 되도록 설정되어 있으며, 각 사용자의 지갑으로 Minting되진 않습니다.
     * 또한 트랜잭션 ID 등을 반환하는 로직 등은
     * @param itemId 등록할 NFT의 상품 ID
     */
    public void mintNft(Long itemId) {
        if (CURRENT_PROFILE.equals("prod")) {
            String metadataUrl = nftRepository.findById(itemId).get().getNftMetaDataUrl();

            List<String> generateNFTCommand =
                    Arrays.asList("/bin/sh",
                            "-c",
                            bashService.makeCommand(Arrays.asList(
                                    "cd",
                                    NFT_MINT_DIRECTORY,
                                    "&&",
                                    ".",
                                    "~/.nvm/nvm.sh",
                                    "&&",
                                    NODE_COMMAND,
                                    NFT_MINT_FILE,
                                    metadataUrl)));

            bashService.executeCommand(generateNFTCommand);
        }
    }

    /**
     * 로컬에서 이미지 변환을 진행하지 않고 NFT를 생성하는 메서드입니다.
     * TODO : JSON 파일을 더미로 넣어놓는 테스트 데이터
     * @param dto 생성할 NFT 정보
     * @param mainImage 원본 이미지
     * @param member 사용자
     * @return 생성된 NFT의 정보를 담고 있는 DTO
     */
    private AddNftResDTO createNFTInLocal(AddNftReqDTO dto, MultipartFile mainImage, Member member) {
        // 1. 이미지 업로드
        FileUploadResDTO fileUploadResDTO = fileUploadUtil.uploadSingleFileByMultipartFile(NFT_CATEGORY, mainImage);

        // 2. NFT 상품 생성
        Nft createdNft = nftRepository.save(Nft.createNft(dto, fileUploadResDTO.getFileUrl(),
                fileUploadResDTO.getFileUrl(), fileUploadResDTO.getFileName(), fileUploadResDTO.getFileName(), member));

        return new AddNftResDTO(createdNft.getId());
    }

    /**
     * 실제 서버에서 이미지 변환까지 진행해서 NFT를 생성하는 메서드입니다.
     * @param dto 생성할 NFT 정보
     * @param mainImage 원본 이미지
     * @param member 사용자
     * @return 생성된 NFT의 정보를 담고 있는 DTO
     * @throws IOException 파일 업로드 실패 시
     */
    private AddNftResDTO createNFTInServer(AddNftReqDTO dto, MultipartFile mainImage, Member member) throws IOException {

        // 1. 로컬 서버에 이미지 업로드
        FileUploadToServerReqDTO imageLocalUploadRes = fileUploadUtil.uploadSingleFileToServer(mainImage);

        // 2. S3에 원본 이미지 (미리보기 이미지로 사용) 업로드
        FileUploadResDTO previewImageRes = fileUploadUtil.uploadSingleFileByFile(PREVIEW_CATEGORY, imageLocalUploadRes.getFile());

        // 3. 업로드된 이미지 변환
        ConvertImageReqDTO convertImageReqDTO = new ConvertImageReqDTO(dto.getEffect(), dto.getSigmaS(),
                dto.getSigmaR(), dto.getDescriptionInput());

        String convertedImageLocalPath =
                imageConverter.convertImage(convertImageReqDTO, imageLocalUploadRes);

        // 4. 로컬 서버에 있는 변환된 이미지를 S3에 업로드
        FileUploadResDTO imageS3UploadRes =
                fileUploadUtil.uploadSingleFileFromServer(NFT_CATEGORY, convertedImageLocalPath);

        // 5. NFT 상품 생성
        Nft createdNft = nftRepository.save(Nft.createNft(dto, imageS3UploadRes.getFileUrl(),
                previewImageRes.getFileUrl(), imageS3UploadRes.getFileName(), previewImageRes.getFileName(), member));

        // 6. JSON 파일 생성
        String extractedJsonLocalPath = imageConverter.extractJsonFromImage(convertImageReqDTO, imageLocalUploadRes,
                NFT_ITEM_URL + createdNft.getId(), imageS3UploadRes.getFileUrl());

        // 7. 생성된 JSON 파일을 S3에 업로드
        FileUploadResDTO jsonS3UploadRes =
                fileUploadUtil.uploadSingleFileFromServer("metadata", extractedJsonLocalPath);

        // 8. 생성된 JSON URL을 NFT 엔티티에 반영
        createdNft.setNftMetaData(jsonS3UploadRes.getFileUrl(), jsonS3UploadRes.getFileName());

        // 9. 로컬 서버에 남아있는 원본 이미지와 변환된 이미지와 JSON 파일 삭제
        fileUploadUtil.deleteSingleFileFromServer(imageLocalUploadRes.getFilePath());
        fileUploadUtil.deleteSingleFileFromServer(extractedJsonLocalPath);
        fileUploadUtil.deleteSingleFileFromServer(convertedImageLocalPath);

        return new AddNftResDTO(createdNft.getId());
    }

}
