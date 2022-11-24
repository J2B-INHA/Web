package j2b.nft_generator.file.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * 서버에 이미지를 저장하고나서 반환하는 DTO입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadToServerReqDTO {
    private String fileName;
    private String filePath;
    private String originalFileName;
    private File file;
}
