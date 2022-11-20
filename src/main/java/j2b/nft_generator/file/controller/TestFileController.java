package j2b.nft_generator.file.controller;

import j2b.nft_generator.file.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 업로드 테스트 컨트롤러입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class TestFileController {

    private final FileUploadUtil fileUploadUtil;

    @GetMapping("/test/file/upload")
    public String uploadSingleFile(@RequestParam(name = "category") String category,
                                   @RequestPart(name = "file")MultipartFile file) {
        return fileUploadUtil.uploadSingleFile(category, file);
    }

    @GetMapping("/test/file/delete")
    public String deleteSingleFile(@RequestParam(name = "url") String url) {
        fileUploadUtil.deleteFile(url);
        return "success";
    }

}
