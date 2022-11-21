package j2b.nft_generator.nft.controller;

import j2b.nft_generator.member.service.MemberService;
import j2b.nft_generator.nft.dto.AddNftReqDTO;
import j2b.nft_generator.nft.dto.AddNftResDTO;
import j2b.nft_generator.nft.dto.HomeNftResDTO;
import j2b.nft_generator.nft.dto.ViewNftResDTO;
import j2b.nft_generator.nft.service.NftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * NFT 관련 컨트롤러입니다.
 * @version 1.0.0
 * @author CHO Min HO
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class NftController {

    private final NftService nftService;
    private final MemberService memberService;

    /**
     * NFT 생성 폼 관련 메서드입니다.
     * TODO : validation 처리
     * @param nftDto Form으로부터 입력받은 NFT 기본 정보
     * @param mainImage NFT 메인 이미지
     * @param previewImage NFT 미리보기 이미지
     * @return item 페이지
     */
    @PostMapping("/addItem")
    public String createNFT(@ModelAttribute AddNftReqDTO nftDto,
                            @RequestPart("mainImageFile") MultipartFile mainImage,
                            @RequestPart("previewImageFile") MultipartFile previewImage) {
        AddNftResDTO nft = nftService.createNft(nftDto, mainImage, previewImage, memberService.getLoginMember());
        return "redirect:/item/" + nft.getId();
    }

    @GetMapping("/item/{id}")
    public String viewSingleItem(@PathVariable(name = "id") Long id, Model model) {
        ViewNftResDTO result = nftService.viewSingleNft(id);
        if (result == null) {
            // TODO : error 페이지 작업 이후 error 페이지로 전송 필요
            return "index";
        }

        List<HomeNftResDTO> nftBlocks = nftService.getMultipleNftBlocks(4);

        model.addAttribute("nft", result);
        model.addAttribute("nftBlocks", nftBlocks);
        return "item";
    }

    @GetMapping("/")
    public String viewHome(Model model) {
        List<HomeNftResDTO> nftBlocks = nftService.getMultipleNftBlocks(8);
        model.addAttribute("nftBlocks", nftBlocks);
        return "index";
    }
}
