package j2b.nft_generator.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URLEncoder;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * AWS S3에서 파일을 다운로드할 때 사용되는 클래스입니다.
 * @version 1.0.0
 * @author CHO Min Ho
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileDownloadUtil {
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
     * 파일을 다운로드하는 ResponseEntity를 생성하는 메서드입니다.
     * @param storedFileName AWS S3에 저장된 파일 이름
     * @return 파일 다운로드 관련 ResponseEntity
     * @throws IOException 파일 다운로드 실패 시
     */
    public ResponseEntity<byte[]> downloadFile(String storedFileName) throws IOException {
        S3Object object = amazonS3Client.getObject(new GetObjectRequest(bucketName, storedFileName));
        S3ObjectInputStream objectInputStream =  object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        String fileName = URLEncoder.encode(storedFileName, UTF_8).replaceAll("\\+", "%20");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

}
