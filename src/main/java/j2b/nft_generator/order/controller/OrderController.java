package j2b.nft_generator.order.controller;

import j2b.nft_generator.file.FileDownloadUtil;
import j2b.nft_generator.order.dto.AddOrderResDTO;
import j2b.nft_generator.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.io.IOException;

/**
 * Order 관련 컨트롤러 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final FileDownloadUtil fileDownloadUtil;

    /**
     * 구매하기에 관련된 메서드입니다.
     * @param id nft 상품 ID
     * @return 구매 성공 페이지
     */
    @GetMapping("/order/{id}")
    public String orderNft(@PathVariable(name = "id") Long id, Model model) {
        AddOrderResDTO order = orderService.createOrder(id);

        model.addAttribute("orderInfo", order);

        return "orderSuccess";
    }

    /**
     * NFT 메타데이터를 다운로드받는 메서드입니다.
     * @param metadataName 메타데이터 파일의 이름
     */
    @GetMapping("/metadata/download/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<byte[]> downloadMetadata(@PathVariable(name = "name") String metadataName) throws IOException {
        return fileDownloadUtil.downloadFile(metadataName);
    }
}
