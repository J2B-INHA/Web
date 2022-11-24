package j2b.nft_generator.test;

import j2b.nft_generator.file.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Test RestController입니다.
 * 각 페이지 로의 접근을 테스트합니다.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class TestRestController {

    private final FileUploadUtil fileUploadUtil;

    @GetMapping("/test/file/upload/local")
    public String uploadFileLocal(@RequestPart MultipartFile file) throws IOException {
        return fileUploadUtil.uploadSingleFileToServer(file).getFilePath();
    }

    @GetMapping("/test/file/delete/local")
    public String deleteFileLocal(@RequestParam String path) {
        fileUploadUtil.deleteSingleFileFromServer(path);
        return "success";
    }

}
