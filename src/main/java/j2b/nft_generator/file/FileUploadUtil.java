package j2b.nft_generator.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import j2b.nft_generator.file.dto.FileUploadResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

/**
 * 파일 업로드 관련 Util 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadUtil {

    public static final String NFT_CATEGORY = "nft";
    public static final String PREVIEW_CATEGORY = "preview";

    /**
     * AWS S3 bucket 이름
     */
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    /**
     * AWS S3 Client 객체
     * AWS S3 서버와 통신합니다.
     */
    private final AmazonS3Client amazonS3Client;

    /**
     * 파일 확장자는 .으로 구분합니다.
     */
    private static final String FILE_EXTENSION_SEPARATOR = ".";
    /**
     * 파일 카테고리는 /으로 구분합니다.
     */
    private static final String CATEGORY_PREFIX = "/";
    /**
     * 파일 생성일자는 _으로 구분합니다.
     */
    private static final String TIME_SEPARATOR = "_";

    /**
     * 파일과 해당 파일의 유형을 넘겨받아, AWS S3 bucket에 저장하고, 저장된 파일에 접근하는 URL을 반환합니다.
     * 저장된 파일은 nft/사진이름_1452152.jpg 와 같은 형식으로 작명됩니다.
     * @param category 파일의 유형
     * @param multipartFile 넘겨받은 multipartFile (파일)
     * @return 업로드된 파일의 접근 URL
     */
    public FileUploadResDTO uploadSingleFile(String category, MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        validateFileExists(multipartFile);

        String fileName = buildFileName(category, multipartFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            log.error("파일 업로드 중 IOException 발생!!!");
            // TODO : 예외 처리 로직
        }

        return new FileUploadResDTO(fileName, amazonS3Client.getUrl(bucketName, fileName).toString());
    }

    /**
     * S3 bucket에서 특정 파일을 삭제합니다.
     * @param fileName 삭제할 파일 이름
     */
    public void deleteFile(String fileName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }

    /**
     * 빈 파일일 경우, BaseException을 던집니다.
     * @param multipartFile 넘겨받은 multipartFile (파일)
     */
    private void validateFileExists(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            log.error("빈 파일 오류 발생!!!");
        }
    }

    /**
     * 파일의 유형, 이름, 현재 시간을 이용해서 저장된 파일 이름을 작명하고, 작명된 이름을 반환합니다.
     * @param category 파일의 유형
     * @param originalFileName 파일의 이름
     * @return 작명된 파일 이름
     */
    private String buildFileName(String category, String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        String fileName = originalFileName.substring(0, fileExtensionIndex);
        String now = String.valueOf(System.currentTimeMillis());

        return category + CATEGORY_PREFIX + fileName + TIME_SEPARATOR + now + fileExtension;
    }


}

