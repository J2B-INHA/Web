package j2b.nft_generator.nft.controller;

import j2b.nft_generator.nft.dto.AddNftReqDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
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

    @PostMapping("/addItem")
    public String createNFT(@ModelAttribute AddNftReqDTO nftDto,
                            @RequestPart("mainImageFile") MultipartFile mainImage,
                            @RequestPart("previewImageFile") MultipartFile previewImage) {
        log.info("success");
        return "index";
    }
}
