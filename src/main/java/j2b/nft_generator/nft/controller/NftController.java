package j2b.nft_generator.nft.controller;

import j2b.nft_generator.member.service.MemberService;
import j2b.nft_generator.nft.dto.AddNftReqDTO;
import j2b.nft_generator.nft.dto.ViewNftResDTO;
import j2b.nft_generator.nft.service.NftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/addItem")
    public String createNFT(@ModelAttribute AddNftReqDTO nftDto,
                            @RequestPart("mainImageFile") MultipartFile mainImage,
                            @RequestPart("previewImageFile") MultipartFile previewImage) {
        nftService.createNft(nftDto, mainImage, previewImage, memberService.getLoginMember());
        return "index";
    }

    @GetMapping("/item/{id}")
    public String viewSingleItem(@PathVariable(name = "id") Long id, Model model) {
        ViewNftResDTO result = nftService.viewSingleNft(id);
        if (result == null) {
            // TODO : error 페이지 작업 이후 error 페이지로 전송 필요
            return "index";
        }
        model.addAttribute("nft", result);
        return "item";
    }

    @GetMapping("/")
    public String viewHome(Model model) {

        return "index";
    }
}
