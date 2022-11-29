package j2b.nft_generator.nft.controller;

import j2b.nft_generator.imageconverter.constant.Effect;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
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
     * NFT 생성 폼을 불러오는 메서드입니다.
     * @return NFT 등록 html
     */
    @GetMapping("/addItem")
    public String addItem(Model model) {
        model.addAttribute("nftForm", new AddNftReqDTO());
        model.addAttribute("effects", Effect.values());
        return "addItemForm";
    }

    /**
     * NFT 생성 폼 관련 메서드입니다.
     * @param nftDto Form으로부터 입력받은 NFT 기본 정보
     * @param mainImage NFT 메인 이미지
     * @return item 페이지
     */
    @PostMapping("/addItem")
    public String createNFT(@Valid @ModelAttribute("nftForm") AddNftReqDTO nftDto,
                            BindingResult bindingResult,
                            @RequestPart("mainImageFile") MultipartFile mainImage) throws IOException {

        if (bindingResult.hasErrors()) {
            return "addItemForm";
        }

        AddNftResDTO nft = nftService.createNft(nftDto, mainImage, memberService.getLoginMember());
        return "redirect:/item/" + nft.getId();
    }

    /**
     * 특정 NFT 상품 조회 메서드입니다.
     * @param id NFT 상품 ID
     * @return 특정 상품 페이지
     */
    @GetMapping("/item/{id}")
    public String viewSingleItem(@PathVariable(name = "id") Long id, Model model) {
        ViewNftResDTO result = nftService.viewSingleNft(id);
        if (result == null) {
            return "error/404";
        }

        List<HomeNftResDTO> nftBlocks = nftService.getMultipleNftBlocks(4);

        model.addAttribute("nft", result);
        model.addAttribute("nftBlocks", nftBlocks);
        return "item";
    }

    /**
     * 홈페이지 메서드입니다.
     * @return 홈페이지
     */
    @GetMapping("/")
    public String viewHome(Model model) {
        List<HomeNftResDTO> nftBlocks = nftService.getAllNftBlocks();
        model.addAttribute("nftBlocks", nftBlocks);
        return "index";
    }
}
