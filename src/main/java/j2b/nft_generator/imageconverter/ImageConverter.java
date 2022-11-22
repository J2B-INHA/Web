package j2b.nft_generator.imageconverter;

import j2b.nft_generator.file.FileUploadUtil;
import j2b.nft_generator.file.dto.FileUploadToServerReqDTO;
import j2b.nft_generator.imageconverter.constant.Effect;
import j2b.nft_generator.imageconverter.dto.ConvertImageReqDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.UUID.randomUUID;

/**
 * 쉘 스크립트를 이용하여 업로드한 이미지를 변환하는 클래스입니다.
 * @version 1.0.0
 * @author CHO Min HO
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ImageConverter {

    private final FileUploadUtil fileUploadUtil;

    private final List<String> installCommandList = Arrays.asList("sudo yum install -y python-pip",
            "sudo pip3 install numpy", "sudo yum install -y opencv-python", "sudo pip3 install opencv-python");
    private final String IMAGE_CONVERTER = File.separator + "home" + File.separator + "ec2-user" +
            File.separator + "nft-generator.py ";
    private final String IMAGE_LOCAL_PATH = File.separator + "home" + File.separator + "ec2-user" +
            File.separator + "convertedFile" + File.separator;

    /**
     * 실 서버에 배포 시 파이썬 관련 패키지를 설치하는 bash 명령어를 실행합니다.
     */
    @PostConstruct
    @Profile("prod")
    public void init() {
        for (String command : installCommandList) {
            executeCommand(command);
        }
    }

    /**
     * S3에 업로드되어 있는 이미지를 서버로 다운받아 이미지 변환을 진행하고, 다시 S3에 이미지를 업로드합니다.
     * 이후 원본 이미지는 삭제합니다.
     * token ID는 UUID를 통해 발급합니다.
     * @param dto 원본 이미지 URL, 변환할 이미지 정보
     * @return 변환 후 이미지의 로컬 경로
     */
    public String convertImage(ConvertImageReqDTO dto, FileUploadToServerReqDTO toServerReqDTO, String nftPageLink) {

        // TODO : JSON 만들어지고 나서 이미지 URL (우리 페이지에서의 상품 URL), NFT 설명에 대해 JSON 수정

        if (dto.getEffect() == Effect.CARTOON || dto.getEffect() == Effect.DETAIL) {
            // 이미지 변환
            executeCommand("sudo python3 " + IMAGE_CONVERTER +
                    IMAGE_LOCAL_PATH + " " + dto.getEffect().getValue() + " " + toServerReqDTO.getFilePath() +  " " +
                    dto.getSigmaS() + " " + dto.getSigmaR() + " " + "\"" + dto.getNftDescription() + "\" " +
                    nftPageLink + "  " +  "imageLink "  + randomUUID().toString().substring(0, 10) + " " +
                    toServerReqDTO.getFileName());

            // 변환된 이미지의 경로 반환
            return IMAGE_LOCAL_PATH + toServerReqDTO.getFileName();

        }
        else if (dto.getEffect() == Effect.SKETCH) {
            // 이미지 변환
            executeCommand("sudo python3 " + IMAGE_CONVERTER +
                    IMAGE_LOCAL_PATH + " " + dto.getEffect().getValue() + " " + toServerReqDTO.getFilePath() +  " " +
                    dto.getSigmaS() + " " + dto.getSigmaR() + " " + "\"" + dto.getNftDescription() + "\" " +
                    nftPageLink + "  " +  "imageLink "  + randomUUID().toString().substring(0, 10) + " " +
                    toServerReqDTO.getFileName() + " " + dto.getSketch().getValue() + " " + dto.getShadeFactor());

            // 변환된 이미지의 경로 반환
            return IMAGE_LOCAL_PATH + toServerReqDTO.getFileName();
        }

        return null;
    }

    /**
     * Bash 명령어를 실행하는 메서드입니다.
     * @param command 명령어
     */
    private void executeCommand(String command) {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
